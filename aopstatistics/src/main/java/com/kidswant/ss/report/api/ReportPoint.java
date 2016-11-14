package com.kidswant.ss.report.api;

import java.io.Serializable;

/**
 * Created by fei on 2015/11/12.
 */
public class ReportPoint implements Serializable {

    /**页面ID**/
    private String pageId;
    /**业务类型**/
    private String bussinessType="001";//默认001
    /**事件ID**/
    private String eventId;
    /***上报参数*/
    private String repoParam;

    public ReportPoint(){

    }

    public ReportPoint(String pageId, String bussinessType, String eventId, String repoParam) {
        this.pageId = pageId;
        this.bussinessType = bussinessType;
        this.eventId = eventId;
        this.repoParam = repoParam;
    }

    public ReportPoint(String pageId) {
        this.pageId = pageId;
    }

    public ReportPoint(String pageId, String bussinessType, String eventId) {
        this.pageId = pageId;
        this.bussinessType = bussinessType;
        this.eventId = eventId;
    }


    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getRepoParam() {
        return repoParam;
    }

    public void setRepoParam(String repoParam) {
        this.repoParam = repoParam;
    }
}
