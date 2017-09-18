package com.vincent.library.util;

import android.content.Context;
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
}
