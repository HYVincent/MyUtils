package com.vincent.library.log;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Spinner;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.vincent.library.BuildConfig;
import com.vincent.library.base.Config;
import com.vincent.library.base.MyLibrary;

/**
 * @name MyUtils
 * @class name：com.vincent.library.log
 * @class describe XLogs
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 17:13
 * @change
 * @chang time
 * @class describe
 */

public class XLogs {

    private static Logger logger;
    private static XLogs instance = null ;
    private static boolean switchs = BuildConfig.DEBUG;//开关，也可以手动控制..

    private XLogs(){
        if(logger == null){
            logger = getLogger();
        }
    }

    public static synchronized XLogs getInstance() {
        // 这个方法比上面有所改进，不用每次都进行生成对象，只是第一次
        // 使用时生成实例，提高了效率！
        if (instance == null ) {
            instance = new XLogs();
        }
        return instance;
    }

    public static Logger getLogger() {
        if(logger == null){
            logger = initLogger();
        }
        return logger;
    }

    /**
     * 外部设置的方法
     * @param switchss
     */
    public static void setSwitchs(boolean switchss){
        switchs = switchss;
    }



    /**
     * Print the configured log.
     */
    public static void D(Object d){
        debug(d);
    }

    private static void debug(Object t){
        if(switchs){
            getLogger().d(t);
        }else {
            getLogger().d("Hello XLogs");
        };
    }

    public static void V(Object t){
        verbose(t);
    }

    private static void verbose(Object o){
        if(switchs){
            getLogger().v(o);
        }else {
            getLogger().v("Hello XLogs");
        };
    }

    public static void I(Object t){
        info(t);
    }

    private static void info(Object t){
        if(switchs){
            getLogger().i(t);
        }else {
            getLogger().i("Hello XLogs");
        };
    }

    public static void W(Object t){
        warn(t);
    }

    private static void warn(Object o){
        if(switchs){
            getLogger().w(o);
        }else {
            getLogger().w("Hello XLogs");
        };
    }

    public static void E(Object t){
        error(t);
    }

    private static void error(Object o){
        if(switchs){
            getLogger().e(o);
        }else {
            getLogger().e("Hello XLogs");
        };
    }
    private static Logger initLogger() {
        Logger.Builder builder = new Logger.Builder();
        builder.tag(Config.XLOG_TAG);
        if (Config.XLOG_IS_OUT_PUT_THREAD_INFO) {
            builder.t();
        } else {
            builder.nt();
        }

        if (Config.XLOG_IS_OUT_PUT_TACK_TRACE_INFO) {
            builder.st(1);
        } else {
            builder.nst();
        }

        if (Config.XLOG_IS_BORDER) {
            builder.b();
        } else {
            builder.nb();
        }
        logger = builder.build();
        return logger;
    }
}
