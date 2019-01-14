package me.li.ricky.common.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class T {

    private static Toast sToast;
    private static Context sContext;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private T() {
    }

    public static void register(Context context) {
        sContext = context.getApplicationContext();
    }

    public static void showShort(int resId) {
        check();
        if (toastNull()) {
            sToast = Toast.makeText(sContext, resId, Toast.LENGTH_SHORT);
        }
        sToast.show();
    }

    public static void showShort(String message) {
        check();
        if (toastNull()) {
            sToast = Toast.makeText(sContext, message, Toast.LENGTH_SHORT);
        }
        sToast.show();
    }

    public static void showLong(int resId) {
        check();
        if (toastNull()) {
            sToast = Toast.makeText(sContext, resId, Toast.LENGTH_LONG);
        }
        sToast.show();
    }

    public static void showLong(String message) {
        check();
        if (toastNull()) {
            sToast = Toast.makeText(sContext, message, Toast.LENGTH_LONG);
        }
        sToast.show();
    }

    private static void check() {
        if (sContext == null) {
            throw new NullPointerException("Context 为空，请先调用 T.register(Context)");
        }
    }

    private static boolean toastNull() {
        return sToast == null;
    }

    public boolean inUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public void runOnUiThread(Runnable action) {
        mHandler.post(action);
    }

}
