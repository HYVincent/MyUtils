package com.vincent.example.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vincent.example.R;
import com.vincent.library.util.IntentUtils;


public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = IntentActivity.class.getSimpleName();

    private Button btnGoMusic;//打开系统音乐
    private Button btnGoLinkmanContacts;//跳转到联系人
    private Button btnGoDial;//跳转到拨号
    private Button btnGoCellPhone;//直接拨打电话
    private Button btnGoHelp;//跳转到系统辅助页面
    private Button btnGoAddAccount;//跳转到添加账户页面
    private Button btnGoSetting;//跳转到设置页面
    private Button btnGoWifi;//跳转到WiFi列表
    private Button btnGoAPNSetting;//跳转到APN设置
    private Button btnGoPermissionManager;//根据包名跳转到相应的权限管理页面
    private Button btnGoDevSetting;//跳转到开发人员选项
    private Button btnGoAppList;//跳转到应用列表
    private Button btnGoBluetoothSetting;//跳转到蓝牙设置
    private Button btnGoNetwork;//跳转到移动网络设置页面
    private Button btnGoDateSetting;//跳转到日期设置页面
    private Button btnGoPhoneStatus;//跳转到手机状态页面
    private Button btnGoScreenProtect;//跳转到屏幕保护
    private Button btnGoLanguageInput;//跳转到语言和输入法
    private Button btnGoLocationService;//跳转到位置服务
    private Button btnGoNetworkOperator;//跳转到运营商选择页面
    private Button btnGoNFCSetting;//nfc共享设置
    private Button btnGoBackupReset;//备份和重置页面
    private Button btnGoSafetySetting;//跳转到安全设置页面
    private Button btnGoSoudsSetting;//声音设置
    private Button btnGoAccountSync;//账号同步
    private Button btnGoUserDictionary;//用户词典
    private Button btnGoPermissionAct;//华为权限管理 btn_go_permission_act
    private Button btnGoWindowsPermission;//悬浮窗权限管理
    private Button btnGoHWProtectAPP;//受保护app
    private Button btnGoHWSelfMotionStartAppList;//华为自启动APP列表
    private Button btnGoHWRelevanceStart;//关联启动
    private Button btnGoHWNotificationManager;//通知管理
    private Button btnGoHWClearMemory;//内存管理
    private Button btnGoHWInterception;//骚扰拦截

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        btnGoMusic = (Button) findViewById(R.id.btn_go_music);
        btnGoMusic.setOnClickListener(this);
        btnGoLinkmanContacts = (Button) findViewById(R.id.btn_go_linkman_contacts);
        btnGoLinkmanContacts.setOnClickListener(this);
        btnGoDial = (Button) findViewById(R.id.btn_go_dial);
        btnGoDial.setOnClickListener(this);
        btnGoCellPhone = (Button) findViewById(R.id.btn_go_cell_phone);
        btnGoCellPhone.setOnClickListener(this);
        btnGoHelp = (Button) findViewById(R.id.btn_go_help);
        btnGoHelp.setOnClickListener(this);
        btnGoAddAccount = (Button) findViewById(R.id.btn_go_add_account);
        btnGoAddAccount.setOnClickListener(this);
        btnGoSetting = (Button) findViewById(R.id.btn_go_setting);
        btnGoSetting.setOnClickListener(this);
        btnGoWifi = (Button) findViewById(R.id.btn_go_wifi_list);
        btnGoWifi.setOnClickListener(this);
        btnGoAPNSetting = (Button) findViewById(R.id.btn_go_apn_setting);
        btnGoAPNSetting.setOnClickListener(this);
        btnGoPermissionManager = (Button) findViewById(R.id.btn_go_permission_manager);
        btnGoPermissionManager.setOnClickListener(this);
        btnGoDevSetting = (Button) findViewById(R.id.btn_go_dev_setting);
        btnGoDevSetting.setOnClickListener(this);
        btnGoAppList = (Button) findViewById(R.id.btn_go_app_list);
        btnGoAppList.setOnClickListener(this);
        btnGoBluetoothSetting = (Button) findViewById(R.id.btn_go_bluetooth_setting);
        btnGoBluetoothSetting.setOnClickListener(this);
        btnGoNetwork = (Button) findViewById(R.id.btn_go_network);
        btnGoNetwork.setOnClickListener(this);
        btnGoDateSetting = (Button) findViewById(R.id.btn_go_date_setting);
        btnGoDateSetting.setOnClickListener(this);
        btnGoPhoneStatus = (Button) findViewById(R.id.btn_go_phone_status);
        btnGoPhoneStatus.setOnClickListener(this);
        btnGoScreenProtect = (Button) findViewById(R.id.btn_go_screen_protect);
        btnGoScreenProtect.setOnClickListener(this);
        btnGoLanguageInput = (Button) findViewById(R.id.btn_go_language_input);
        btnGoLanguageInput.setOnClickListener(this);
        btnGoLocationService = (Button) findViewById(R.id.btn_go_location_service);
        btnGoLocationService.setOnClickListener(this);
        btnGoNetworkOperator = (Button) findViewById(R.id.btn_go_nework_operator);
        btnGoNetworkOperator.setOnClickListener(this);
        btnGoNFCSetting = (Button) findViewById(R.id.btn_go_nfc_setting);
        btnGoNFCSetting.setOnClickListener(this);
        btnGoBackupReset = (Button) findViewById(R.id.btn_go_backup_reset);
        btnGoBackupReset.setOnClickListener(this);
        btnGoSafetySetting = (Button) findViewById(R.id.btn_go_safety_setting);
        btnGoSafetySetting.setOnClickListener(this);
        btnGoSoudsSetting = (Button) findViewById(R.id.btn_go_souds_setting);
        btnGoSoudsSetting.setOnClickListener(this);
        btnGoAccountSync = (Button) findViewById(R.id.btn_go_account_sync);
        btnGoAccountSync.setOnClickListener(this);
        btnGoUserDictionary = (Button) findViewById(R.id.btn_go_user_dictionary);
        btnGoUserDictionary.setOnClickListener(this);
        btnGoPermissionAct = (Button) findViewById(R.id.btn_go_permission_act);//华为权限管理
        btnGoPermissionAct.setOnClickListener(this);
        btnGoWindowsPermission = (Button) findViewById(R.id.btn_go_windows_permission);
        btnGoWindowsPermission.setOnClickListener(this);
        btnGoHWProtectAPP = (Button) findViewById(R.id.btn_go_protect_act);
        btnGoHWProtectAPP.setOnClickListener(this);
        btnGoHWSelfMotionStartAppList = (Button) findViewById(R.id.btn_go_self_motion_start);
        btnGoHWSelfMotionStartAppList.setOnClickListener(this);
        btnGoHWRelevanceStart = (Button)findViewById(R.id.btn_relevance_start);
        btnGoHWRelevanceStart.setOnClickListener(this);
        btnGoHWNotificationManager = (Button)findViewById(R.id.btn_go_notification_manager);
        btnGoHWNotificationManager.setOnClickListener(this);
        btnGoHWClearMemory = (Button)findViewById(R.id.btn_go_clear_memory);
        btnGoHWClearMemory.setOnClickListener(this);
        btnGoHWInterception = (Button)findViewById(R.id.btn_go_interception);
        btnGoHWInterception.setOnClickListener(this);
    }

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,IntentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_music:
                IntentUtils.goMusic(IntentActivity.this);
                break;
            case R.id.btn_go_linkman_contacts:
                IntentUtils.goLinkmanContacts(IntentActivity.this);
                break;
            case R.id.btn_go_dial:
                IntentUtils.goDial(IntentActivity.this, "1111");
                break;
            case R.id.btn_go_cell_phone:
                //TODO 需要权限
                IntentUtils.goCellPhone(IntentActivity.this, "2222");
                break;
            case R.id.btn_go_help:
                IntentUtils.goHelp(IntentActivity.this);
                break;
            case R.id.btn_go_add_account:
                IntentUtils.goAddAccount(IntentActivity.this);
                break;
            case R.id.btn_go_setting:
                IntentUtils.goCloseSignalIamp(IntentActivity.this);
                break;
            case R.id.btn_go_wifi_list:
                IntentUtils.goWifi(IntentActivity.this);
                break;
            case R.id.btn_go_apn_setting:
                IntentUtils.goAPNSetting(IntentActivity.this);
                break;
            case R.id.btn_go_permission_manager:
                IntentUtils.goPermissionManagerActivity(IntentActivity.this, "com.vincent.intent");
                break;
            case R.id.btn_go_dev_setting:
                IntentUtils.goDevSetting(IntentActivity.this);
                break;
            case R.id.btn_go_app_list:
                IntentUtils.goAPPList(IntentActivity.this);
                break;
            case R.id.btn_go_bluetooth_setting:
                IntentUtils.goBluetoothSetting(IntentActivity.this);
                break;
            case R.id.btn_go_network:
                IntentUtils.goNetwork(IntentActivity.this);
                break;
            case R.id.btn_go_date_setting:
                IntentUtils.goDateSetting(IntentActivity.this);
                break;
            case R.id.btn_go_phone_status:
                IntentUtils.goPhoneStatus(IntentActivity.this);
                break;
            case R.id.btn_go_screen_protect:
                IntentUtils.goScreenProtct(IntentActivity.this);
                break;
            case R.id.btn_go_language_input:
                IntentUtils.goLanguageInput(IntentActivity.this);
                break;
            case R.id.btn_go_location_service:
                IntentUtils.goLocationService(IntentActivity.this);
                break;
            case R.id.btn_go_nework_operator:
                IntentUtils.goNetowrkOperator(IntentActivity.this);
                break;
            case R.id.btn_go_nfc_setting:
                IntentUtils.goNFCSetting(IntentActivity.this);
                break;
            case R.id.btn_go_backup_reset:
                IntentUtils.goBackupReset(IntentActivity.this);
                break;
            case R.id.btn_go_safety_setting:
                IntentUtils.goSafetySetting(IntentActivity.this);
                break;
            case R.id.btn_go_souds_setting:
                IntentUtils.goSoudsSetting(IntentActivity.this);
                break;
            case R.id.btn_go_account_sync:
                IntentUtils.goAccountSync(IntentActivity.this);
                break;
            case R.id.btn_go_user_dictionary:
                IntentUtils.goUserDictionary(IntentActivity.this);
                break;
            case R.id.btn_go_permission_act:
                IntentUtils.goHWWindowPermission(IntentActivity.this);
                break;
            case R.id.btn_go_windows_permission:
                IntentUtils.goHuaWeiSetting(IntentActivity.this);
                break;
            case R.id.btn_go_protect_act:
                Log.d(TAG, "onClick: .....................");
                IntentUtils.goProtectAppManager(IntentActivity.this);
                break;
            case R.id.btn_go_self_motion_start:
                IntentUtils.goHWSelfMotionStartManager(IntentActivity.this);
                break;
            case R.id.btn_relevance_start:
                IntentUtils.goRelevanceStartManager(IntentActivity.this);
                break;
            case R.id.btn_go_notification_manager:
                IntentUtils.goNotificationManager(IntentActivity.this);
                break;
            case R.id.btn_go_clear_memory:
                IntentUtils.goHWClearMemory(IntentActivity.this);
                break;
            case R.id.btn_go_interception:
                IntentUtils.goHWInterceptionAct(IntentActivity.this);
                break;
        }
    }
}
