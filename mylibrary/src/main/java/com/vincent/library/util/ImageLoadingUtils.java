package com.vincent.library.util;

import android.content.Context;

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

    private static Context mContext;

    private ImageLoadingUtils(Context context){
        mContext = context;
    }

    public static void loadingImg(String networkUrl){
//        Glide.with(mContext).load()
    }

}
