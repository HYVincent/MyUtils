package com.vincent.library.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vincent.library.R;

/**
 * @name MyUtils
 * @class name：com.vincent.library.view
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/1 0:17
 * @change
 * @chang time
 * @class describe
 */


public class HintMsgDialog extends Dialog{

    private ImageView ivIcon;
    private TextView tvMsg;
    private int imgId = 0;
    private String msg = "";

    public HintMsgDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout_hint_msg);
        //空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        ivIcon = findViewById(R.id.iv_icon);
        tvMsg = findViewById(R.id.tv_msg);
        tvMsg.setText(msg);
        if(imgId!=0){
            ivIcon.setImageDrawable(ContextCompat.getDrawable(getContext(),imgId));
            ivIcon.setVisibility(View.VISIBLE);
            android.widget.LinearLayout.LayoutParams lp=(android.widget.LinearLayout.LayoutParams)tvMsg.getLayoutParams();
            //设置自己需要的距离
            lp.leftMargin=10;
            tvMsg.setLayoutParams(lp);
        }else {
            ivIcon.setVisibility(View.GONE);
        }
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setMsg(String title) {
        msg = title;
    }



    public void setImg(int imgId){
        this.imgId = imgId;
    }

    public void show(long showTime){
        show();
        tvMsg.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        },showTime);
    }

}
