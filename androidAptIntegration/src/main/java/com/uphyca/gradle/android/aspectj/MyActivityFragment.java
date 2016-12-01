package com.uphyca.gradle.android.aspectj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyActivityFragment extends BaseActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyActivityFragment.class);
        context.startActivity(intent);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void requestData() {

    }
}
