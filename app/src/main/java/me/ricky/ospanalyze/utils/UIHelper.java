package me.ricky.ospanalyze.utils;

import android.widget.Toast;

import me.ricky.ospanalyze.App;

public class UIHelper {

    public static void toast(String msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_LONG).show();
    }

}
