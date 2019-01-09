package me.ricky.ospanalyze.utils;

import android.content.pm.PackageManager;

import me.ricky.ospanalyze.App;

public class PermissionUtils {

    public static boolean isStorePermissionOk(String[] permissions, int[] grantResults) {
        int storage = Arrays.contains(permissions, App.P.STORAGE);
        if (storage > -1) {
            if (grantResults[storage] == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }

        return false;
    }

}
