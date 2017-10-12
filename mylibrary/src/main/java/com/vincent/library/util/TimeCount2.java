package com.vincent.library.util;

import android.os.CountDownTimer;

/**
 * @name HUDHelp
 * @class name：com.vincent.library.util
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/10/11 17:55
 * @change
 * @chang time
 * @class describe
 */

public class TimeCount2 extends CountDownTimer {

    private TimeFinishOnListener listener;

    /**
     * @param millisInFuture 总的时间
     * @param countDownInterval 一般为1s触发一次
     */
    public TimeCount2(long millisInFuture, long countDownInterval,TimeFinishOnListener listener) {
        super(millisInFuture, countDownInterval);
        this.listener = listener;
    }

    @Override
    public void onTick(long l) {
        listener.everyAction(l);
    }

    @Override
    public void onFinish() {
        listener.finishAction();
    }

    public interface TimeFinishOnListener{

        void finishAction();

        void everyAction(long l);
    }

}
