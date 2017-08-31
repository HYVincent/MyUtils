package com.vincent.library.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.vincent.library.R;


/**
 * 获取验证码的倒计时
 * Created by Vincent on 2016/9/6.
 */

public class TimeCount extends CountDownTimer {

    private TextView tv;
    private Context c;
    private String text;
    private onCountTimeFinishListener listener;

    /**
     * 初始化，构造函数
     * @param context 上下文对象
     * @param millisInFuture 总的时间
     * @param countDownInterval 一般设置为1000，一秒钟减1
     * @param textView 需要设置倒计时的对象
     * @param text 时间结束之后变成什么字
     */
    public TimeCount(Context context, long millisInFuture, long countDownInterval, TextView textView, String text, onCountTimeFinishListener listener) {
        super(millisInFuture, countDownInterval);
        this.tv = textView;
        this.c=context;
        this.text=text;
        this.listener = listener;
    }

    @Override
    public void onTick(long l) {//计时开始
        tv.setClickable(false);//设置不可点击
        tv.setTextColor(ContextCompat.getColor(c, android.R.color.white));
        tv.setBackgroundColor(ContextCompat.getColor(c, android.R.color.darker_gray));
        tv.setText(l / 1000 + "秒后将返回主页");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onFinish() {
        tv.setText(text);
        tv.setClickable(true);
//        tv.setBackground(ContextCompat.getDrawable(c,R.drawable.shape_background_getcode));
        listener.onCountTimeFinish();
    }

    interface onCountTimeFinishListener{
        void onCountTimeFinish();
    }

}
