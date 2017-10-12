package com.vincent.library.util;

import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @name MyUtils
 * @class name：com.vincent.library.util
 * @class describe 封装图片加载库 方便替换或者加载各种类型的图片
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/1 9:54
 * @change
 * @chang time
 * @class describe
 */

public class ImageLoadingUtils {

    /**
     * 图片加载
     * @param context
     * @param imageView
     */
    public static void loadingImg(Context context, Object o, ImageView imageView){
        Glide.with(context).load(o).into(imageView);
    }

    /**
     * 设置图片的闪烁效果
     * @param iv_chat_head
     */
    public static void setFlickerAnimation(ImageView iv_chat_head) {
        final Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(500);//闪烁时间间隔
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
//        animation.setRepeatCount(Animation.INFINITE);//无限模式
        animation.setRepeatCount(Animation.ZORDER_NORMAL);
        animation.setRepeatMode(Animation.REVERSE);
        iv_chat_head.setAnimation(animation);
        iv_chat_head.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.cancel();
            }
        },3000);
    }

}
