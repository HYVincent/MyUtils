package com.vincent.library.network;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.vincent.library.log.XLogs;

/**
 * @name MyUtils
 * @class name：com.vincent.library.network
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/6 9:46
 * @change
 * @chang time
 * @class describe
 */

public class NetworkChangeListenerUtils  extends BroadcastReceiver {
    //在onCreate中注册
    /*NetworkChangeListenerUtils receiver = new NetworkChangeListenerUtils();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    registerReceiver(receiver,intentFilter);*/

    /*unregisterReceiver(receiver);*/ //解注册

    private static final String TAG = NetworkChangeListenerUtils.class.getSimpleName();

    private NetworkChangeListener listener;

    public NetworkChangeListenerUtils(NetworkChangeListener networkChangeListener){
        this.listener = networkChangeListener;
    };

    public interface NetworkChangeListener{

        void noNetwork();

        void network2G();

        void network3G();

        void network4G();

        void networkMoble();

        void WIFI();
    }

    public static void register(Activity activity,BroadcastReceiver broadcastReceiver){
        if(activity!=null){
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            activity.registerReceiver(broadcastReceiver, intentFilter);
        }else {
            XLogs.getLogger().e("register faile");
        }
    }

    public static void unRegister(Activity activity,BroadcastReceiver broadcastReceiver){
        if(activity!=null){
            activity.unregisterReceiver(broadcastReceiver);
        }else {
            Log.d(TAG, "unRegister: activity is null..");
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //**判断当前的网络连接状态是否可用*/
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //如果当前没有网络
        if (null == connManager)
        {
//            ToastUtils.showMsgLong("当前无网络");
            listener.noNetwork();
        }
        //获取当前网络类型，如果为空，返回无网络
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
//            ToastUtils.showMsgLong("当前没有网络");
            listener.noNetwork();
        }
        // 判断是不是连接的是不是wifi
        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
//                    ToastUtils2.showMsg("当前网络为:WIFI");
                    listener.WIFI();
                }
        }

        // 如果不是wifi，则判断当前连接的是运营商的哪种网络2g、3g、4g等
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (null != networkInfo) {
            NetworkInfo.State state = networkInfo.getState();
            String strSubTypeName = networkInfo.getSubtypeName();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    switch (activeNetInfo.getSubtype()) {
                        //如果是2g类型
                        case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
//                            ToastUtils2.showMsg("当前网络为:2G");
                            listener.network2G();
                            break;
                        //如果是3g类型
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
//                            ToastUtils2.showMsg("当前网络为:3G");
                            listener.network3G();
                            break;
                        //如果是4g类型
                        case TelephonyManager.NETWORK_TYPE_LTE:
//                            ToastUtils2.showMsg("当前网络为:4G");
                            listener.network4G();
                            break;
                        default:
                            //中国移动 联通 电信 三种3G制式
                            if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
//                                ToastUtils2.showMsg("当前网络为:3G");
                                listener.network3G();
                            } else {
                                listener.networkMoble();
//                                ToastUtils2.showMsg("当前网络为:MOBILE");
                            }
                    }
                }
        }
    }

}
