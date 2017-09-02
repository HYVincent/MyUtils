package com.vincent.example.ui.activity;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.vincent.example.R;
import com.vincent.example.base.BaseActivity;
import com.vincent.library.dialog.DialogUtil;
import com.vincent.library.dialog.LoadingDialog;
import com.vincent.library.toast.ToastUtils;

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

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hintTitle();
        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtilsActivity.actionStart(MainActivity.this);
            }
        });
        findViewById(R.id.btn_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingActivity.actionStart(MainActivity.this);
            }
        });
        findViewById(R.id.btn_msg_hint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MsgHintActivity.actionStart(MainActivity.this);
            }
        });
        findViewById(R.id.btn_qr_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraAboutActivity.actionStart(MainActivity.this);
            }
        });
        findViewById(R.id.btn_xlogs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XLogsActivity.actionStart(MainActivity.this);
            }
        });
        findViewById(R.id.btn_go_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentActivity.actionStart(MainActivity.this);
            }
        });
    }
}
