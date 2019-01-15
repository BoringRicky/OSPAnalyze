package me.ricky.ospanalyze;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

import me.li.ricky.common.utils.DeviceInfo;
import me.li.ricky.common.utils.FileUtil;
import me.li.ricky.common.utils.T;
import me.ricky.ospanalyze.log.LogActivity;
import me.ricky.ospanalyze.sp.SpUtilsActivity;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv_content);

    }

    public void onLogs(View view) {
        startActivity(new Intent(this, LogActivity.class));
    }

    public void onSpUtils(View view) {
        startActivity(new Intent(this, SpUtilsActivity.class));
    }

    public void onDeviceInfo(View view) {
        StringBuilder builder = new StringBuilder();
        builder.append("Board = ").append(DeviceInfo.BuildInfo.getBoard()).append("\n");
        builder.append("RELEASE = ").append(DeviceInfo.BuildInfo.getOSVersion()).append("\n");
        builder.append("Device = ").append(DeviceInfo.BuildInfo.getDevice()).append("\n");
        builder.append("Manufacturer = ").append(DeviceInfo.BuildInfo.getManufacturer()).append("\n");
        builder.append("BootLoader = ").append(DeviceInfo.BuildInfo.getBootLoader()).append("\n");
        builder.append("Type = ").append(DeviceInfo.BuildInfo.getType()).append("\n");
        builder.append("Sdk Version= ").append(DeviceInfo.BuildInfo.getSdkVersion()).append("\n");
        builder.append("Model= ").append(DeviceInfo.BuildInfo.getModel()).append("\n");
        builder.append("RadioVersion= ").append(DeviceInfo.BuildInfo.getRadioVersion()).append("\n");
        builder.append("DisPlay= ").append(DeviceInfo.BuildInfo.getDisplay()).append("\n");
        builder.append("FingerPrint= ").append(DeviceInfo.BuildInfo.getFingerPrint()).append("\n");
        builder.append("CpuABI= ").append(DeviceInfo.BuildInfo.getCpuABI()).append("\n");
        builder.append("CpuABI2= ").append(DeviceInfo.BuildInfo.getCpuABI2()).append("\n");
        builder.append("Hardware= ").append(DeviceInfo.BuildInfo.getHardware()).append("\n");
        builder.append("Host= ").append(DeviceInfo.BuildInfo.getHost()).append("\n");
        builder.append("Product= ").append(DeviceInfo.BuildInfo.getProduct()).append("\n");
        builder.append("TAGs= ").append(DeviceInfo.BuildInfo.getTags()).append("\n");
        builder.append("USER= ").append(DeviceInfo.BuildInfo.getUser()).append("\n");
        builder.append("ID= ").append(DeviceInfo.BuildInfo.getId()).append("\n");

        String[] abis = DeviceInfo.BuildInfo.getSupportedABIS();
        if (abis != null && abis.length > 0) {
            for (String abi : abis) {
                builder.append("getSupportedABIS = ").append(abi).append("\n");
            }
        }

        builder.append("\n");

        builder.append("AndroidId=").append(DeviceInfo.getAndroidId(this)).append("\n");
        builder.append("Default Language=").append(DeviceInfo.getDefaultLanguage()).append("\n");
        builder.append("Mac Address=").append(DeviceInfo.getMacAddress(this)).append("\n");
        builder.append("Net Type=").append(DeviceInfo.getNetType(this)).append("\n");

        builder.append("\n");

        Map<String,String> allInfos = DeviceInfo.BuildInfo.allBuildInfos();
        for (Map.Entry<String, String> entry : allInfos.entrySet()) {
            builder.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        }

        String filePath = "/a/b/c/d/abc.txt";
        builder.append(FileUtil.getFileSuffix(filePath));

        mTextView.setText(builder.toString());
    }

    public void onToast(View view) {
        T.showLong("Toast show long");
    }
}
