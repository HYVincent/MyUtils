package com.vincent.example.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;

import com.vincent.example.R;
import com.vincent.example.base.BaseActivity;
import com.vincent.library.toast.ToastUtils2;
import com.vincent.library.view.HintMsgDialog;


/**
 * @name MyUtils
 * @class name：com.vincent.example
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/8/31 23:27
 * @change
 * @chang time
 * @class describe
 */


public class MsgHintActivity extends BaseActivity {

    private HintMsgDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_hint);
        setTitleText("提示消息");
        dialog = new HintMsgDialog(this);
        dialog.setMsg("click");
        dialog.setImg(R.mipmap.ui_icon_notify_done);
        findViewById(R.id.btn_msg_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.showSuccess("发送成功");
            }
        });
        findViewById(R.id.btn_msg_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.showErrorMsg("发送失败");
            }
        });
        findViewById(R.id.btn_msg_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.showInfoMsg("请勿重复点击");
            }
        });
        findViewById(R.id.btn_msg_toast_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils2.showSuccessMsg("登录成功");
            }
        });
        findViewById(R.id.btn_msg_toast_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils2.showErrorMsg("登陆失败");
            }
        });
        findViewById(R.id.btn_msg_toast_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils2.showInfoMsg("正在登录，请勿重复点击");
            }
        });
        findViewById(R.id.btn_msg_toast_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils2.onlyImg(MsgHintActivity.this,R.mipmap.ui_icon_notify_done);
            }
        });
        findViewById(R.id.btn_msg_toast_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils2.showMsg(MsgHintActivity.this,"哈哈",R.mipmap.ui_icon_notify_done, Gravity.CENTER);
            }
        });
    }

    public static void actionStart(Activity activity)
    {
        Intent intent = new Intent(activity,MsgHintActivity.class);
        activity.startActivity(intent);
    }
}
