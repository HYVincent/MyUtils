package com.vincent.example.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.elvishew.xlog.XLog;
import com.vincent.example.R;
import com.vincent.example.base.BaseActivity;
import com.vincent.library.log.XLogs;

/**
 * @name MyUtils
 * @class name：com.vincent.example.ui.activity
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 17:06
 * @change
 * @chang time
 * @class describe
 */

public class XLogsActivity extends BaseActivity {

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,XLogsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xlog);
        setTitleText("XLogs");
        findViewById(R.id.btn_xlogs_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                XLogs.getLogger().i("test....");
//                XLogs.getLogger().d("test....");
//                XLogs.getLogger().w("test....");
//                XLogs.getLogger().e("test....");
//                XLogs.getLogger().v("test....");
                XLog.d("ffffffffffffffffffffffffffff");
            }
        });
        findViewById(R.id.btn_xlogs_test_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                XLogs.getLogger().i("test....");
//                XLogs.getLogger().d("test....");
//                XLogs.getLogger().w("test....");
//                XLogs.getLogger().e("test....");
//                XLogs.getLogger().v("test....");
                XLogs.setSwitchs(true);
                XLog.d("ffffffffffffffffffffffffffff");
            }
        });
        findViewById(R.id.btn_xlogs_test_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                XLogs.getLogger().i("test....");
//                XLogs.getLogger().d("test....");
//                XLogs.getLogger().w("test....");
//                XLogs.getLogger().e("test....");
//                XLogs.getLogger().v("test....");
                XLogs.setSwitchs(false);
                XLog.d("ffffffffffffffffffffffffffff");
            }
        });
    }
}
