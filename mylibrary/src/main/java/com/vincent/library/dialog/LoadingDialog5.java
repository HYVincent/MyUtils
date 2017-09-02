package com.vincent.library.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincent.library.R;

/**
 * @name MyUtils
 * @class name：com.vincent.library.loading
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/8/31 14:48
 * @change
 * @chang time
 * @class describe
 */

public class LoadingDialog5 {

    private static Dialog dialog;
    private static final String TAG = LoadingDialog5.class.getSimpleName();
    private static String msgs = "";
    private static TextView tvMsg;

    private static void setMsg(String msg) {
        if(tvMsg!=null){
            tvMsg.setText(msg);
        }
    }

    /**
     * 加载动画..
     * @return
     */
    private static Dialog createLoadingDialog(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_loading_5, null);// 得到加载view;
        LinearLayout layout =  view.findViewById(R.id.dialog_loading_view_5);// 加载布局
        ImageView imageView = view.findViewById(R.id.img_loading);
        tvMsg = view.findViewById(R.id.dialog_loading_tv_5);
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotate_animation);
        // 使用ImageView显示动画
        imageView.startAnimation(hyperspaceJumpAnimation);
        if(dialog == null){
            dialog = new Dialog(activity ,R.style.MyLoadingDialogStyleIsWhite);// 创建自定义样式dialog
        }
        dialog.setCancelable(true); // 是否可以按“返回键”消失
        dialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        dialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        return dialog;
    }


    /**
     * 显示默认的消息加载中不修改..
     * @param activity
     */
    public static void show(Activity activity){
        createLoadingDialog(activity).show();
    }

    /**
     * 关闭dialog
     */
    public static void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog.cancel();
        }
    }
}
