package com.uphyca.gradle.android.aspectj;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MyActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        TextView.class.cast(findViewById(R.id.greeting)).setText("greeting");
        TextView.class.cast(findViewById(R.id.greeting)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doActivityKisListener(v);
            }
        });

        TextView.class.cast(findViewById(R.id.greeting1)).setOnClickListener(this);
    }

    private void doActivityKisListener(View v)
    {
        MyActivityFragment.startActivity(MyActivity.this);
    }

    private void doActivityParamKisListener(View v, String param)
    {

    }

    @Override
    public void onClick(View v) {
        doActivityParamKisListener(v, "param------");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
