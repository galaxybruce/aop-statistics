package com.galaxybruce.aop.statistics;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by bruce.zhang
 */
public class AopStatistics {

    private String mConfigFileName;
    private static Properties mProperties;
    private static List<IAopStatistics> aopStatisticsList = new ArrayList<>(1);

    private AopStatistics(Builder builder) {
        this.mConfigFileName = builder.configFileName;
        AopStatistics.aopStatisticsList = builder.aopStatisticsList;
    }

    public static void reportEvent(String bussinessType, String eventId, String repoParam) {
        for (IAopStatistics iAopStatistics : aopStatisticsList) {
            iAopStatistics.reportEvent(bussinessType, eventId, repoParam);
        }
    }

    public static void reportPageOnResume(String bussinessType, String pageId, String eventId, String repoParam) {
        for (IAopStatistics iAopStatistics : aopStatisticsList) {
            iAopStatistics.reportPageOnResume(bussinessType, pageId, eventId, repoParam);
        }
    }

    public static void reportPageOnPause(String bussinessType, String pageId, String eventId, String repoParam) {
        for (IAopStatistics iAopStatistics : aopStatisticsList) {
            iAopStatistics.reportPageOnPause(bussinessType, pageId, eventId, repoParam);
        }
    }

    public void loadProperties(final Context context) {
        //        InputStream in = null;
        //        Properties props = null;
        //        try {
        //            in = getClass().getResourceAsStream(
        //                    "/org/androidpn/client/client.properties");
        //            if (in != null) {
        //                props = new Properties();
        //                props.load(in);
        //            } else {
        //                Log.e(LOGTAG, "Could not find the properties file.");
        //            }
        //        } catch (IOException e) {
        //            Log.e(LOGTAG, "Could not find the properties file.", e);
        //        } finally {
        //            if (in != null)
        //                try {
        //                    in.close();
        //                } catch (Throwable ignore) {
        //                }
        //        }
        //        return props;

        new Thread(new Runnable() {
            @Override
            public void run() {
                Properties props = new Properties();
                try {
                    String configName = mConfigFileName;
                    if(TextUtils.isEmpty(configName)) configName = "reportpoint";
                    int id = context.getResources().getIdentifier(configName, "raw",
                            context.getPackageName());
                    props.load(context.getResources().openRawResource(id));
                } catch (Exception e) {

                }
                AopStatistics.mProperties = props;
            }
        }).start();
    }

    public static String getPropertyValue(String key)
    {
        return mProperties != null ? mProperties.getProperty(key) : null;
    }

    public static class Builder {
        private String configFileName;
        private List<IAopStatistics> aopStatisticsList;

        public Builder() {
            this.aopStatisticsList = new ArrayList<>();
        }

        public Builder setConfigFileName(String configFileName)
        {
            this.configFileName = configFileName;
            return this;
        }

        public Builder addAopStatistics(IAopStatistics filter) {
            if (aopStatisticsList == null)
                throw new IllegalStateException("aopStatisticsList can not be null");

            this.aopStatisticsList.add(filter);
            return this;
        }

        public AopStatistics build() {
            return new AopStatistics(this);
        }
    }

}
