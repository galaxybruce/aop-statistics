package com.uphyca.gradle.android.aspectj;

import android.app.Application;

import com.kidswant.ss.report.api.ReportDataUtil;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ReportDataUtil.loadProperties(this);
    }

}
