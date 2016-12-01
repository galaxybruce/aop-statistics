package com.galaxybruce.aop.statistics;

/**
 * Created by bruce.zhang
 */
public interface IAopStatistics {

    /**
     * @param bussinessType 业务类型
     * @param eventId 事件ID
     * @param repoParam 传递的参数
     */
    public abstract void reportEvent(String bussinessType, String eventId, String repoParam);

    public abstract void  reportPageOnResume(String bussinessType, String pageId, String eventId, String repoParam);

    public abstract void reportPageOnPause(String bussinessType, String pageId, String eventId, String repoParam);


}
