package com.testapp.saschamelcher.testlogin.util;

import android.view.View;

import com.testapp.saschamelcher.testlogin.model.Vocabs;

/**
 * Created by saschamelcher on 30/08/15.
 */
public class TapOnClickListener implements View.OnClickListener {

    Vocabs currentCard;
    public TapOnClickListener(int myLovelyVariable) {
        this.currentCard = currentCard;
    }

    @Override
    public void onClick(View v)
    {
//        IntermediateFragment.flipCard(v, currentCard);
    }
}
