package me.li.ricky.common.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.util.List;

/**
 * LocalBroadcastManager 注册广播，解除广播，发送广播的工具类
 *
 * @author liteng
 */
public class OwnBroadcastManager {

    private Context mContext;
    private LocalBroadcastManager mBroadcastManager;

    private OwnBroadcastManager() {
    }


    public static OwnBroadcastManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void register(Context context) {
        mContext = context.getApplicationContext();
        mBroadcastManager = LocalBroadcastManager.getInstance(mContext);
    }

    public void registerReceiver(BroadcastReceiver receiver, String... actions) {
        requireNonNull(mContext);

        IntentFilter filter = new IntentFilter();
        if (actions != null && actions.length != 0) {
            for (String action : actions) {
                filter.addAction(action);
            }
        }
        mBroadcastManager.registerReceiver(receiver, filter);
    }

    public void registerReceiver(BroadcastReceiver receiver, List<String> actions) {
        requireNonNull(mContext);
        IntentFilter filter = new IntentFilter();
        if (actions != null) {
            for (String action : actions) {
                filter.addAction(action);
            }
        }
        mBroadcastManager.registerReceiver(receiver, filter);
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        requireNonNull(mContext);
        if (receiver == null) {
            return;
        }
        mBroadcastManager.unregisterReceiver(receiver);
    }


    public void sendBroadcast(String action) {
        requireNonNull(mContext);
        Intent intent = new Intent();
        intent.setAction(action);
        mBroadcastManager.sendBroadcast(intent);
    }



    public static <T> T requireNonNull(T obj) {
        if (obj == null) {
            throw new NullPointerException("请先在 Application 里的 onCreate() 调用 OwnBroadcastManager.register(Context) , 对 OwnBroadcastManager 进行注册");
        }
        return obj;
    }


    private static class InstanceHolder {
        private static OwnBroadcastManager INSTANCE = new OwnBroadcastManager();
    }

}
