package com.vincent.library.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.vincent.library.R;
import com.vincent.library.base.MyLibrary;
import com.vincent.library.util.SystemUtilts;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @name MyUtils
 * @class name：com.vincent.library.notification
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 18:56
 * @change
 * @chang time
 * @class describe
 */

public class NotificationUtils {


    private static NotificationManager mNotificationManager;
    private static final int  notifyId=100;
    /** Notification构造器 */
    private NotificationCompat.Builder mBuilder;


    /**
     * 发送通知提示
     * @param context 上下文
     * @param imgId 图标id
     * @param title 通知title
     * @param msg 消息
     * @param activity 点击通知需要跳转的Activity，Activity需要完整路径，包含包名
     */
    public static void sendNotification(Context context, String activity, int imgId, String title, String msg) {
        try {
            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            Intent resultIntent = new Intent(context, SystemUtilts.getReflectInstance(MyLibrary.getContext(),activity).getClass());
//            mBuilder.setAutoCancel(false);//设置消息点击之后取消
            resultIntent.putExtra("title",title);
            resultIntent.putExtra("msg",msg);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setSmallIcon(imgId)
                    .setTicker("您有新的消息")
                    .setContentTitle(title)//通知的标题
                    .setContentText(msg)//显示在界面上的内容
                    .setContentIntent(pendingIntent);
            Notification mNotification = mBuilder.build();
//        mNotification.icon = R.mipmap.et_app_icon;//设置通知  消息  图标
            mNotification.iconLevel=imgId;
            //在通知栏上点击此通知后自动清除此通知
//            mNotification.flags = Notification.FLAG_ONGOING_EVENT;//FLAG_ONGOING_EVENT 在顶部常驻，可以调用下面的清除方法去除  FLAG_AUTO_CANCEL  点击和清理可以去调
            mNotification.flags = Notification.FLAG_AUTO_CANCEL;//设置点击之后消失
//        mNotification.defaults = Notification.DEFAULT_VIBRATE; //设置显示通知时的默认的发声、震动、Light效果
            mNotification.defaults = Notification.DEFAULT_SOUND;//声音效果，不震动
            //设置发出消息的内容
            mNotification.tickerText = "您有新的消息";//通知产生的时候发出的内容
            //设置发出通知的时间
            mNotification.when = System.currentTimeMillis();
//        startForeground(notifyId, mNotification);//把该service创建为前台service
            mNotificationManager.notify(notifyId,mNotification);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 系统消息通知栏
     * @param context
     * @param mClass
     * @param msgTitle
     * @param content
     */
    public static final void sendSystemMsg(Context context,Class mClass,String msgTitle,String content){
        NotificationManager mNotificationManager= (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        android.support.v7.app.NotificationCompat.Builder mBuilder = new android.support.v7.app.NotificationCompat.Builder(context);
        mBuilder.setContentTitle(msgTitle)
                .setContentText(content)
                .setContentIntent(getDefalutIntent(context,Notification.FLAG_AUTO_CANCEL))
//				.setNumber(number)//显示数量
                .setTicker("您有新的消息")//通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE|Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                .setSmallIcon(R.mipmap.ic_launcher_round);
        //点击的意图ACTION是跳转到Intent
        Intent resultIntent = new Intent(context, mClass);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
    }
    private static PendingIntent getDefalutIntent(Context context,int flags){
        PendingIntent pendingIntent= PendingIntent.getActivity(context, 1, new Intent(), flags);
        return pendingIntent;
    }

    /** 初始化通知栏 */
    private void initNotify(Context mContext,int imgId){
        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setContentTitle("测试标题")
                .setContentText("测试内容")
                .setContentIntent(getDefalutIntent(mContext,Notification.FLAG_AUTO_CANCEL))
//				.setNumber(number)//显示数量
                .setTicker("测试通知来啦")//通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
//				.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(imgId);
    }


    /** 显示常驻通知栏 */
    public static void showCzNotify(Context mContext,int imgId,Class mClass){
        mNotificationManager= (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
//		Notification mNotification = new Notification();//为了兼容问题，不用该方法，所以都采用BUILD方式建立
//		Notification mNotification  = new Notification.Builder(this).getNotification();//这种方式已经过时
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
        Intent resultIntent = new Intent(mContext, mClass);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//		//PendingIntent 跳转动作
        PendingIntent pendingIntent=PendingIntent.getActivity(mContext, 0, resultIntent, 0);
        mBuilder.setSmallIcon(imgId)
                .setTicker("常驻通知来了")
                .setContentTitle("常驻测试")
                .setContentText("使用cancel()方法才可以把我去掉哦")
                .setContentIntent(pendingIntent);
        Notification mNotification = mBuilder.build();
        //设置通知  消息  图标
        mNotification.icon = imgId;
        //在通知栏上点击此通知后自动清除此通知
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;//FLAG_ONGOING_EVENT 在顶部常驻，可以调用下面的清除方法去除  FLAG_AUTO_CANCEL  点击和清理可以去调
        //设置显示通知时的默认的发声、震动、Light效果
        mNotification.defaults = Notification.DEFAULT_VIBRATE;
        //设置发出消息的内容
        mNotification.tickerText = "通知来了";
        //设置发出通知的时间
        mNotification.when=System.currentTimeMillis();
//		mNotification.flags = Notification.FLAG_AUTO_CANCEL; //在通知栏上点击此通知后自动清除此通知
//		mNotification.setLatestEventInfo(this, "常驻测试", "使用cancel()方法才可以把我去掉哦", null); //设置详细的信息  ,这个方法现在已经不用了
        mNotificationManager.notify(notifyId, mNotification);
    }

    /**
     * 带按钮的通知栏 自定义通知栏
     */
  /*  public void showButtonNotify(){
        NotificationCompat.Builder mBuilder = new Builder(this);
        RemoteViews mRemoteViews = new RemoteViews(getPackageName(), R.layout.view_custom_button);
        mRemoteViews.setImageViewResource(R.id.custom_song_icon, R.drawable.sing_icon);
        //API3.0 以上的时候显示按钮，否则消失
        mRemoteViews.setTextViewText(R.id.tv_custom_song_singer, "周杰伦");
        mRemoteViews.setTextViewText(R.id.tv_custom_song_name, "七里香");
        //如果版本号低于（3。0），那么不显示按钮
        if(BaseTools.getSystemVersion() <= 9){
            mRemoteViews.setViewVisibility(R.id.ll_custom_button, View.GONE);
        }else{
            mRemoteViews.setViewVisibility(R.id.ll_custom_button, View.VISIBLE);
            //
            if(isPlay){
                mRemoteViews.setImageViewResource(R.id.btn_custom_play, R.drawable.btn_pause);
            }else{
                mRemoteViews.setImageViewResource(R.id.btn_custom_play, R.drawable.btn_play);
            }
        }

        //点击的事件处理
        Intent buttonIntent = new Intent(ACTION_BUTTON);
		*//* 上一首按钮 *//*
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PREV_ID);
        //这里加了广播，所及INTENT的必须用getBroadcast方法
        PendingIntent intent_prev = PendingIntent.getBroadcast(this, 1, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_custom_prev, intent_prev);
		*//* 播放/暂停  按钮 *//*
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PALY_ID);
        PendingIntent intent_paly = PendingIntent.getBroadcast(this, 2, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_custom_play, intent_paly);
		*//* 下一首 按钮  *//*
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_NEXT_ID);
        PendingIntent intent_next = PendingIntent.getBroadcast(this, 3, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_custom_next, intent_next);

        mBuilder.setContent(mRemoteViews)
                .setContentIntent(getDefalutIntent(Notification.FLAG_ONGOING_EVENT))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("正在播放")
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                .setOngoing(true)
                .setSmallIcon(R.drawable.sing_icon);
        Notification notify = mBuilder.build();
        notify.flags = Notification.FLAG_ONGOING_EVENT;
        //会报错，还在找解决思路
//		notify.contentView = mRemoteViews;
//		notify.contentIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);
        mNotificationManager.notify(200, notify);
    }*/


}
