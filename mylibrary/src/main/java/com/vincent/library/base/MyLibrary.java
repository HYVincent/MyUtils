package com.vincent.library.base;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.ClassicFlattener;
import com.elvishew.xlog.interceptor.BlacklistTagsFilterInterceptor;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.SystemPrinter;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;
import com.vincent.library.util.PermissionUtils;

import java.io.File;

/**
 * @name MyUtils
 * @class name：com.vincent.library.base
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 14:11
 * @change
 * @chang time
 * @class describe
 */

public class MyLibrary {

    private static Context context;
    private static final String TAG = MyLibrary.class.getSimpleName();


    public static void init(Context mContext){
        context = mContext;
        initXlog();
    }

    public static Context getContext() {
        if(context == null){
            throw new NullPointerException("MyLibrary is no init,Please init it in your Application");
        }
        return context.getApplicationContext();
    }

    /**
     * Initialize XLog.
     */
    private static void initXlog() {
//        XLog.init(LogLevel.ALL);//一般初始化..
//        XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);//正式版本禁止打印日志

        //高级初始化
        LogConfiguration config = new LogConfiguration.Builder()
                .tag(TAG)                                         // 指定 TAG，默认为 "X-LOG"
                .t()                                                   // 允许打印线程信息，默认禁止
                .st(2)                                                 // 允许打印深度为2的调用栈信息，默认禁止
                .b()                                                   // 允许打印日志边框，默认禁止
//                .jsonFormatter(new MyJsonFormatter())                  // 指定 JSON 格式化器，默认为 DefaultJsonFormatter
//                .xmlFormatter(new MyXmlFormatter())                    // 指定 XML 格式化器，默认为 DefaultXmlFormatter
//                .throwableFormatter(new MyThrowableFormatter())        // 指定可抛出异常格式化器，默认为 DefaultThrowableFormatter
//                .threadFormatter(new MyThreadFormatter())              // 指定线程信息格式化器，默认为 DefaultThreadFormatter
//                .stackTraceFormatter(new MyStackTraceFormatter())      // 指定调用栈信息格式化器，默认为 DefaultStackTraceFormatter
//                .borderFormatter(new MyBoardFormatter())               // 指定边框格式化器，默认为 DefaultBorderFormatter
//                .addObjectFormatter(AnyClass.class,                    // 为指定类添加格式化器
//                        new AnyClassObjectFormatter())                 // 默认使用 Object.toString()
                .build();

        Printer androidPrinter = new AndroidPrinter();             // 通过 android.util.Log 打印日志的打印器
        Printer SystemPrinter = new ConsolePrinter();               // 通过 System.out.println 打印日志的打印器
        Printer filePrinter = new FilePrinter                      // 打印日志到文件的打印器
                .Builder("/sdcard/xlog/")                              // 指定保存日志文件的路径
                .fileNameGenerator(new DateFileNameGenerator())        // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
//                .backupStrategy(new MyBackupStrategy())                // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
//                .logFlattener(new MyLogFlattener())                    // 指定日志平铺器，默认为 DefaultLogFlattener
                .build();
        XLog.init(//LogLevel.ALL,                                    // 指定日志级别，低于该级别的日志将不会被打印
                config,                                                // 指定日志配置，如果不指定，会默认使用 new LogConfiguration.Builder().build()
                androidPrinter,                                        // 添加任意多的打印器。如果没有添加任何打印器，会默认使用 AndroidPrinter
//                SystemPrinter,                                        //如果加上将会使用System.out.p...方式输出。。。
                filePrinter);
    }

}
