package com.testapp.saschamelcher.testlogin.model;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saschamelcher on 30/08/15.
 */

public class FlashcardEngine {

    public List<Vocabs> cardSet;
    public static final int CONFIDENCE5 = 5;
    public static final int CONFIDENCE4 = 4;
    public static final int CONFIDENCE3 = 3;
    public static final int CONFIDENCE2 = 2;
    public static final int CONFIDENCE1 = 1;
    public static final int UNSEEN = 0;

    public FlashcardEngine() {
        cardSet = new ArrayList<Vocabs>();
    }

    public void startQuery() {

        ParseQuery<Vocabs> query = new ParseQuery<Vocabs>("Vocabs");
        query.findInBackground(new FindCallback<Vocabs>() {
            @Override
            public void done(List<Vocabs> list, ParseException e) {

                if (e == null) {
                    ParseObject.pinAllInBackground(list);
                }
            }
        });
    }


    public Vocabs getCard(List<Vocabs> cardSet) {
        Vocabs card = cardSet.get(0);
        return card;
    }

    public void setCardSet(List<Vocabs> cardSet) {
        this.cardSet = cardSet;
    }

}
