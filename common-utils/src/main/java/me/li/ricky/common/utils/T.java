package me.li.ricky.common.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

public class T {
    private static final int DEFAULT_GRAVITY = -1;

    private static Context sContext;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    private T() {
    }

    public static void register(Context context) {
        if (sContext == null) {
            sContext = context.getApplicationContext();
        }
    }

    /** 自定义Toast View */
    public static void makeTextShortCenter(View customView, int gravity) {
        Toast toast = new Toast(sContext);
        toast.setView(customView);
        toast.setDuration(Toast.LENGTH_SHORT);
        if (gravity != DEFAULT_GRAVITY) {
            toast.setGravity(gravity, 0, 0);
        }
        toast.show();
    }

    public static void showShort(final int resId) {
        showShort(resId, DEFAULT_GRAVITY);
    }

    public static void showShort(String message) {
        showShort(message, DEFAULT_GRAVITY);
    }

    public static void showShort(String message, int gravity) {
        show(message, Toast.LENGTH_SHORT, gravity);
    }

    public static void showShort(int messageResId, int gravity) {
        show(messageResId, Toast.LENGTH_SHORT, gravity);
    }

    public static void showLong(int resId) {
        showLong(resId, DEFAULT_GRAVITY);
    }

    public static void showLong(String message) {
        showLong(message, DEFAULT_GRAVITY);
    }

    public static void showLong(int messageResId, int gravity) {
        show(messageResId, Toast.LENGTH_LONG, gravity);
    }

    public static void showLong(String message, int gravity) {
        show(message, Toast.LENGTH_LONG, gravity);
    }

    private static void show(final int messageResId, final int duration, final int gravity) {
        check();

        showOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Toast toast = Toast.makeText(sContext, messageResId, duration);
                if (gravity != DEFAULT_GRAVITY) {
                    toast.setGravity(gravity, 0, 0);
                }
                toast.show();
            }
        });
    }

    private static void show(final String message, final int duration, final int gravity) {
        check();

        showOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Toast toast = Toast.makeText(sContext, message, duration);
                if (gravity != DEFAULT_GRAVITY) {
                    toast.setGravity(gravity, 0, 0);
                }
                toast.show();
            }
        });
    }


    private static void check() {
        if (sContext == null) {
            throw new NullPointerException("Context 为空，请先调用 T.register(Context)");
        }
    }

    private static void showOnUiThread(Runnable action) {
        if (inUiThread()) {
            action.run();
        } else {
            runOnUiThread(action);
        }
    }

    private static boolean inUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static void runOnUiThread(Runnable action) {
        mHandler.post(action);
    }

}
