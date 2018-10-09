package me.ricky.ospanalyze.log.timber;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.ricky.ospanalyze.BuildConfig;
import timber.log.Timber;

public class CrashReportingTree extends Timber.Tree {

    public static final String TAG = "CrashReportingTree";

    @Override
    protected boolean isLoggable(@Nullable String tag, int priority) {
        if (priority != Log.ERROR) {
            return false;
        }
        return super.isLoggable(tag, priority);
    }

    @Override
    protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
        String path = "Log";
        String fileNameTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        String logTimeStamp = fileNameTimeStamp;
        String fileName = fileNameTimeStamp + "-crash.log";

        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("时间：")
                .append(logTimeStamp)
                .append(" ， 标签：")
                .append(tag)
                .append(" ，Log 内容：")
                .append(message);

        try {
            File file = generateLogFile(path, fileName);

            if (file != null) {
                FileWriter writer = new FileWriter(file, true);
                writer.append(logBuilder);
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error while logging into file : " + e);
        }
    }

    private static File generateLogFile(@NonNull String path, @NonNull String fileName) {
        File file = null;
        if (isSdCard()) {

            File location = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), BuildConfig.APPLICATION_ID + File.separator + path);

            boolean dirExists = true;

            if (!location.exists()) {
                dirExists = location.mkdirs();
            }

            if (dirExists) {
                file = new File(location, fileName);
            }
        }

        return file;
    }

    private static boolean isSdCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

}
