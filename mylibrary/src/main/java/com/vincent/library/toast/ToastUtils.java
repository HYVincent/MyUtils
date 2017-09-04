package com.vincent.library.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vincent.library.R;
import com.vincent.library.base.MyLibrary;

/**
 * @name MyUtils
 * @class name：com.vincent.library.toast
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/8/31 15:24
 * @change
 * @chang time
 * @class describe
 */

public class ToastUtils {

    private static Toast mToast;
    private static String msgContent;
    private static TextView textView;

    private static final long DEFAULT_SHOW_TIME = 2000L;//默认显示时间

    public static Toast createToast(CharSequence text, int duration) {
        View v = LayoutInflater.from(MyLibrary.getContext()).inflate(R.layout.eplay_toast, null);
        textView =  v.findViewById(R.id.textView1);
        mToast = new Toast(MyLibrary.getContext());
        textView.setText(text);
        mToast.setDuration(duration);
        mToast.setGravity(Gravity.BOTTOM,0,60);
        mToast.setView(v);
        return mToast;
    }

    private static void resetMsg(String tostMsg){
        if(textView!=null){
            textView.setText(tostMsg);
        }
    }

    public static Toast makeText(CharSequence text, int duration) {
        if(mToast!=null){
            resetMsg(text.toString());
        }
        return  ToastUtils.createToast(text,duration);
    }


    public static void showMsgLong(String msg){
        if(mToast!=null){
            resetMsg(msg);
        }else {
            ToastUtils.createToast(msg, Toast.LENGTH_LONG);
        }
        show();
    }

    /**
     * 自定义显示时间
     * @param context
     * @param msg
     * @param time
     */
    public static void showMsgTime(Context context, String msg, final long time){
        if(mToast!=null){
            resetMsg(msg);
        }else {
            ToastUtils.createToast(msg, Toast.LENGTH_SHORT);
        }
        mToast.show();
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mToast.cancel();
            }
        },time);
    }

    /**
     * 显示位置
     * @param context
     * @param msg
     */
    public static void showMsgShort(Context context,String msg){
        if(mToast!=null){
            resetMsg(msg);
        }else {
            ToastUtils.createToast(msg, Toast.LENGTH_SHORT);
        }
        show();
    }

    /**
     * 显示默认时间
     */
    private static void show() {
        if (mToast != null) {
            mToast.show();
            textView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mToast.cancel();
                    mToast.setGravity(Gravity.BOTTOM,0,120);
                }
            },DEFAULT_SHOW_TIME);
        }
    }

    /**
     * 设置显示的位置
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
            show();
        }
    }
}
