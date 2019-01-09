package me.ricky.ospanalyze.log.timber;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.ricky.ospanalyze.App;
import me.ricky.ospanalyze.R;
import me.ricky.ospanalyze.utils.PermissionUtils;
import me.ricky.ospanalyze.utils.UIHelper;
import timber.log.Timber;

/**
 * @author liteng
 */
public class TimberActivity extends AppCompatActivity {

    private static final int CODE = 0x001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timber);

        requestPermission();

    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(App.P.PERMISSIONS, CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODE:

                boolean isStorePermissionOk =  PermissionUtils.isStorePermissionOk(permissions,grantResults);
                if (isStorePermissionOk) {
                    dealStorePermissionOk();
                }

                break;
            default:
                break;
        }
    }

    private void dealStorePermissionOk() {
        UIHelper.toast("可以访问存储设备");
    }

    public void printLog(View view) {
        Timber.d("this is TimberActivity onCreate()");
    }
}
