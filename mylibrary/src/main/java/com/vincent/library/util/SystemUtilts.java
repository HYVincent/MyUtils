package com.vincent.library.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @name MyUtils
 * @class name：com.vincent.library.util
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 18:59
 * @change
 * @chang time
 * @class describe
 */

public class SystemUtilts {

    /**
     * 获取手机型号
     * @return
     */
    public static String getPhoneMdel(){
        String s=android.os.Build.MODEL;
        return s;

    }
    /**
     * 获取手机厂商
     * @return
     */
    public static String getPhoneManufacturer(){
        String phoneManufacturer=android.os.Build.MANUFACTURER;
        return phoneManufacturer;
    }
    /**
     * 返回系统版本号
     * @return
     */
    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            Log.e(e.toString(), "");

        }
        return version;
    }
    /**
     * 获取手机的IMEI号码
     * @param context
     * @return
     */
    public static String getIMEINumber(Context context){
        String imei =((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//        不过纯APP开发SystemProperties，TelephonyProperties汇报错误，因为android.os.SystemProperties在SDK的库中是没有的，
//        需要把Android SDK 目录下data下的layoutlib.jar文件加到当前工程的附加库路径中，就可以Import。
//        如果Android Pad没有IMEI,用此方法获取设备ANDROID_ID：
//        String IMEI =SystemProperties.get(android.telephony.TelephonyProperties.PROPERTY_IMEI);
        return imei;
    }
    /**
     * 判断应用是在前台还是后台
     */

    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Log.e("后台", appProcess.processName);
                    return true;
                } else {
                    Log.e("前台", appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }
    /**
     * 判断某个服务是否正在运行的Method
     *
     * @param mContext
     * @param serviceName
     * 是包�?+服务的类名（例如：net.loonggg.testbackstage.TestService�?
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }

    /**
     * 开启权限
     * @param context
     */
    public static void goSetting(Context context){
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
    public static boolean isAppInstalled(Context context,String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed =false;
        try {
            pm.getPackageInfo(packageName,PackageManager.GET_ACTIVITIES);
            installed =true;
        } catch(PackageManager.NameNotFoundException e) {
            installed =false;
        }
        return installed;
    }

    /**
     * 通过反射获取类对象
     * @param context
     * @param className 包含包名
     * @return
     */
    public static Object getReflectInstance(Context context, String className){
        try {
            //获取Student的Class对象
            Class<?> clazz = Class.forName(className);
            //获取该类中所有的属性
            Field[] fields = clazz.getDeclaredFields();
            //遍历所有的属性
            for (Field field : fields) {
                //打印属性信息，包括访问控制修饰符，类型及属性名
                System.out.println(field);
//                System.out.println("修饰符：" + Modifier.toString(field.getModifiers()));
                Log.d(SystemUtilts.class.getSimpleName(),"修饰符：" + Modifier.toString(field.getModifiers()));
                Log.d(SystemUtilts.class.getSimpleName(),"类型：" + field.getType());
                Log.d(SystemUtilts.class.getSimpleName(),"属性名：" + field.getName());
            }
            //获取该类中的所有方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                //打印方法签名
                System.out.println(method);
                Log.d(SystemUtilts.class.getSimpleName(),method.toString());
                Log.d(SystemUtilts.class.getSimpleName(),"修饰符：" + Modifier.toString(method.getModifiers()));
                Log.d(SystemUtilts.class.getSimpleName(),"方法名：" + method.getName());
                Log.d(SystemUtilts.class.getSimpleName(),"返回类型：" + method.getReturnType());
                //获取方法的参数对象
                Class<?>[] clazzes = method.getParameterTypes();
                for (Class<?> class1 : clazzes) {
                    Log.d(SystemUtilts.class.getSimpleName(),"参数类型：" + class1);
                }
            }
            //通过Class对象创建实例
//            Student student = (Student)clazz.newInstance();
//            //获取属性名为studentName的字段(Field)对象，以便下边重新设置它的值
//            Field studentName = clazz.getField("studentName");
//            //设置studentName的值为”张三“
//            studentName.set(student, "张三");
//
//            //通过Class对象获取名为”finishTask“，参数类型为String的方法(Method)对象
//            Method finishTask = clazz.getMethod("finishTask", String.class);
//            //调用finishTask方法
//            finishTask.invoke(student, "数学");
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


}
