package com.uphyca.gradle.android.aspectj;

import android.app.Application;
import android.util.Log;

import com.galaxybruce.aop.statistics.AopStatistics;
import com.galaxybruce.aop.statistics.IAopStatistics;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new AopStatistics.Builder().addAopStatistics(new IAopStatistics() {
            @Override
            public void reportEvent(String bussinessType, String eventId, String repoParam) {
                Log.i("aaaaaaaa", "reportEvent: " + bussinessType + "-" + eventId + "-" + repoParam);
            }

            @Override
            public void reportPageOnResume(String bussinessType, String pageId, String eventId, String repoParam) {
                Log.i("aaaaaaaa", "reportPageOnResume: " + bussinessType + "-" + pageId + "-" + eventId + "-" + repoParam);
            }

            @Override
            public void reportPageOnPause(String bussinessType, String pageId, String eventId, String repoParam) {
                Log.i("aaaaaaaa", "reportPageOnPause: " + bussinessType + "-" + pageId  + "-" + eventId + "-" + repoParam);
            }
        }).build().loadProperties(this);
    }

}
