package com.testapp.saschamelcher.testlogin.util;

import android.content.Context;
import android.preference.PreferenceCategory;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.testapp.saschamelcher.testlogin.R;

/**
 * Created by saschamelcher on 12/08/15.
 */

public class MyPreferenceCategory extends PreferenceCategory {

    public MyPreferenceCategory(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public MyPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyPreferenceCategory(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        // It's just a TextView!
        EditText defValue =  (EditText)super.onCreateView(parent);
        defValue.setTextColor(parent.getResources().getColor(R.color.myTextPrimaryColor));

        return defValue;
    }
}
