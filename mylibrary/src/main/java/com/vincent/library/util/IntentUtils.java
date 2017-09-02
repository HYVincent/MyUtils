package com.vincent.library.util;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * @name IntentSystemAndroid
 * @class name：test.vincent.com.intentutils
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/7/20 16:31
 * @change
 * @chang time
 * @class describe
 */

public class IntentUtils {

    public static void go(Activity activity, String tag, String pageName, String className) {
        Intent intent = new Intent(tag);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ComponentName comp = new ComponentName(pageName, className);
        intent.setComponent(comp);
        activity.startActivity(intent);
    }

    /**
     * 开启悬浮窗权限,oppo手机
     *
     * @param context
     */
    public static void goOppoFloatingWindowSetting(Activity context) {
        try {
            Intent intent = new Intent("com.shangyi.sayimo");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.coloros.safecenter",
                    "com.coloros.safecenter.permission.floatwindow.FloatWindowListActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            //抛出异常时提示信息
            Toast.makeText(context, "进入失败手动进入", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 判断某个APP是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Activity context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    /**
     * 跳转到华为手机管家悬浮窗权限管理页面
     */
    public static void goHuaWeiSetting(Activity activity) {
        try {
            //HUAWEI H60-l02 P8max测试通过
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//   ComponentName comp = new ComponentName("com.huawei.systemmanager","com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
//   ComponentName comp = new ComponentName("com.huawei.systemmanager",
//      "com.huawei.permissionmanager.ui.SingleAppActivity");//华为权限管理，跳转到指定app的权限管理位置需要华为接口权限，未解决
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");//悬浮窗管理页面
            intent.setComponent(comp);
            activity.startActivity(intent);
        } catch (SecurityException e) {
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//   ComponentName comp = new ComponentName("com.huawei.systemmanager","com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            ComponentName comp = new ComponentName("com.huawei.systemmanager",
                    "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理，跳转到本app的权限管理页面,这个需要华为接口权限，未解决
//      ComponentName comp = new ComponentName("com.huawei.systemmanager","com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");//悬浮窗管理页面
            intent.setComponent(comp);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            /**
             * 手机管家版本较低 HUAWEI SC-UL10
             */
//   Toast.makeText(MainActivity.this, "act找不到", Toast.LENGTH_LONG).show();
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem");//权限管理页面 android4.4
//   ComponentName comp = new ComponentName("com.android.settings","com.android.settings.permission.single_app_activity");//此处可跳转到指定app对应的权限管理页面，但是需要相关权限，未解决
            intent.setComponent(comp);
            activity.startActivity(intent);
            e.printStackTrace();
        } catch (Exception e) {
            //抛出异常时提示信息
            Toast.makeText(activity, "进入设置页面失败，请手动设置", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 跳转到华为手机管家权限管理主页
     *  com.huawei.systemmanager/com.huawei.permissionmanager.ui.MainActivity
     */
    public static void goHWWindowPermission(Activity activity){
        try {
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//
            intent.setComponent(comp);
            activity.startActivity(intent);
        }catch (Exception e){
//            ToastUtils.showSingleToastCenter(activity,"打开失败");
            e.printStackTrace();
        }
    }

    /**
     * 华为手机管家自启动管理
     * com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity
     */
    public static void goHWSelfMotionStartManager(Activity activity){
        try {
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
            intent.setComponent(comp);
            activity.startActivity(intent);
        }catch (Exception e){
//            ToastUtils.showSingleToastCenter(activity,"打开失败");
            e.printStackTrace();
        }
    }
    /**
     *跳转到华为手机管家关联启动页面
     * com.huawei.systemmanager/.startupmgr.ui.StartupAwakedAppListActivity
     *  java.lang.SecurityException: Permission Denial: starting Intent { act=com.vincent.julie flg=0x10000000 cmp=com.huawei.systemmanager/.startupmgr.ui.StartupAwakedAppListActivity } from ProcessRecord{bdb00e8 31867:com.vincent.julie/u0a156} (pid=31867, uid=10156) not exported from uid 1000
     */
    public static void goRelevanceStartManager(Activity activity){
        try {
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupAwakedAppListActivity");
            intent.setComponent(comp);
            activity.startActivity(intent);
        }catch (SecurityException e){
//            ToastUtils.showSingleTextToast(activity,"安全异常，未获取许可，打开失败");
            Toast.makeText(activity,"安全异常,挑战失败",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }catch (Exception e){
//            ToastUtils.showSingleTextToast(activity,"打开失败");
            e.printStackTrace();
        }
    }
    /**
     * 受保护的app 锁屏可以继续运行
     * com.huawei.systemmanager/.optimize.process.ProtectActivity
     */
    public static void goProtectAppManager(Activity activity){
        try {
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");
            intent.setComponent(comp);
            activity.startActivity(intent);
        }catch (Exception e){
//            ToastUtils.showSingleToastCenter(activity,"打开失败");
            e.printStackTrace();
        }
    }
    /**
     * 华为手机管家通知管理页面
     * com.huawei.systemmanager/com.huawei.notificationmanager.ui.NotificationManagmentActivity
     */
    public static void goNotificationManager(Activity activity){
        try {
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity");
            intent.setComponent(comp);
            activity.startActivity(intent);
        }catch (Exception e){
//            ToastUtils.showSingleToastCenter(activity,"打开失败");
            e.printStackTrace();
        }
    }
    /**
     * 华为手机管家清理加速页面
     * com.huawei.systemmanager/.spacecleanner.SpaceCleanActivity
     */
    public static void goHWClearMemory(Activity activity){
        try {
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.spacecleanner.SpaceCleanActivity");
            intent.setComponent(comp);
            activity.startActivity(intent);
        }catch (Exception e){
//            ToastUtils.showSingleToastCenter(activity,"打开失败");
            e.printStackTrace();
        }
    }
    /**
     * 华为手机管家骚扰拦截页面
     * com.huawei.systemmanager/com.huawei.harassmentinterception.ui.InterceptionActivity
     */
    public static void goHWInterceptionAct(Activity activity){
        try {
            Intent intent = new Intent("com.vincent.julie");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.harassmentinterception.ui.InterceptionActivity");
            intent.setComponent(comp);
            activity.startActivity(intent);
        }catch (Exception e){
//            ToastUtils.showSingleToastCenter(activity,"打开失败");
            e.printStackTrace();
        }
    }

    /**
     * ip设置 高级WIFI设置页面
     * @param activity
     */
    public static void goIPSetting(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_WIFI_IP_SETTINGS);//高级WIFI设置
        activity.startActivity(intent);
    }


    /**
     * 用户字典
     * @param activity
     */
    public static void goUserDictionary(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS);
        activity.startActivity(intent);
    }


    /**
     * 账号同步
     * @param activity
     */
    public static void goAccountSync(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_SYNC_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 声音设置
     * @param activity
     */
    public static void goSoudsSetting(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_SOUND_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 安全设置
     * @param activity
     */
    public static void goSafetySetting(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_SECURITY_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 搜索设置
     * @param activity
     */
    public static void goSearchSetting(Activity activity){
        /*Intent intent =  new Intent(Settings.ACTION_SEARCH_SETTINGS);//找不到，报异常了 ActivityNotFoundException
        activity.startActivity(intent);*/
    }

    /**
     * 快速启动
     * @param activity
     */
    public static void goQuicklyStart(Activity activity){
       /* Intent intent =  new Intent(Settings.ACTION_QUICK_LAUNCH_SETTINGS);
        activity.startActivity(intent);*/
    }

    /**
     * 备份和重置页面
     * @param activity
     */
    public static void goBackupReset(Activity activity)
    {
        Intent intent =  new Intent(Settings.ACTION_PRIVACY_SETTINGS);
        activity.startActivity(intent);
    }
    /**
     * NFC共享设置
     * @param activity
     */
    public static void goNFCSetting(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_NFCSHARING_SETTINGS);//NFC
        activity.startActivity(intent);

      /*  Intent intent =  new Intent(Settings.ACTION_NFC_SETTINGS);//NFC设置页面
        activity.startActivity(intent);*/

    }


    /**
     * 跳转到运营商页面
     * @param activity
     */
    public static void goNetowrkOperator(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 跳转到位置服务
     * @param activity
     */
    public static void goLocationService(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 存储设置 内存和存储
     * @param activity
     */
    public static void goSaveSetting(Activity activity){
        /*Intent intent =  new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);//内存和存储
        activity.startActivity(intent);*/
        Intent intent =  new Intent(Settings.ACTION_MEMORY_CARD_SETTINGS);//同上
        activity.startActivity(intent);
    }

    /**
     * 跳转到语言和输入设备
     * @param activity
     */
    public static void goLanguageInput(Activity activity){
        /*Intent intent =  new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);//输入法列表
        activity.startActivity(intent);*/
       /* Intent intent =  new Intent(Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);//多国语言选择
        activity.startActivity(intent);*/

        Intent intent =  new Intent(Settings.ACTION_LOCALE_SETTINGS);//语言选择 语言偏好设置
        activity.startActivity(intent);

    }

    /**
     * 屏幕保护
     * @param activity
     */
    public static void goScreenProtct(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_DREAM_SETTINGS);
        activity.startActivity(intent);
    }


    /**
     * 手机状态
     * @param activity
     */
   public static void goPhoneStatus(Activity activity){
       Intent intent =  new Intent(Settings.ACTION_DISPLAY_SETTINGS);
       activity.startActivity(intent);
   }

    /**
     * 跳转到日期设置页面
     * @param activity
     */
    public static void goDateSetting(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_DATE_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 跳转到移动网络设置页面
     * @param activity
     */
    public static void goNetwork(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 跳转到蓝牙设置页面
     * @param activity
     */
    public static void goBluetoothSetting(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 应用程序列表
     * @param activity
     */
    public static void goAPPList(Activity activity){
        /*Intent intent =  new Intent(Settings.ACTION_APPLICATION_SETTINGS);//应用管理
        activity.startActivity(intent);*/

        /*Intent intent =  new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);//代搜索的列表
        activity.startActivity(intent);*/

        Intent intent =  new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
        activity.startActivity(intent);

    }

    /**
     * 开发人员选项
     * @param activity
     */
    public static void goDevSetting(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 根据包名跳转到相应的权限管理页面去
     * @param activity
     * @param pageName
     */
    public static void goPermissionManagerActivity(Activity activity,String pageName){
        Uri packageURI = Uri.parse("package:" + pageName);
        Intent intent =  new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,packageURI);
        activity.startActivity(intent);
    }

    /**
     * @param activity 跳转到APN设置页面
     */
    public static void goAPNSetting(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_APN_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * @param activity 设置页面
     */
    public static void goCloseSignalIamp(Activity activity){
        /*Intent intent =  new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        activity.startActivity(intent);*/

        Intent intent =  new Intent(Settings.ACTION_SETTINGS);//同上
        activity.startActivity(intent);

    }

    /**
     * @param activity 跳转到WiFi页面
     */
    public static void goWifi(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_WIFI_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 添加账户
     * @param activity
     */
    public static void goAddAccount(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_ADD_ACCOUNT);
        activity.startActivity(intent);
    }

    /**
     * 跳转到辅助功能页面
     * @param activity
     */
    public static void goHelp(Activity activity){
        Intent intent =  new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 打开系统音乐
     * @param activity
     */
    public static void goMusic(Activity activity) {
        Intent intent = new Intent("com.hud.help");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ComponentName comp = new ComponentName("com.android.mediacenter", "com.android.mediacenter.PageActivity");
        intent.setComponent(comp);
        activity.startActivity(intent);
    }

    /**
     * 跳转到联系人
     * @param activity
     */
    public static void goLinkmanContacts(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Contacts.People.CONTENT_URI);
        activity.startActivity(intent);
    }

    /**
     * 跳转到拨号页面，传递电话号码过去
     * @param activity
     * @param phoneNumber
     */
    public static void goDial(Activity activity, String phoneNumber) {
        Uri uri = Uri.parse("tel:" + phoneNumber); //设置要操作的路径
        Intent it = new Intent();
        it.setAction(Intent.ACTION_DIAL);  //设置要操作的Action
        it.setData(uri); //要设置的数据
        activity.startActivity(it);    //执行跳转
    }

    /**
     * 直接拨打电话
     * @param activity
     * @param phoneNumber
     */
    public static void goCellPhone(Activity activity, String phoneNumber) {
        Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        //TODO 这里需要 CALL_PHONE 权限，不然无法拨打电话
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(activity,"此处需要检查权限",Toast.LENGTH_LONG).show();
            return;
        }
        activity.startActivity(intentPhone);
    }

}
