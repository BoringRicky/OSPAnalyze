package me.ricky.ospanalyze.sp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

import me.li.ricky.common.utils.SpUtil;
import me.ricky.ospanalyze.R;

/**
 * @author liteng
 */
public class SpUtilsActivity extends AppCompatActivity {

    private static final String TAG = "SpUtilsActivity";
    private static final String KEY = TAG;

    private TextView mTvSpName;
    private TextView mTvSpContent;
    private SpUtil mUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_utils);
//        mUtils = SpUtil.init(this);
        mUtils = SpUtil.init(this,"sp_test");
        mTvSpName = findViewById(R.id.tv_sp_name);
        mTvSpContent = findViewById(R.id.tv_sp_content);

        mTvSpName.setText("SharedPreferences 文件名称 ： "+SpUtil.getCurrentSpName());
    }

    public void onPutCommit(View view) {
        mUtils.putCommit(KEY, "putCommit-->111");
    }

    public void onPutApply(View view) {
        mUtils.putApply(KEY, "putApply-->1111");
    }

    public void onGet(View view) {
        Object o = mUtils.get(KEY, null);
        if (o == null) {
            o = "没有值";
        }
        mTvSpContent.setText(o.toString());
    }

    public void onGetAll(View view) {
        Map<String, ?> all = mUtils.getAll();
        mTvSpContent.setText(all.toString());
    }

    public void onRemove(View view) {
        mUtils.removeApply(KEY);
    }


    public void onClear(View view) {
        mUtils.clearApply();
    }
}
