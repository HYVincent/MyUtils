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
import com.vincent.library.base.Config;
import com.vincent.library.base.MyLibrary;

/**
 * @name MyUtils
 * @class name：com.vincent.library.log
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 17:13
 * @change
 * @chang time
 * @class describe
 */

public class XLogs<T> {


    private static Logger logger;
    private static XLogs xLogs;

    private XLogs(){
        if(logger == null){
            logger = getLogger();
        }
    }

    public static XLogs getxLogs() {
        if(xLogs == null){
            xLogs = new XLogs();
        }
        return xLogs;
    }

    /**
     * Print the configured log.
     */
    private static Logger getLogger() {
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

    public void d(T t){
        getLogger().d(t);
    }

    public void v(T t){
        getxLogs().v(t);
    }

    public void i(T t){
        getxLogs().i(t);
    }

    public void w(T t){
        getxLogs().w(t);
    }

    public void e(T t){
        getxLogs().e(t);
    }

}
