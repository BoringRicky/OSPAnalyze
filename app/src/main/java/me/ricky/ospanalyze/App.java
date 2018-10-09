package me.ricky.ospanalyze;

import android.app.Application;

import me.ricky.ospanalyze.log.timber.CrashReportingTree;
import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        Timber.plant(new CrashReportingTree());
        Timber.tag("Timber");
    }
}
