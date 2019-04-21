package net.qiujuer.italker.push;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.qiujuer.italker.common.app.Activity;

import butterknife.BindView;

public class MainActivity extends Activity {
    @BindView(R.id.txt_test)
    TextView mTestTest;


    @Override
    protected void initWidget() {
        super.initWidget();
        mTestTest.setText("hello");
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
