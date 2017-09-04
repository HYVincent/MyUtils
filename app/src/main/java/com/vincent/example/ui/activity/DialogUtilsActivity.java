package com.vincent.example.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.vincent.example.R;
import com.vincent.example.base.BaseActivity;
import com.vincent.library.dialog.DialogUtil;
import com.vincent.library.listener.CommonItemOnClickListener;
import com.vincent.library.toast.ToastUtils;
import com.vincent.library.toast.ToastUtils2;

import java.util.ArrayList;
import java.util.List;

/**
 * @name MyUtils
 * @class name：com.vincent.example.ui.activity
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 11:40
 * @change
 * @chang time
 * @class describe
 */

public class DialogUtilsActivity extends BaseActivity{

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity, DialogUtilsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_utils);
        setTitleText("dialog");
        findViewById(R.id.btn_dialog_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtil.hintTextDialog(DialogUtilsActivity.this, "Title", "This is msg content", new DialogUtil.CommonActionListener() {
                    @Override
                    public void action() {
                        ToastUtils2.showSuccessMsg("点击成功");
                    }
                });
            }
        });

        findViewById(R.id.btn_dialog_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtil.hintTextDialog(DialogUtilsActivity.this, "确定取消吗?", new DialogUtil.CommonActionListener() {
                    @Override
                    public void action() {
                        ToastUtils2.showSuccessMsg("取消成功");
                    }
                });
            }
        });

        findViewById(R.id.btn_dialog_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<String> data = new ArrayList<>();
                for (int i=0;i<100;i++){
                    data.add("item "+String.valueOf(i));
                }
                DialogUtil.showBottomList( DialogUtilsActivity.this,data,"",3,true, new CommonItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ToastUtils.showMsgLong(data.get(position));
                    }
                });
            }
        });

        findViewById(R.id.btn_dialog_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<String> data = new ArrayList<>();
                for (int i=0;i<100;i++){
                    data.add("item "+String.valueOf(i));
                }
                DialogUtil.showBottomList( DialogUtilsActivity.this,data,"请选择",3,true, new CommonItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ToastUtils.showMsgLong(data.get(position));
                    }
                });
            }
        });

        findViewById(R.id.btn_dialog_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showMsgTime(DialogUtilsActivity.this,"自定义显示时间",100);
            }
        });
        findViewById(R.id.btn_dialog_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.createToast("自定义显示位置", Toast.LENGTH_LONG);
                ToastUtils.setGravity(Gravity.CENTER,0,0);
            }
        });
    }
}
