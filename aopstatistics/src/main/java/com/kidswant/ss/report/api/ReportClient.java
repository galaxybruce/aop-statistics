package com.kidswant.ss.report.api;

import android.util.Log;

/**
 * Created by fei on 2015/11/12.
 */
public class ReportClient {

    /**
     * @param bussinessType 业务类型
     * @param eventId 事件ID
     * @param repoParam 传递的参数
     */
    public static void reportEvent(String bussinessType, String eventId, String repoParam){
        Log.i("aaaaaaaaaaaa", "reportEvent: " + bussinessType + "---"+ eventId + "---"+ repoParam);
    }

    public static void reportPage(String bussinessType, String pageId, String eventId, String repoParam, boolean open){
        ReportPoint point = new ReportPoint(pageId, bussinessType, eventId, repoParam);
        if(open)
        {
            reportOnResume(point);
        }
        else
        {
            reportPageOnPause(point);
        }
    }

    public static void  reportOnResume(ReportPoint point){
        Log.i("aaaaaaaaaaaa", "reportOnResume: " + point.getBussinessType() + "---" + point.getPageId() + "---"+ point.getEventId() + "---"+ point.getRepoParam());
    }

    public static void reportPageOnPause(ReportPoint point){
        Log.i("aaaaaaaaaaaa", "reportPageOnPause: " + point.getBussinessType() + "---" + point.getPageId() + "---"+ point.getEventId() + "---"+ point.getRepoParam());
    }


}
