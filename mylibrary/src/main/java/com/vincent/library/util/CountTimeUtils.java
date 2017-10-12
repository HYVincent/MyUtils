package com.vincent.library.util;

/**
 * @name HUDHelp
 * @class name：com.vincent.library.util
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/10/11 17:56
 * @change
 * @chang time
 * @class describe
 */

public class CountTimeUtils {

    private static TimeCount2 timeCount;

    /**
     * 开始倒计时
     * @param allTime
     * @param listener
     */
    public static void countTime(long allTime, TimeCount2.TimeFinishOnListener listener){
        TimeCount2 timeCount = new TimeCount2(allTime, 1000,listener);
        timeCount.start();
    }

    /**
     * 取消倒计时..
     */
    public static void cancel(){
        if(timeCount != null){
            timeCount.cancel();
        }
    }

}

