package me.ricky.ospanalyze.log.timber.trees;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class CrashReportingTree extends Timber.Tree {

    public static final String TAG = "CrashReportingTree";

    @Override
    protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
        Log.e(TAG,"priority = " + priority + " , tag = " + tag + ", message = " + message);
    }

}
