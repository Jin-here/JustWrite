package com.jrq365.jeffgxy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by caojin on 2016/1/29.
 */
public class SharePop {

    private final String WX_APP_ID = "wx264520611fecb7cd";
    private final String WX_APP_SECRET = "e222fbd74cf87a75534ecffd21a5a1e6";

    private final String QQ_APP_ID = "1104811677";
    private final String QQ_APP_SECRET = "u2VY3xPXHbCFr5f9";

    private Activity mActivity;
    private IWXAPI api;
    private Tencent mTencent;

    public SharePop(Activity mActivity, Tencent mTencent){
        this.mActivity = mActivity;
        this.mTencent = mTencent;
        api = WXAPIFactory.createWXAPI(mActivity, WX_APP_ID, true);
        api.registerApp(WX_APP_ID);
    }

    public void show(View v){
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.share_pop, null);
        TextView btn_wxhy = (TextView) view.findViewById(R.id.btn_wxhy);
        TextView btn_wxpy = (TextView) view.findViewById(R.id.btn_wxpy);
        TextView btn_qqhy = (TextView) view.findViewById(R.id.btn_qqhy);
        TextView btn_qqkj = (TextView) view.findViewById(R.id.btn_qqkj);
        btn_wxhy.setOnClickListener(listener);
        btn_wxpy.setOnClickListener(listener);
        btn_qqhy.setOnClickListener(listener);
        btn_qqkj.setOnClickListener(listener);

        PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        popupWindow.setOutsideTouchable(true);

        WindowManager windowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        int height = windowManager.getDefaultDisplay().getHeight();
        // 设置好参数之后再show
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, height - popupWindow.getHeight());

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                // 微信好友
                case R.id.btn_wxhy:
                    shareWebToWX(false);
                    break;
                // 微信朋友圈
                case R.id.btn_wxpy:
                    shareWebToWX(true);
                    break;
                // QQ好友
                case R.id.btn_qqhy:
                    shareToQQ();
                    break;
                // QQ空间
                case R.id.btn_qqkj:
                    shareToQQZone();
                    break;
            }
        }
    };

    /**
     * 网址参考：
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419317340&token=eef463de3eb89ff872ff5652734d0d62dda488b8&lang=zh_CN
     * @param isToPX
     */
    private void shareWebToWX(boolean isToPX){
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://www.baidu.com/";

        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "网页标题";
        msg.description = "网页描述";
        Bitmap thumb = BitmapFactory.decodeResource(mActivity.getResources(), R.mipmap.ic_launcher);
        msg.thumbData = bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = isToPX ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

        api.sendReq(req);
    }

    /**
     * 参考网址：
     * http://wiki.connect.qq.com/%E5%88%86%E4%BA%AB%E6%B6%88%E6%81%AF%E5%88%B0qq%EF%BC%88%E5%AE%9A%E5%90%91%E5%88%86%E4%BA%AB%EF%BC%89_android_sdk
     */
    public void shareToQQ()
    {
        final Bundle bundle = new Bundle();
        //这条分享消息被好友点击后的跳转URL。
        bundle.putString("targetUrl", "http://connect.qq.com/");
        //分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_SUMMARY不能全为空，最少必须有一个是有值的。
        bundle.putString("title", "我在测试");
        //分享的图片URL
        bundle.putString("imageUrl", "http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg");
        //分享的消息摘要，最长50个字
        bundle.putString("summary", "测试");
        //手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
        bundle.putString("appName", "??我在测试");
        //标识该消息的来源应用，值为应用名称+AppId。
        bundle.putString("appSource", "365橙融网" + QQ_APP_ID);

        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if (null != mTencent) {
                    mTencent.shareToQQ(mActivity, bundle, new IUiListener() {
                        @Override
                        public void onComplete(Object o) {

                        }

                        @Override
                        public void onError(UiError uiError) {

                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                }
            }
        });

    }

    /**
     * 参考官方demo
     */
    private void shareToQQZone(){
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "这不是测试");
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "测试");
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://www.baidu.com");
        ArrayList<String> imageList = new ArrayList<>();
        imageList.add("http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg");
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageList);

        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if (null != mTencent) {
                    mTencent.shareToQzone(mActivity, params, new IUiListener() {
                        @Override
                        public void onComplete(Object o) {

                        }

                        @Override
                        public void onError(UiError uiError) {

                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                }
            }
        });
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
