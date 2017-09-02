package com.vincent.library.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tbruyelle.rxpermissions2.RxPermissionsFragment;

/**
 * @name MyUtils
 * @class name：com.vincent.library.util
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 11:45
 * @change
 * @chang time
 * @class describe
 */

public class PermissionUtils {


    /**
     * getRxPermissions().request(
     Manifest.permission.CAMERA)//权限名称，多个权限之间逗号分隔开
     .subscribe(new Consumer<Boolean>() {
    @Override
    public void accept(Boolean granted) throws Exception {
    Log.e(TAG, "{accept}granted=" + granted);//执行顺序——1【多个权限的情况，只有所有的权限均允许的情况下granted==true】
    if (granted) { // 在android 6.0之前会默认返回true
    // 已经获取权限
    //                            Toast.makeText(MapViewActivity.this, "已经获取权限", Toast.LENGTH_SHORT).show();
    startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 0);//启动扫描 我日你大爷的，换成QRSCANO_CODE就没有结果，直接写0就正常了，操
    } else {
    // 未获取权限
    showMsg(0,"扫描门牌号需要照相机权限");
    }
    }
    }, new Consumer<Throwable>() {
    @Override
    public void accept(Throwable throwable) throws Exception {
    Log.e(TAG, "{accept}");//可能是授权异常的情况下的处理
    }
    }, new Action() {
    @Override
    public void run() throws Exception {
    Log.e(TAG, "{run}");//执行顺序——2
    }
    });
     */



    private static RxPermissions rxPermissions = null;
    private static RxPermissionsFragment rxPermissionsFragment = null;
    private static PermissionUtils permissionUtils;

    private PermissionUtils(Activity activity)
    {
        if(rxPermissions == null){
            rxPermissions = new RxPermissions(activity);
        }
        if(rxPermissionsFragment == null)
        {
            rxPermissionsFragment = new RxPermissionsFragment();
        }
    }
    public static RxPermissions getRxPermissions(Activity activity) {
        if(permissionUtils == null){
            permissionUtils = new PermissionUtils(activity);
        }
        return rxPermissions;
    }

    public static RxPermissionsFragment getRxPermissionsFragment() {
        return rxPermissionsFragment;
    }
}
