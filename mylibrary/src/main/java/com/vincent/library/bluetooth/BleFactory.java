package com.vincent.library.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.vincent.library.bluetooth.BleVO.BleDevice;


/**
 * Created by LiuLei on 2017/5/3.
 * 蓝牙工厂类
 */

public class BleFactory<T extends BleDevice>{
    private Context mContext;

    public BleFactory(Context context){
        mContext = context;
    }

    public T create(BleManager<T> bleManager,BluetoothDevice device) throws Exception{
        return bleManager.getBleDevice(device);
    }

    public Context getContext() {
        return mContext;
    }
}
