package com.testapp.saschamelcher.testlogin.util;

import android.graphics.drawable.Drawable;

/**
 * Created by saschamelcher on 05/08/15.
 */
public class NavigationItem {

    private String mText;
 //   private Drawable mDrawable;

    public NavigationItem(String text) {
        mText = text;
    //    mDrawable = drawable;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

//    public Drawable getDrawable() {
//        return mDrawable;
//    }
//
//    public void setDrawable(Drawable drawable) {
//        mDrawable = drawable;
//    }
}
