package me.ricky.ospanalyze.log.timber;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import me.ricky.ospanalyze.R;
import timber.log.Timber;

public class TimberActivity extends AppCompatActivity {

    private static final int CODE = 0x001;

    private static final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timber);

        requestPermission();


    }

    private void requestPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(PERMISSIONS, CODE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODE:
                if (permissions[0].equals(PERMISSIONS[0])) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        dealPermissionOK();
                    }
                }
                break;
        }
    }

    private void dealPermissionOK() {
        Timber.tag("Timber");

        Timber.e("this is TimberActivity onCreate()");

    }
}
