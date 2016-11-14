package com.uphyca.gradle.android.aspectj;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class BaseFragment extends Fragment {

    protected Context mContext = null;
    protected boolean isOnResume = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

      @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){//若要对用户可见
            if(!isOnResume){//当前状态是是没有onresume
                reportPagePoint(true);
                isOnResume = true;
            }
        }else {//若要对用户不可见
            if(isOnResume){//当前状态是可见
                isOnResume = false;
                reportPagePoint(false);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getUserVisibleHint() && !isOnResume){//若当前是对于用户可见，且未onresume
            isOnResume = true;
            reportPagePoint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isOnResume){//若当前是onresume,则pause
            isOnResume = false;
            reportPagePoint(false);
        }
    }

    private void reportPagePoint(boolean open){};

    /*** 界面埋点参数*/
    public String getReportParam()
    {
        return null;
    }

    /*** 界面埋点flag 有些界面埋点是动态的 根据参数判断*/
    public String getReportFlag()
    {
        return "flagXXX";
    }

}