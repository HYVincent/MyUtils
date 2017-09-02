package com.vincent.example.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vincent.example.R;
import com.vincent.example.listener.CommonActionListener;
import com.vincent.library.apkDownUtils.DownloadService;
import com.vincent.library.dialog.LoadingDialog;
import com.vincent.library.toast.ToastUtils2;
import com.vincent.library.util.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description ：基类，所有的Activity都应该继承此类
 * project name：CClud2
 * author : Vincent
 * creation date: 2017/5/13 13:25
 *
 * @version 1.0
 */

public class BaseActivity extends AppCompatActivity implements BackKeyFragmentListener{

    private static final String TAG = BaseActivity.class.getSimpleName();

    private static List<Activity> activityList = new ArrayList<>();
    private InputMethodManager inputMethodManager;

    //以下为APP下载更新所需
    public DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

    };

    private ViewStub viewStubNetworkExecption;//网络连接异常
    private ViewStub viewStubDataLoadingFail;//数据加载失败
    private ViewStub viewStubNoData;//没有数据

    private LinearLayout rootLlleft,rootLlRight;
    private TextView rootTvText,rootTvRight;
    private ImageView rootIvLeft;

    private LinearLayout rootView;
    private RelativeLayout rlTitle;

    //加个标志，因为动态布局只能被加载一次
    private boolean isLoadingA = false;
    private boolean isLoadingB = false;
    private boolean isLoadingC = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>21){
            StatusBarUtil.setColor(BaseActivity.this, ContextCompat.getColor(BaseActivity.this, R.color.color_blue_4562e9),0);
        }
        activityList.add(this);
        startDownService();
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);//输入法
        IntentFilter intentFilter = new IntentFilter();//网络状态广播注册
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkStatus,intentFilter);
    }

    public RxPermissions getRxPermissions() {
        return PermissionUtils.getRxPermissions(this);
    }

    /**
     * 此方法在网络异常的时候覆盖本Activity
     * @param layoutResID 布局id
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_root);
        if(rootView==null){
            rootView = (LinearLayout) this.findViewById(R.id.ll_root);
        }
        initView(rootView);
        try {
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            rootView.addView(view, lp);
        } catch (Exception e) {
            Log.e(TAG, " add view error :" + e);
        }
    }


    private void initView(View view) {
        rootLlleft = (LinearLayout)view.findViewById(R.id.root_ll_left);
        rootLlleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rootLlRight = (LinearLayout)view.findViewById(R.id.root_ll_right);
        rootTvText = (TextView)view.findViewById(R.id.root_title_text);
        rootIvLeft = (ImageView) view.findViewById(R.id.root_iv_left);
        rootTvRight = (TextView)view.findViewById(R.id.root_tv_right);

        rlTitle = (RelativeLayout)view.findViewById(R.id.root_rl_title);

        //注意 ViewStub的inflate方法只能调用一次，否则就会异常
        viewStubNetworkExecption = (ViewStub)view.findViewById(R.id.common_stub_import_network_exception);
        viewStubDataLoadingFail = (ViewStub)view.findViewById(R.id.common_stub_import_loading_data_fail);
        viewStubNoData = (ViewStub)view.findViewById(R.id.common_stub_import_no_data);

    }

    public void hintTitle(){
        if(rlTitle!=null){
            rlTitle.setVisibility(View.GONE);
        }
    }

    public void hintLeft(){
        if(rootLlleft!=null){
            rootIvLeft.setVisibility(View.GONE);
        }
    }

    /**
     * 设置title文本
     * @param titleText
     */
    public void setTitleText(String titleText){
        if(rootTvText == null){
            return;
        }
        rootTvText.setText(titleText);
    }

    /**
     * 显示右边的菜单
     * @param rightText
     * @param listener
     */
    public void showRightMenu(String rightText, final CommonActionListener listener){
        rootLlRight.setVisibility(View.VISIBLE);
        rootTvRight.setText(rightText);
        rootLlRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.action();
            }
        });
    }

    /**
     * 获取TextView对象
     * @return
     */
    public TextView getRightTextView(){
        if(rootTvRight!=null){
            return rootTvRight;
        }else {
            return null;
        }
    }

    /**
     * 隐藏左边的菜单
     */
    public void hitLeftMenu(){
        rootLlleft.setVisibility(View.GONE);
    }

    /**
     * 加载网络异常的布局
     */
    public void loadingNetworkExecptionLayou(final CommonActionListener listener){
        if(!isLoadingA){
            viewStubNetworkExecption.inflate();
            isLoadingA = true;
        }
        TextView tvRefresh = (TextView) rootView.findViewById(R.id.tv_refresh_network_execption);
        tvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.action();
            }
        });
        viewStubNetworkExecption.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏网络异常的布局
     */
    public void hintNetworkExcptionLayout(){
        if(viewStubNetworkExecption!=null){
            viewStubNetworkExecption.setVisibility(View.GONE);
        }
    }

    /**
     * 显示数据加载失败的布局
     * @param listener 时间监听
     */
    public void loadingDataFailLayout(final CommonActionListener listener){
        if(!isLoadingB){
            viewStubDataLoadingFail.inflate();
            isLoadingB = true;
        }
        TextView tvRefresh = tvRefresh = (TextView)rootView.findViewById(R.id.click_tv_refresh_data_loading_fail);
        tvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.action();
            }
        });
        viewStubDataLoadingFail.setVisibility(View.VISIBLE);
    }

    public void hintDataLoadingFailLayout(){
        if(viewStubDataLoadingFail!=null){
            viewStubDataLoadingFail.setVisibility(View.GONE);
        }
    }

    /**
     * 加载没有数据的布局
     */
    public void loadingNoDataLayout(final CommonActionListener listener){
        if(!isLoadingC){
            viewStubNoData.inflate();
            isLoadingC = true;
        }
        TextView tvRefresh = (TextView)rootView.findViewById(R.id.click_tv_refresh_no_data);
        tvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.action();
            }
        });
        viewStubNoData.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏没有数据的布局
     */
    public void hintNoDataLayout(){
        viewStubNoData.setVisibility(View.GONE);
    }



    /**
     * 返回输入弹出框胡状态
     * @return true open  false close
     */
    public boolean inputStatus(){
        return inputMethodManager.isActive();
    }


    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public void colseInput(EditText editText){
        //关闭
        if(inputMethodManager == null){
            inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        if(!inputMethodManager.isActive()){
            return;
        }
       /* ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(editText.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);*/
       if(editText != null) {
           inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0); //强制隐藏键盘
       }

        /**
         * 以下方法有时候出现空指针
         */
        /*inputMethodManager.hideSoftInputFromWindow(BaseActivity.this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);*/
    }

    /**
     * 打开或者关闭输入弹出框
     * @return
     */
    public void openOrCloseInput(){
        try {
            if(inputMethodManager.isActive()){
                //关闭
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }else {
                //打开
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void startDownService(){
        //检查升级相关
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent); // 启动服务
        bindService(intent, connection, BIND_AUTO_CREATE); // 绑定服务
    }

    /**
     * 显示加载动画
     * @param message
     */
    public void showDialog(String message){
        LoadingDialog.show(this,message);
    }

    /**
     * 关闭加载动画
     */
    public void closeDialog(){
        LoadingDialog.closeDialog();
    }


    /**
     * 消息提醒
     * @param code 0 错误提示  1 正确提示 2 信息
     * @param msg
     */
    public void showMsg(int code,String msg){
        switch (code){
            case 0:
                ToastUtils2.showErrorMsg(msg);
                break;
            case 1:
                ToastUtils2.showErrorMsg(msg);
                break;
            case 2:
                ToastUtils2.showInfoMsg(msg);
                break;
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("BaseActivity-->onDestory");
        activityList.remove(this);
        unbindService(connection);
        unregisterReceiver(networkStatus);
        super.onDestroy();
    }

    /**
     * 杀死其它的Activity，
     * @param activity
     */
    public void finshOther(Activity activity){
        for (int i=0;i<activityList.size();i++){
            if(!activityList.get(i).equals(activity)){
                activityList.get(i).finish();
            }
        }
    }

    /**
     * 结束APP
     */
    public void finishAllActivity(){
        for (Activity activity:activityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        BaseFragment fragment = selectedFragment;
    }

    private BroadcastReceiver networkStatus = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onReceive(Context context, Intent intent) {
            //**判断当前的网络连接状态是否可用*/
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()){
//                hintNetworkExcptionLayout();
            }else {
                //当前网络不可用
                /*showMsg(0,"无网络可用");
                loadingNetworkExecptionLayou(new CommonActionListener() {
                    @Override
                    public void action() {
                        //跳转到设置页面 移动网络
                        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                });*/
            }
        }
    };

}