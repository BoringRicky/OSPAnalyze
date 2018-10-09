package me.ricky.ospanalyze.log;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.ricky.ospanalyze.R;
import me.ricky.ospanalyze.log.timber.TimberActivity;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }

    public void onTimber(View view) {
        startActivity(new Intent(this, TimberActivity.class));
    }
}
