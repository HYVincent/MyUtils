package com.vincent.example.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.vincent.example.R;
import com.vincent.example.base.BaseActivity;
import com.vincent.library.qrcode.activity.CaptureActivity;
import com.vincent.library.qrcode.encoding.EncodingUtils;
import com.vincent.library.toast.ToastUtils;
import com.vincent.library.util.PermissionUtils;
import com.vincent.library.util.ScreenUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


/**
 * @name MyUtils
 * @class name：com.vincent.example.ui.activity
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 13:54
 * @change
 * @chang time
 * @class describe
 */

public class CameraAboutActivity extends BaseActivity {

    private static final int QR_CODE = 0x11;
    private static final String TAG = CameraAboutActivity.class.getSimpleName();
    private ImageView ivQrCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_camera);
        setTitleText("照相机相关");
        ivQrCode = (ImageView)findViewById(R.id.iv_qr_code);
        findViewById(R.id.btn_qr_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScan();
            }
        });
        findViewById(R.id.btn_create_qr_code_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = EncodingUtils.createQRCode("我是二维码的内容",
                        ScreenUtils.getScreenWidth(CameraAboutActivity.this) / 2,
                        ScreenUtils.getScreenWidth(CameraAboutActivity.this) / 2, null);
                ivQrCode.setImageBitmap(bitmap);
            }
        });
        findViewById(R.id.btn_create_qr_code_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = EncodingUtils.createQRCode("我是二维码的内容",
                        ScreenUtils.getScreenWidth(CameraAboutActivity.this) / 2,
                        ScreenUtils.getScreenWidth(CameraAboutActivity.this) / 2, null);

                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                Bitmap qrCode = EncodingUtils.addLogo(bitmap,bitmap1);

                ivQrCode.setImageBitmap(qrCode);
            }
        });
    }

    private void openScan() {
        PermissionUtils.getRxPermissions(CameraAboutActivity.this).request(
                Manifest.permission.CAMERA)//权限名称，多个权限之间逗号分隔开
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        Log.e(TAG, "{accept}granted=" + granted);//执行顺序——1【多个权限的情况，只有所有的权限均允许的情况下granted==true】
                        if (granted) { // 在android 6.0之前会默认返回true
                            CaptureActivity.actionStart(CameraAboutActivity.this,QR_CODE);
                        } else {
                            // 未获取权限
                            showMsg(0,"扫描档口信息需要照相机权限");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
//                        Log.e(TAG, "{accept}");//可能是授权异常的情况下的处理
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
//                        Log.e(TAG, "{run}");//执行顺序——2
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == QR_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                System.out.println("RESULT_OK");
                Bundle bundle = data.getExtras();
                String result = bundle.getString("result");
                Log.d(TAG, "onActivityResult: "+result);
                String utf8Result = "";
                try {
                    utf8Result = URLDecoder.decode(result, "UTF-8");
                    if (TextUtils.isEmpty(utf8Result)) {
                        Log.d(TAG, "onActivityResult: resule is null");
                    } else {
                        ToastUtils.showMsgLong(utf8Result);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,CameraAboutActivity.class);
        activity.startActivity(intent);
    }

}
