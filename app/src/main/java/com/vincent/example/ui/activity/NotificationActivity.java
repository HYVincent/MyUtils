package com.vincent.example.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vincent.example.R;
import com.vincent.example.base.BaseActivity;
import com.vincent.library.notification.NotificationUtils;

/**
 * @name MyUtils
 * @class name：com.vincent.example.ui.activity
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 19:01
 * @change
 * @chang time
 * @class describe
 */

public class NotificationActivity extends BaseActivity {


    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,NotificationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setTitleText("通知");
        findViewById(R.id.btn_notification_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationUtils.sendNotification(NotificationActivity.this,"com.vincent.example.ui.activity.MainActivity",
                        R.mipmap.ic_launcher,"我是消息的Title","我是消息的内容");
            }
        });
        findViewById(R.id.btn_notification_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationUtils.sendSystemMsg(NotificationActivity.this,MainActivity.class,"title","啊啊啊啊啊");
            }
        });

        findViewById(R.id.btn_notification_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationUtils.showCzNotify(NotificationActivity.this,R.mipmap.ui_icon_notify_done,MainActivity.class);
            }
        });

    }

}
