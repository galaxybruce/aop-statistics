package com.uphyca.gradle.android.aspectj;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CLinearLayout extends LinearLayout{

    public CLinearLayout(Context context) {
        this(context, null);
    }

    public CLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCKisClick(v);
                doCSecondKisClick(v);
            }
        });
    }

    private void doCKisClick(View v)
    {
//        Log.i("aaaaaaaaaaaa", "CLinearLayout.click");
    }

    private void doCSecondKisClick(View v)
    {
//        Log.i("aaaaaaaaaaaa", "CLinearLayout.doCSecondKisClick");
    }

}
