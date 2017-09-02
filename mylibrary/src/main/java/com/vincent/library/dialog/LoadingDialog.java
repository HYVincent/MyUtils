package com.vincent.library.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class LoadingDialog {

    private static Dialog dialog;
    private static final String TAG = LoadingDialog.class.getSimpleName();
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
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_loading_1, null);// 得到加载view;
        LinearLayout layout =  view.findViewById(R.id.dialog_loading_view_1);// 加载布局
        tvMsg = view.findViewById(R.id.loading_dialog_tv_msg_1);
        if(dialog == null){
            dialog = new Dialog(activity ,R.style.MyLoadingDialogStyleIsBack);// 创建自定义样式dialog
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

    /**.
     * @param activity
     * @param msg
     */
    public static void show(Activity activity,String msg){
        createLoadingDialog(activity);
        setMsg(msg);
        dialog.show();
    }

    /**
     * 显示默认的消息加载中不修改..
     * @param activity
     */
    public static void show(Activity activity){
        createLoadingDialog(activity).show();
    }

    public static void showNoText(Activity activity){
        createLoadingDialog(activity);
        tvMsg.setVisibility(View.GONE);
        dialog.show();
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
