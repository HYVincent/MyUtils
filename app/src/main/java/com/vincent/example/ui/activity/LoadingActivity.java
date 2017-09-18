package com.vincent.example.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.vincent.example.R;
import com.vincent.example.base.BaseActivity;
import com.vincent.library.dialog.LoadingDialog;
import com.vincent.library.dialog.LoadingDialog2;
import com.vincent.library.dialog.LoadingDialog3;
import com.vincent.library.dialog.LoadingDialog4;
import com.vincent.library.dialog.LoadingDialog5;
import com.vincent.library.dialog.MyLoadingDialog;

/**
 * @name MyUtils
 * @class name：com.vincent.example.ui.activity
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 14:42
 * @change
 * @chang time
 * @class describe
 */

public class LoadingActivity extends BaseActivity {

    private static final String TAG = LoadingActivity.class.getSimpleName();

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity, LoadingActivity.class);
        activity.startActivity(intent);
    }

    private MyLoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        dialog = new MyLoadingDialog(this);
        setTitleText("Loading Dialog ..");
        findViewById(R.id.btn_loading_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog.show(LoadingActivity.this,"正在登陆..");
            }
        });
        findViewById(R.id.btn_loading_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog2.show(LoadingActivity.this);
            }
        });
        findViewById(R.id.btn_loading_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog.show(LoadingActivity.this,"正在登陆..");
            }
        });
        findViewById(R.id.btn_loading_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog.showNoText(LoadingActivity.this);
            }
        });
        findViewById(R.id.btn_loading_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog3.show(LoadingActivity.this,"加载中..");
            }
        });
        findViewById(R.id.btn_loading_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog3.showNoText(LoadingActivity.this);
            }
        });

        findViewById(R.id.btn_loading_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog4.show(LoadingActivity.this);
            }
        });
        findViewById(R.id.btn_loading_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog5.show(LoadingActivity.this);
            }
        });
        findViewById(R.id.btn_loading_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.shows("加载中..");
            }
        });

    }
}
