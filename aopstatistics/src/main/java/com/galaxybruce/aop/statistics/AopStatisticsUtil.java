package com.galaxybruce.aop.statistics;

import android.content.Context;

import java.util.Properties;

/**
 * Created by bruce.zhang 2015/11/12.
 */
public class AopStatisticsUtil {

    private static Properties mProperties;

    public static void loadProperties(Context context) {
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

        Properties props = new Properties();
        try {
            int id = context.getResources().getIdentifier("reportpoint", "raw",
                    context.getPackageName());
            props.load(context.getResources().openRawResource(id));
        } catch (Exception e) {

        }
        mProperties = props;
    }

    public static String getPropertyValue(String key)
    {
        return mProperties != null ? mProperties.getProperty(key) : null;
    }

}
