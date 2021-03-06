package com.vincent.example.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.vincent.example.R;
import com.vincent.example.base.BaseActivity;
import com.vincent.library.log.XLogs;
import com.vincent.library.network.NetUtils;
import com.vincent.library.network.NetworkChangeListenerUtils;
import com.vincent.library.toast.ToastUtils;

import static com.vincent.library.network.NetUtils.NETWORN_2G;
import static com.vincent.library.network.NetUtils.NETWORN_3G;
import static com.vincent.library.network.NetUtils.NETWORN_4G;
import static com.vincent.library.network.NetUtils.NETWORN_MOBILE;
import static com.vincent.library.network.NetUtils.NETWORN_NONE;
import static com.vincent.library.network.NetUtils.NETWORN_WIFI;

/**
 * @name MyUtils
 * @class name：com.vincent.example.ui.activity
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/6 9:33
 * @change
 * @chang time
 * @class describe
 */

public class NetworkActivity extends BaseActivity {

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,NetworkActivity.class);
        activity.startActivity(intent);
    }

    private TextView tvResult;
    private String str = "当前网络类型为:";
    private String type = "";

    private NetworkChangeListenerUtils listenerUtils = new NetworkChangeListenerUtils(new NetworkChangeListenerUtils.NetworkChangeListener() {
        @Override
        public void noNetwork() {
            ToastUtils.showMsgLong("无网络");
        }

        @Override
        public void network2G() {
            ToastUtils.showMsgLong("2G");
        }

        @Override
        public void network3G() {
            ToastUtils.showMsgLong("3G");
        }

        @Override
        public void network4G() {
            ToastUtils.showMsgLong("4G");
        }

        @Override
        public void networkMoble() {
            ToastUtils.showMsgLong("Moble");
        }

        @Override
        public void WIFI() {
            ToastUtils.showMsgLong("WIFI");
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        setTitleText("网络相关");
        tvResult = (TextView) findViewById(R.id.tv_result);
        NetworkChangeListenerUtils.register(this,listenerUtils);
        findViewById(R.id.btn_check_network_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (NetUtils.getNetworkState()){
                    case NETWORN_WIFI:
                        type = "wifi";
                        break;
                    case NETWORN_2G:
                        type = "2G";
                        break;
                    case NETWORN_3G:
                        type = "3G";
                        break;
                    case NETWORN_4G:
                        type = "4G";
                        break;
                    case NETWORN_MOBILE:
                        type = "mobile";
                        break;
                    case NETWORN_NONE:
                        type = "无网络连接";
                        break;
                }
//                XLogs.getLogger().d(str+type);
                ToastUtils.showMsgLong(str+type);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkChangeListenerUtils.unRegister(this,listenerUtils);
    }
}
