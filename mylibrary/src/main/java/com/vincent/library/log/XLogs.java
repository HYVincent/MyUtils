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

    private XLogs(){
        if(logger == null){
            logger = getLogger();
        }
    }


    public static Logger getLogger() {
        if(logger == null){
            logger = initLogger();
        }
        return logger;
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
