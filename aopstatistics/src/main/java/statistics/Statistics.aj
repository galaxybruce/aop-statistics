package com.kidswant.aop.statistics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import java.lang.reflect.Method;

public aspect Statistics {

    /* 点击事件或者流程事件，参数通过CodeSignature反射获取*/
    pointcut doAllListener() : call(* *.*KisListener(..));

    /* Activity 页面事件   within限制具体类型，不然重载方法会多次调用
    pointcut doOnOpen() : execution(protected * *..BaseActivity.onResume()) && within(BaseActivity);
    pointcut doOnClose() : execution(protected * *..BaseActivity.onPause()) && within(BaseActivity);*/

    /* 页面事件 */
    pointcut doOnPage(boolean open) : execution(private void reportPagePoint(boolean)) && args(open);


    after() : doAllListener() {
        String methodName = getMethodPathName(thisJoinPoint);

        CodeSignature codeSignature = (CodeSignature) thisJoinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] parameterValues = thisJoinPoint.getArgs();

        String param = null;
        for (int i = 0; i < parameterNames.length; i++) {
            String name = parameterNames[i];
            if("param".equals(name))
            {
                param = parameterValues[i] != null ? parameterValues[i].toString() : null;
                break;
            }
        }

        reportEvent(methodName, param);
    }

    /*  void around() : doOnOpen() {
        proceed();

        reportPage(thisJoinPoint, true);
     }

    void around() : doOnClose() {
        proceed();

        reportPage(thisJoinPoint, false);
    }*/

    before(boolean open) : doOnPage(open) {
        reportPage(thisJoinPoint, open);
    }

    /** 上报点击事件或者流程事件 **/
    private void reportEvent(String name, String param)
    {
        /** android.util.Log.i("aaaaaaaaaaaa", "reportEvent.name: " + name+ "-"+ param); **/

        String value = com.kidswant.aop.statistics.ReportDataUtil.getPropertyValue(name);
        if(value == null || "".equals(value)) return;

        String[] values = value.split(":");
        if(values.length < 3) return;

        com.kidswant.aop.statistics.AopStatistics.reportEvent(values[0], values[2], param);
    }

    /** 上报页面事件 **/
    private void reportPage(JoinPoint joinPoint, boolean open)
    {
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        String typeName = target.getClass().getName();/*signature.getDeclaringTypeName() 是匹配方法声明的类，是基类里面的*/;
        Object param = getPropertyValue(target, "getReportParam");
        Object flag = getPropertyValue(target, "getReportFlag");
        if(flag != null && !"".equals(flag))
        {
            typeName = typeName + "&" + flag;
        }

       /** android.util.Log.i("aaaaaaaaaaaa", "reportPage.name: " + typeName+ "-"+ param + "-" + open); **/

        String value = com.kidswant.aop.statistics.ReportDataUtil.getPropertyValue(typeName);
        if(value == null || "".equals(value)) return;
        String[] values = value.split(":");
        if(values.length < 3) return;

        if(open)
        {
            com.kidswant.aop.statistics.AopStatistics.reportPageOnResume(values[0], values[1], values[2], (String)param);
        }
        else
        {
            com.kidswant.aop.statistics.AopStatistics.reportPageOnPause(values[0], values[1], values[2], (String)param);
        }
    }

    /** 获取方法名全路径 **/
    private String getMethodPathName(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        String typeName = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        String methodPathName = typeName + "." + methodName;
        return methodPathName;
    }

    /** 反射获取参数值 **/
    private Object getPropertyValue(Object o, String methodName){
       Object value = null;
       try {
           Method m = o.getClass().getMethod(methodName, null);
           value = m.invoke(o, null);
       } catch (Exception e) {
           e.printStackTrace();
       }

       return value;
    }
}
