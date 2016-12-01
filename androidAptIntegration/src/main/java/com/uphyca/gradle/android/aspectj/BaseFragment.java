package com.uphyca.gradle.android.aspectj;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


public abstract class BaseFragment extends Fragment implements IInitUI{


    protected Context mContext = null;

    protected boolean mViewInit;			//view是否创建
    protected boolean mFirstLoad = true;	//数据第一次加载过

    protected boolean isOnResume = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext = getActivity();
        View rootView = getView();
        if(rootView == null)
        {
            rootView = inflater.inflate(getLayoutId(), null);
        }
        else
        {
            final ViewParent parent = rootView.getParent();
            if(parent instanceof ViewGroup)
            {
                ((ViewGroup) parent).removeView(rootView);
            }
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(savedInstanceState);
        initView(view);
        mViewInit = true;
        requestData();
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

    @Override
    public void onDestroy()
    {
        mViewInit = false;
        mFirstLoad = false;
        super.onDestroy();
    }

    /*********************** 重新登录后的行为 start ************************/
    private Runnable mLoginTask;
    protected void setLoginTask(Runnable loginTask)
    {
        mLoginTask = loginTask;
    }

    private void doLoginTask()
    {
        if(mLoginTask != null)
        {
            Runnable runnable = mLoginTask;
            mLoginTask = null;
            runOnUiThread(runnable);
        }
    }
    /*********************** 重新登录后的行为 end ************************/

    protected void runOnUiThread(Runnable action) {
        if(getActivity() != null)
        {
            getActivity().runOnUiThread(action);
        }
    }

    public void runOnUiThreadDelay(Runnable action, long delay)
    {
        if(getActivity() != null)
        {
            getActivity().getWindow().getDecorView().postDelayed(action, delay);
        }
    }

}