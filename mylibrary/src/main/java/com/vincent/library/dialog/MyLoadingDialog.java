package com.vincent.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.vincent.library.R;

/**
 * @name MyUtils
 * @class name：com.vincent.library.dialog
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/18 11:29
 * @change
 * @chang time
 * @class describe
 */


public class MyLoadingDialog extends Dialog {

    private Context mContexet;
    private String msg;
    private TextView tvMsg;

    public MyLoadingDialog(@NonNull Context context) {
        super(context, R.style.MyLoadingDialogStyleIsWhite);
        this.mContexet = context;
    }

    public MyLoadingDialog(Context context,int stypeId)
    {
        super(context,stypeId);
        this.mContexet = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        setContentView(R.layout.dialog_loading_3);
        tvMsg = findViewById(R.id.loading_dialog_tv_msg_3);
        if(!TextUtils.isEmpty(msg)){
            tvMsg.setText(msg);
        }
    }

    public void shows(){
       show();
    }

    public void shows(String msg)
    {
        if(tvMsg!=null){
            tvMsg.setText(msg);
        }
        show();
    }

}
