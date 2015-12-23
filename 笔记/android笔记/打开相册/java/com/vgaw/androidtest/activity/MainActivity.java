package com.vgaw.androidtest.activity;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
    }

    public void fuckNext(View view) {

        Intent intent = null;
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

        startActivityForResult(intent, 0x11);
    }

    // TODO: 2015-12-22 高效字符串比较
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        System.out.println("++" + data.getData());

        String path = null;
        Uri uri = data.getData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                DocumentsContract.isDocumentUri(this, uri)) {
            String wholeId = DocumentsContract.getDocumentId(uri);
            if (isExternalStorageDocument(uri)) {
                String[] split = wholeId.split(":");
                String type = split[0];
                if ("primary".equals(type)) {
                    System.out.println("i am primary");
                    path = Environment.getExternalStorageDirectory() + "/" + split[1];
                    // Environment.getExternalStorageDirectory(): /storage/emulated/0
                }
                // TODO: 2015-12-22 handle non-primary volumes
                System.out.println("i am non-primary");
            } else if (isDownloadsDocument(uri)) {
                // content://com.android.providers.downloads.documents/document/1
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(wholeId));
                System.out.println(contentUri);
                path = getPath(this, contentUri, null, null);
                System.out.println("i am downloads");
            } else if (isMediaDocument(uri)) {
                // content://com.android.providers.media.documents/document/image%3A11
                String id = wholeId.split(":")[1];
                path = getPath(this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        MediaStore.Images.Media._ID + "=?",
                        new String[]{id});
                System.out.println("i am media");
            }
        } else if ("content".equals(uri.getScheme())) {
            // action_pick && api<19
            // content://media/external/images/media/12
            if (isGooglePhotosUri(uri)) {
                path = uri.getLastPathSegment();
            }
            System.out.println("i am content");
            path = getPath(this, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // action_pick && api>=19
            // file:///storage/emulated/0/...
            path = uri.getPath();
            System.out.println("i am file");
        }

        System.out.println(path);
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
