package com.vincent.example;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vincent.library.dialog.DialogUtil;
import com.vincent.library.dialog.LoadingDialog;
import com.vincent.library.toast.ToastUtils;

public class MainActivity extends AppCompatActivity {

    private Dialog loadingDialog;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x11:
                    Log.d(TAG, "handleMessage: close loading dialog start");
                    LoadingDialog.closeDialog();
                    ToastUtils.showMsgLong(MainActivity.this,"close");
                    Log.d(TAG, "handleMessage: close loading dialog end");
                    break;
            }
        }
    };
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingDialog = LoadingDialog.createLoadingDialog(MainActivity.this,"加载中..");
        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtil.hintTextDialog(MainActivity.this, "title", "this is msg", new DialogUtil.CommonActionListener() {
                    @Override
                    public void action() {
//                        ToastUtils.showMsgLong(MainActivity.this,"Toast Long");
                        ToastUtils.showMsgShort(MainActivity.this,"Toast Short");
                    }
                });
            }
        });
        findViewById(R.id.btn_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog.show();
                /*try {
                    Log.d(TAG, "onClick: sleep 3000ms");
                    Thread.sleep(3000);
                    Log.d(TAG, "onClick: send msg start");
                    Message message = Message.obtain();
                    message.what = 0x11;
                    handler.sendMessage(message);
                    Log.d(TAG, "onClick: send msg end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });
        findViewById(R.id.btn_msg_hint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MsgHintActivity.actionStart(MainActivity.this);
            }
        });
    }
}
