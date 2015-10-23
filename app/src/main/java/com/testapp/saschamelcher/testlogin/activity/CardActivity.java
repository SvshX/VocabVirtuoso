package com.testapp.saschamelcher.testlogin.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.testapp.saschamelcher.testlogin.R;
import com.testapp.saschamelcher.testlogin.fragment.NativeFragment;
import com.testapp.saschamelcher.testlogin.fragment.TranslationFragment;
import com.testapp.saschamelcher.testlogin.model.FlashcardEngine;
import com.testapp.saschamelcher.testlogin.model.Vocabs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saschamelcher on 15/08/15.
 */

public class CardActivity extends AppCompatActivity implements NativeFragment.FragmentCommunicator, TranslationFragment.OnButtonClickListener {

    private Toolbar mToolbar;
    private NativeFragment nativeFragment;
    private TranslationFragment translationFragment;
    private Integer set;
    public List<Vocabs> cardSet;
    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        new AlertDialog.Builder(CardActivity.this, R.style.myDialog)
                .setMessage("It's time to learn new words! - in chunks of 10 at a time. Rate your confidence on each word to optimise your retention by repeating words in a calculated interval of time.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();

        SharedPreferences sharedPref = this.getSharedPreferences("preferences",
                Context.MODE_PRIVATE);
        set = sharedPref.getInt("VocabSet", -1);

        if (findViewById(R.id.fragment_ParentViewGroup) != null) {

            if (savedInstanceState != null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("Set", set);
            nativeFragment = new NativeFragment();
            nativeFragment.setArguments(bundle);
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_ParentViewGroup, nativeFragment, NativeFragment.TAG)
                    .addToBackStack(null)
                    .commit();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentCommunicator(List<Vocabs> cardSet) {

//        translationFragment = (TranslationFragment) getFragmentManager().findFragmentByTag(TranslationFragment.TAG);
//        translationFragment.setBackCard(cardSet);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ParseQuery<Vocabs> query = ParseQuery.getQuery("Vocabs");
        query.whereEqualTo("VocabSet", set);
        query.findInBackground(new FindCallback<Vocabs>() {
            @Override
            public void done(List<Vocabs> list, ParseException e) {
                if (e == null) {
                    cardSet = new ArrayList<Vocabs>();
                    for (Vocabs v : list) {
                        Vocabs newCards = new Vocabs();
                        newCards.setNativeWord(v.getNativeWord());
                        newCards.setTranslation(v.getTranslation());
                        newCards.setSentence(v.getSentence());
                        cardSet.add(newCards);
                    }
                }
                nativeFragment = (NativeFragment) getFragmentManager().findFragmentByTag(NativeFragment.TAG);
                nativeFragment.setFrontCard(cardSet);
            }
        });
    }

    @Override
    public void onButtonClick(List<Vocabs> cardSet) {

            nativeFragment = (NativeFragment) getFragmentManager().findFragmentByTag(NativeFragment.TAG);
            nativeFragment.updateFrontCard(cardSet);
    }

public void startTranslationFragment() {

    translationFragment = new TranslationFragment();
    getFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_ParentViewGroup, translationFragment, TranslationFragment.TAG)
            .addToBackStack(null)
            .commit();

    counter++;

}

    public List<Vocabs> getCardSet() {
        return cardSet;
    }

    public void finishStudying() {

        getFragmentManager()
                .beginTransaction()
                .remove(nativeFragment)
                .commit();
    }

}