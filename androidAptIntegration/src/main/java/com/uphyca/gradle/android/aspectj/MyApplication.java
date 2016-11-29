package com.uphyca.gradle.android.aspectj;

import android.app.Application;

import statistics.AopStatisticsUtil;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AopStatisticsUtil.loadProperties(this);
    }

}
