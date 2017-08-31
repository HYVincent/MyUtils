package com.vincent.library.dialog;

import android.app.Dialog;
import android.content.Context;
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

    private static Dialog loadingDialog;

    /**
     * 加载动画..
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout =  v.findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView =  v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息
        loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
//        loadingDialog.show();
        return loadingDialog;
    }

    public static void show(){
        if(loadingDialog!=null){
            loadingDialog.show();
        }
    }

    /**
     * 关闭dialog
     */
    public static void closeDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
