package com.vgaw.androidtest.activity;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hchl.financeteam.R;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by caojin on 2016/4/29.
 */
public class Utils {
    /***************************格式判断************************************/
    /**
     * 是否为手机号
     *
     * @param phone
     * @return
     */
    public static boolean isMoblie(String phone) {
        String pattern = "^[1][3|4|5|7|8]+\\d{9}";
        return Pattern.compile(pattern).matcher(phone).matches();
    }

    /**
     * 是否为身份证
     *
     * @param input
     * @return
     */
    public static boolean isIdCard(String input) {
        if (input.length() != 18) {
            return false;
        } else {
            int sigma = 0;
            Integer[] a = new Integer[]{Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(8), Integer.valueOf(4), Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(6), Integer.valueOf(3), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(8), Integer.valueOf(4), Integer.valueOf(2)};
            String[] w = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

            int number;
            for (number = 0; number < 17; ++number) {
                int check_number = Integer.parseInt(input.substring(number, number + 1));
                int wi = a[number].intValue();
                sigma += check_number * wi;
            }

            number = sigma % 11;
            String var7 = w[number];
            return input.substring(17).equals(var7);
        }
    }

    /****************************拷贝***********************************/
    /**
     * 深度复制
     *
     * @param srcObj
     * @return
     */
    public static Object deepClone(Object srcObj) {

        Object cloneObj = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(out);
            oo.writeObject(srcObj);

            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(in);
            cloneObj = oi.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return cloneObj;
    }

    /**
     * 复制List
     *
     * @param raw
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> cloneList(List<T> raw, Class<T> cls) {
        return JSONArray.parseArray(JSON.toJSONString(raw), cls);
    }

    /**
     * 复制对象
     *
     * @param raw
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T cloneObject(T raw, Class<T> cls) {
        return JSON.parseObject(JSON.toJSONString(raw), cls);
    }

    /******************************格式化*********************************/
    public static String getDate(String time) {
        if (time == null || "".equals(time)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(Long.parseLong(time)));
    }

    /*****************************简化操作**********************************/
    public static void setText(TextView v, String text) {
        v.setText(text == null ? "" : text);
    }

    public static void setCheck(RadioGroup v, int index) {
        if (index < 1) {
            return;
        }
        v.check(v.getChildAt(index - 1).getId());
    }

    public static void setCheck(RadioGroup v, String index) {
        if (index != null) {
            try {
                v.check(v.getChildAt(Integer.parseInt(index) - 1).getId());
            } catch (Exception e) {
                return;
            }
        }
    }

    public static String nullOfEmtpy(String raw) {
        return nullOf(raw, "");
    }

    public static String nullOf(String raw, String after) {
        return raw == null ? after : raw;
    }

    public static boolean isNullOrEmpty(String raw) {
        return raw == null || "".equals(raw);
    }

    public static String getCheck(RadioGroup rg, Activity activity) {
        if (rg.getCheckedRadioButtonId() == -1) {
            return null;
        } else {
            return (String) activity.findViewById(rg.getCheckedRadioButtonId()).getTag();
        }
    }

    /**
     * 获取应用versionCode
     */
    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
    }

    /**
     * 获取应用的versionName
     */
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static String getFileFormat(String fileName) {
        if (isNullOrEmpty(fileName)) {
            return null;
        }

        int point = fileName.lastIndexOf('.');
        return fileName.substring(point + 1);
    }

    /**
     * XX分钟/小时/天/月/年前
     *
     * @param cTime 传入的时间  格式  yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String createTime(String cTime) {
        String resultTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            long createTime = sdf.parse(cTime).getTime();
            long currentTime = System.currentTimeMillis();
            long time = (currentTime - createTime) / 1000 / 60;//得到相差的分钟

            if (time <= 60) {
                if (time < 1) {
                    resultTime = "刚刚";
                } else {
                    resultTime = time + "分钟前";
                }
            } else if (time <= 1440) {
                resultTime = (int) (time / 60) + "小时前";
            } else if (time <= 43920) {
                resultTime = (int) (time / 60 / 24) + "天前";
            } else if (time <= 525600) {
                resultTime = (int) (time / 60 / 24 / 30.5) + "月前";
            } else if (time > 525600) {
                resultTime = (int) (time / 60 / 24 / 30.5 / 12) + "年前";
            }
            return resultTime;
        } catch (ParseException e) {
        }
        return null;
    }

    public static Uri proAlbumBack(Context context, Intent data) {
        String path = null;
        Uri uri = data.getData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                DocumentsContract.isDocumentUri(context, uri)) {
            String wholeId = DocumentsContract.getDocumentId(uri);
            if (isExternalStorageDocument(uri)) {
                String[] split = wholeId.split(":");
                String type = split[0];
                if ("primary".equals(type)) {
                    path = Environment.getExternalStorageDirectory() + "/" + split[1];
                    // Environment.getExternalStorageDirectory(): /storage/emulated/0
                }
                // TODO: 2015-12-22 handle non-primary volumes
            } else if (isDownloadsDocument(uri)) {
                // content://com.android.providers.downloads.documents/document/1
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(wholeId));
                path = getPath(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                // content://com.android.providers.media.documents/document/image%3A11
                String id = wholeId.split(":")[1];
                path = getPath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        MediaStore.Images.Media._ID + "=?",
                        new String[]{id});
            }
        } else if ("content".equals(uri.getScheme())) {
            // action_pick && api<19
            // content://media/external/images/media/12
            if (isGooglePhotosUri(uri)) {
                path = uri.getLastPathSegment();
            }
            path = getPath(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // action_pick && api>=19
            // file:///storage/emulated/0/...
            path = uri.getPath();
        }
        return Uri.fromFile(new File(path));
    }

    /**
     * return the path or null
     *
     * @param context
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    private static String getPath(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA},
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
                cursor.close();
            }
        } finally {
        }
        return path;
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * 是否为数字或字母
     *
     * @param raw
     * @return
     */
    public static boolean isCharOrNum(String raw) {
        char[] array = raw.toCharArray();
        for (char c : array) {
            /*if (!((c >= '0' && c <= '9') || c == 'x' || c == 'X')){
                return false;
            }*/
            if (!(Character.isDigit(c) || Character.isLetter(c))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将整数不足十位十位补零
     *
     * @param raw
     * @return
     */
    public static String addZero(int raw) {
        return raw < 10 ? ("0" + raw) : String.valueOf(raw);
    }
}
