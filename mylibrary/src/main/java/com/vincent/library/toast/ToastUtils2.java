package com.vincent.library.toast;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vincent.library.R;
import com.vincent.library.base.MyLibrary;

/**
 * @name MyUtils
 * @class name：com.vincent.library.toast
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/1 9:11
 * @change
 * @chang time
 * @class describe
 */

public class ToastUtils2 {

    private static Toast mToast;
    private static ImageView ivImg;
    private static TextView tvMsg;
    private  static String msgContent = "";
    private static int imgIds = 0;

    private ToastUtils2(CharSequence text, int duration) {
        View v = LayoutInflater.from(MyLibrary.getContext()).inflate(R.layout.toast_layout_custom, null);
        ivImg = v.findViewById(R.id.toast_iv_icon);
        tvMsg = v.findViewById(R.id.toast_tv_msg);
        mToast = new Toast(MyLibrary.getContext());
        mToast.setDuration(duration);
        tvMsg.setText(text);
        if(imgIds != 0){
            ivImg.setImageDrawable(ContextCompat.getDrawable(MyLibrary.getContext(),imgIds));
            ivImg.setVisibility(View.VISIBLE);
        }else {
            ivImg.setVisibility(View.GONE);
        }
        mToast.setView(v);
    }


    public static void showMsg(String msg){
        new ToastUtils2(msg,Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showMsg(String msg,int imgId){
        if(mToast!=null){
            resetMsg(msg);
            tvMsg.setVisibility(View.VISIBLE);
        }else {
            new ToastUtils2(msg,Toast.LENGTH_SHORT);
        }
        if(ivImg!=null&&imgId!=0){
            ivImg.setImageDrawable(ContextCompat.getDrawable(MyLibrary.getContext(),imgId));
            ivImg.setVisibility(View.VISIBLE);
        }
        mToast.show();
    }

    public static void onlyImg(Context context,int imgId){
        if(mToast!=null){
            ivImg.setImageDrawable(ContextCompat.getDrawable(context,imgId));
            ivImg.setVisibility(View.VISIBLE);
        }else {
            new ToastUtils2("",Toast.LENGTH_SHORT);
        }
        tvMsg.setVisibility(View.GONE);
        if(ivImg!=null&&imgId!=0){
            ivImg.setImageDrawable(ContextCompat.getDrawable(context,imgId));
            ivImg.setVisibility(View.VISIBLE);
        }
        mToast.show();
    }

    /**
     * 设置显示的位置
     * @param gravity
     */
   public static void showMsg(Context context,String msg,int imgId,int gravity){
        if(mToast == null){
            new ToastUtils2(msg,Toast.LENGTH_LONG);
        }
       mToast.setGravity(gravity,0,0);
       showMsg(msg,imgId);
   }


    public static void resetMsg(String msg){
        msgContent = msg;
    }

    public static void resetImg(int imgId){
        imgIds = imgId;
    }

    /**
     * 提示成功或已完成消息类型
     * @param msgContent
     */
    public static void showSuccessMsg(String msgContent){
        showMsg(msgContent,R.mipmap.ui_icon_notify_done);
    }

    /**
     * 提示错误消息类型
     * @param msgContent
     */
    public static void showErrorMsg(String msgContent){
        showMsg(msgContent,R.mipmap.qmui_icon_notify_error);
    }

    /**
     * 提示展示消息类型
     * @param msgContent
     */
    public static void showInfoMsg(String msgContent){
        showMsg(msgContent,R.mipmap.qmui_icon_notify_info);
    }

}
