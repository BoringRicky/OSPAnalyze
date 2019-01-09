package me.ricky.ospanalyze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.ricky.ospanalyze.log.LogActivity;
import me.ricky.ospanalyze.sp.SpUtilsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogs(View view) {
        startActivity(new Intent(this, LogActivity.class));
    }

    public void onSpUtils(View view) {
        startActivity(new Intent(this, SpUtilsActivity.class));
    }
}
