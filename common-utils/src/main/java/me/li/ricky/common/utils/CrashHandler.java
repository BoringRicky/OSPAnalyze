package me.li.ricky.common.utils;

import android.content.Context;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private CrashHandler() {
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private static class InstanceHolder {
        private static CrashHandler INSTANCE = new CrashHandler();
    }

    public static CrashHandler getInstance() {
        return InstanceHolder.INSTANCE;
    }


    public void init(Context context){
        mContext = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
//        getCause(e);  collectDeviceInfo();
        Log.e(TAG,assembleLog(e));
    }


    private String assembleLog(Throwable throwable){
        String cause = getCause(throwable);

        StringBuilder builder = new StringBuilder();
        builder.append("[============================================================================================]");
        builder.append("\n *************");
        String time = getDateFormat().format(new Date(System.currentTimeMillis()));
        builder.append(time).append(" 遇到崩溃，设备信息如下：*************").append("\n");
        Map<String,String> allInfos = DeviceInfo.BuildInfo.allBuildInfos();
        for (Map.Entry<String, String> entry : allInfos.entrySet()) {
            builder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }

        builder.append("*************崩溃信息如下：*************").append("\n");
        builder.append(cause);
        builder.append("\n");
        builder.append("[============================================================================================]");
        return builder.toString();
    }


    private String getCause(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        printWriter.close();
        return writer.toString();
    }


    private SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }


}
