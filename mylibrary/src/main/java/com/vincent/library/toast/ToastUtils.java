package com.vincent.library.toast;

import android.content.Context;
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

    private Toast mToast;

    private ToastUtils(CharSequence text, int duration) {
        View v = LayoutInflater.from(MyLibrary.getContext()).inflate(R.layout.eplay_toast, null);
        TextView textView =  v.findViewById(R.id.textView1);
        textView.setText(text);
        mToast = new Toast(MyLibrary.getContext());
        mToast.setDuration(duration);
        mToast.setView(v);
    }

    public static ToastUtils makeText(Context context, CharSequence text, int duration) {
        return new ToastUtils(text, duration);
    }

    public static void showMsgLong(Context context,String msg){
         new ToastUtils(msg, Toast.LENGTH_LONG).show();
    }

    public static void showMsgShort(Context context,String msg){
        new ToastUtils(msg, Toast.LENGTH_SHORT).show();
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }
    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }

}
