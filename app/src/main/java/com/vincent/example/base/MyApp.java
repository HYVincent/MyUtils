package com.vincent.example.base;

import android.app.Application;

import com.vincent.library.base.MyLibrary;

/**
 * @name MyUtils
 * @class name：com.vincent.example.base
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 12:13
 * @change
 * @chang time
 * @class describe
 */

public class MyApp extends Application{

    private static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        MyLibrary.init(getApplicationContext());
    }

    public static MyApp getMyApp() {
        return myApp;
    }
}
