package me.li.ricky.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.Map;
import java.util.Set;

/**
 * SharedPreferences 工具类，不支持多进程
 * 提供自定义 SharedPreferences 文件的名称
 * <p>
 * 同时提供 putCommit() 和 putApply() 两种方式存储数据
 *
 * @author RickyLi
 */
public class SpUtils {

    private static final String TAG = "SpUtils";

    private static SpUtils sUtils;
    private static Context sContext;
    private static String sCurrentSpName;
    private static SharedPreferences sPreferences;
    private static SharedPreferences.Editor sEditor;

    private SpUtils(Context context) {
        this(context, null);
    }

    private SpUtils(Context context, String spName) {
        sContext = context.getApplicationContext();
        if (TextUtils.isEmpty(spName)) {
            sPreferences = PreferenceManager.getDefaultSharedPreferences(sContext);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sCurrentSpName = PreferenceManager.getDefaultSharedPreferencesName(sContext);
            } else {
                sCurrentSpName = sContext.getPackageName() + "_preferences";
            }
        } else {
            sPreferences = sContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
            sCurrentSpName = spName;
        }

        sEditor = sPreferences.edit();
    }

    public static SpUtils init(Context context) {
        if (sUtils == null) {
            sUtils = new SpUtils(context);
        }

        return sUtils;
    }

    public static SpUtils init(Context context, String spName) {
        if (sUtils == null) {
            sUtils = new SpUtils(context, spName);
        } else if (!TextUtils.equals(sCurrentSpName, spName)) {
            sUtils = new SpUtils(context, spName);
        }

        return sUtils;
    }

    public static String getCurrentSpName() {
        return sCurrentSpName;
    }

    public void clearApply() {
        sEditor.clear().apply();
    }

    public boolean clearCommit() {
        return sEditor.clear().commit();
    }

    public void removeApply(String key) {
        sEditor.remove(key).apply();
    }

    public boolean removeCommit(String key) {
        return sEditor.remove(key).commit();
    }

    public Map<String, ?> getAll() {
        return sPreferences.getAll();
    }

    public String get(String key, String defaultValue) {
        return sPreferences.getString(key, defaultValue);
    }

    public int get(String key, int defaultValue) {
        return sPreferences.getInt(key, defaultValue);
    }

    public boolean get(String key, boolean defaultValue) {
        return sPreferences.getBoolean(key, defaultValue);
    }

    public float get(String key, float defaultValue) {
        return sPreferences.getFloat(key, defaultValue);
    }

    public long get(String key, long defaultValue) {
        return sPreferences.getLong(key, defaultValue);
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sPreferences.getStringSet(key, defaultValue);
    }

    public SpUtils putApply(String key, String value) {
        sEditor.putString(key, value);
        sEditor.apply();
        return sUtils;
    }

    public SpUtils putApply(String key, int value) {
        sEditor.putInt(key, value);
        sEditor.apply();
        return sUtils;
    }

    public SpUtils putApply(String key, boolean value) {
        sEditor.putBoolean(key, value);
        sEditor.apply();
        return sUtils;
    }

    public SpUtils putApply(String key, float value) {
        sEditor.putFloat(key, value);
        sEditor.apply();
        return sUtils;
    }

    public SpUtils putApply(String key, long value) {
        sEditor.putLong(key, value);
        sEditor.apply();
        return sUtils;
    }

    public SpUtils putApply(String key, Set<String> value) {
        sEditor.putStringSet(key, value);
        sEditor.apply();
        return sUtils;
    }

    public boolean putCommit(String key, String value) {
        sEditor.putString(key, value);
        return sEditor.commit();
    }

    public boolean putCommit(String key, int value) {
        sEditor.putInt(key, value);
        return sEditor.commit();
    }

    public boolean putCommit(String key, boolean value) {
        sEditor.putBoolean(key, value);
        return sEditor.commit();
    }

    public boolean putCommit(String key, float value) {
        sEditor.putFloat(key, value);
        return sEditor.commit();
    }

    public boolean putCommit(String key, long value) {
        sEditor.putLong(key, value);
        return sEditor.commit();
    }

    public boolean putCommit(String key, Set<String> value) {
        sEditor.putStringSet(key, value);
        return sEditor.commit();
    }


}
