package com.vincent.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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


public class MsgHintActivity extends AppCompatActivity {

    private HintMsgDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_hint);
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
    }

    public static void actionStart(Activity activity)
    {
        Intent intent = new Intent(activity,MsgHintActivity.class);
        activity.startActivity(intent);
    }
}
