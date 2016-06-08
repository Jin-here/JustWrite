package com.vgaw.androidtest.activity;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vgaw.androidtest.R;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2015-11-13.
 */
public class MainActivity extends FragmentActivity {
    private ImageView iv_show;
    private TextView tv_uri;
    private TextView tv_path;

    private String destPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_album).setOnClickListener(listener);
        findViewById(R.id.btn_camera).setOnClickListener(listener);
        iv_show = (ImageView) findViewById(R.id.iv_show);
        tv_uri = (TextView) findViewById(R.id.tv_uri);
        tv_path = (TextView) findViewById(R.id.tv_path);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                // 打开相机
                case R.id.btn_camera:
                    openCamera();
                    break;
                // 打开相册
                case R.id.btn_album:
                    openAlbum();
                    break;
            }
        }
    };

    /**
     * 打开相机
     *
     * <manifest ... >
     *      <uses-feature android:name="android.hardware.camera"
     *              android:required="true" />
     *      ...
     * </manifest>
     * 告知Google Play该应用是基于相机的，方便其进行筛选（即没有相机功能的设备不能安装此程序）
     *
     * hasSystemFeature(PackageManager.FEATURE_CAMERA)，该方法可以查看设备相机功能可不可用
     */
    private void openCamera(){
        // 方法1：最简单，返回中附带照片缩略图
        /*Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 该方法返回了可以处理Intent返回的Activity组件，如果Intent返回没有Activity处理，程序将会崩溃
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 0x70);
        }*/

        // 方法2
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            File destFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), String.valueOf(System.currentTimeMillis()) + ".png");
            destPath = destFile.getAbsolutePath();
            tv_path.setText(destPath);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destFile));
            startActivityForResult(takePictureIntent, 0x70);
        }
    }

    /**
     * 打开相册
     */
    public void openAlbum() {
        Intent intent = null;

        /*intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择图片"), 0x71);*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            intent = new Intent();
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }else{
            intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // MediaStore.Images.Media.EXTERNAL_CONTENT_URI: content://media/external/images/media
            // NOTE: it will cause exception if you put intent.addCategory(Intent.CATEGORY_OPENABLE) here
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
		}

        startActivityForResult(intent, 0x71);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        switch (requestCode){
            // 相册
            case 0x71:
                proAlbumBack(data);
                break;
            // 相机
            case 0x70:
                proCameraBack(data);
                break;
        }
    }

    private void proCameraBack(Intent data){
        // 对应方法1
        // 从"data"中获得的缩略图适用于图标上面，大图会比较模糊
        /*Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        iv_show.setImageBitmap(bitmap);*/

        // 对应方法2
        iv_show.setImageBitmap(compressPic());

        addToGallery();
    }

    /**
     * 将图片添加到画廊
     */
    private void addToGallery(){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(new File(destPath)));
        sendBroadcast(mediaScanIntent);
    }

    // 解码缩放图像
    private Bitmap compressPic(){
        int targetWidth = iv_show.getWidth();
        int targetHeight = iv_show.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(destPath, bmOptions);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = calculateInSampleSize(bmOptions, targetWidth, targetHeight);
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(destPath, bmOptions);
        return bitmap;
    }

    public int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // BEGIN_INCLUDE (calculate_sample_size)
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            long totalPixels = width * height / inSampleSize;

            // Anything more than 2x the requested pixels we'll sample down further
            final long totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels > totalReqPixelsCap) {
                inSampleSize *= 2;
                totalPixels /= 2;
            }
        }
        Log.e("fuck", "缩放倍数：" + inSampleSize);
        return inSampleSize;
        // END_INCLUDE (calculate_sample_size)
    }

    private void proAlbumBack(Intent data){
        tv_uri.setText(data.getDataString());

        String path = null;
        Uri uri = data.getData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                DocumentsContract.isDocumentUri(this, uri)) {
            String wholeId = DocumentsContract.getDocumentId(uri);
            if (isExternalStorageDocument(uri)) {
                String[] split = wholeId.split(":");
                String type = split[0];
                if ("primary".equals(type)) {
                    Log.e("fuck", "i am primary");
                    path = Environment.getExternalStorageDirectory() + "/" + split[1];
                    // Environment.getExternalStorageDirectory(): /storage/emulated/0
                }
                // TODO: 2015-12-22 handle non-primary volumes
                Log.e("fuck", "i am non-primary");
            } else if (isDownloadsDocument(uri)) {
                // content://com.android.providers.downloads.documents/document/1
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(wholeId));
                Log.e("fuck", contentUri.toString());
                path = getPath(this, contentUri, null, null);
                Log.e("fuck", "i am downloads");
            } else if (isMediaDocument(uri)) {
                // content://com.android.providers.media.documents/document/image%3A11
                String id = wholeId.split(":")[1];
                path = getPath(this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        MediaStore.Images.Media._ID + "=?",
                        new String[]{id});
                Log.e("fuck", "i am media");
            }
        } else if ("content".equals(uri.getScheme())) {
            // action_pick && api<19
            // content://media/external/images/media/12
            if (isGooglePhotosUri(uri)) {
                path = uri.getLastPathSegment();
            }
            Log.e("fuck", "i am content");
            path = getPath(this, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // action_pick && api>=19
            // file:///storage/emulated/0/...
            path = uri.getPath();
            Log.e("fuck", "i am file");
        }

        destPath = path;
        tv_path.setText(path);

        iv_show.setImageBitmap(compressPic());
    }

    /**
     * return the path or null
     * @param context
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    private String getPath(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;
    	Cursor cursor = null;
    	try{
			cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA},
    				selection, selectionArgs, null);
    		if (cursor != null && cursor.moveToFirst()) {
    			int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
    			path = cursor.getString(columnIndex);
    			cursor.close();
    		}
    	}finally{
    	}
        return path;
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
