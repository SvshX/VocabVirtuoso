package com.testapp.saschamelcher.testlogin.activity;

import com.parse.ui.ParseLoginDispatchActivity;

/**
 * Created by saschamelcher on 23/08/15.
 */

public class DispatchActivity extends ParseLoginDispatchActivity {

    @Override
    protected Class<?> getTargetClass() {
        return MainActivity.class;

    }
}