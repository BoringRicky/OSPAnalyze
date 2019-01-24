package me.ricky.ospanalyze;

import android.Manifest;
import android.app.Application;
import android.content.Context;

import me.li.ricky.common.utils.CrashHandler;
import me.li.ricky.common.utils.SpUtil;
import me.li.ricky.common.utils.T;
import me.ricky.ospanalyze.log.timber.trees.CrashReportingTree;
import me.ricky.ospanalyze.log.timber.trees.DebugTree;
import timber.log.Timber;

public class App extends Application {
    public interface P {
        String STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

        String[] PERMISSIONS = {STORAGE};
    }

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        if (sContext == null) {
            sContext = getApplicationContext();
        }

        Timber.plant(new DebugTree());
        Timber.plant(new CrashReportingTree());

        CrashHandler.getInstance();

        T.register(sContext);
        SpUtil.register(sContext);

    }

    public static Context getContext() {
        return sContext;
    }
}
