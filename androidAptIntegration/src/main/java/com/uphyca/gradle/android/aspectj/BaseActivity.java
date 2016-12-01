package com.uphyca.gradle.android.aspectj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity implements IInitUI{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData(savedInstanceState);
        if(getLayoutId() > 0) setContentView(getLayoutId());
        initView(null);
        if(checkLogin()) requestData();
    }

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

    /**
     * 判断是否已登录
     * @return
     */
    private boolean checkLogin()
    {
        return true;
    }
}
