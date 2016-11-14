package com.uphyca.gradle.android.aspectj;

import android.support.v7.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        reportPagePoint(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        reportPagePoint(false);
    }

    private void reportPagePoint(boolean open){};

    /*** 界面埋点参数*/
    public String getReportParam()
    {
        return "paramXXX";
    }

    /*** 界面埋点flag 有些界面埋点是动态的 根据参数判断*/
    public String getReportFlag()
    {
        return "1";
    }
}
