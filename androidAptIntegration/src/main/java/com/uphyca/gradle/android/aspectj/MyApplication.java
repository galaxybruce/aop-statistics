package com.uphyca.gradle.android.aspectj;

import android.app.Application;

import statistics.ReportDataUtil;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ReportDataUtil.loadProperties(this);
    }

}
