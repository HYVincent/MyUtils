package com.vincent.library.bluetooth.ota;

/**
 * Created by LiuLei on 2017/6/7.
 */

public interface OtaListener {
    void onWrite();

    void onChange(byte[] data);
}
