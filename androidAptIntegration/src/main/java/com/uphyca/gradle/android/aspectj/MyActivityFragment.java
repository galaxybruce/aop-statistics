package com.uphyca.gradle.android.aspectj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class MyActivityFragment extends BaseActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyActivityFragment.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}
