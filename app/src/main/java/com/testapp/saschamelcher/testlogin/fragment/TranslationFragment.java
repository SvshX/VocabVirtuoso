package com.testapp.saschamelcher.testlogin.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.testapp.saschamelcher.testlogin.R;
import com.testapp.saschamelcher.testlogin.activity.CardActivity;
import com.testapp.saschamelcher.testlogin.model.Vocabs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saschamelcher on 17/08/15.
 */


public class TranslationFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "translate";
    View view;
    TextView translationCard, sentence;
    private Button confButton1;
    private Button confButton2;
    private Button confButton3;
    private Button confButton4;
    private Button confButton5;
    public List<Vocabs> cardSet;
    public static int backStackCounter = 0;
    OnButtonClickListener onClickCallback;

    public TranslationFragment() {

    }

    public interface OnButtonClickListener {
        void onButtonClick(List<Vocabs> cardSet);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_translation, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Studying");

        CardActivity activity = (CardActivity) getActivity();
        cardSet = activity.getCardSet();

        translationCard = (TextView) view.findViewById(R.id.translationCard);
        sentence = (TextView) view.findViewById(R.id.sentence);

        confButton1 = (Button) view.findViewById(R.id.confButton1);
        confButton1.setOnClickListener(this);

        confButton2 = (Button) view.findViewById(R.id.confButton2);
        confButton2.setOnClickListener(this);

        confButton3 = (Button) view.findViewById(R.id.confButton3);
        confButton3.setOnClickListener(this);

        confButton4 = (Button) view.findViewById(R.id.confButton4);
        confButton4.setOnClickListener(this);

        confButton5 = (Button) view.findViewById(R.id.confButton5);
        confButton5.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        rateCard(v);

    }

    public void rateCard(View v) {

        switch (v.getId()) {
            case R.id.confButton1:
                nextCard();
                break;

            case R.id.confButton2:
                nextCard();
                break;

            case R.id.confButton3:
                nextCard();
                break;

            case R.id.confButton4:
                nextCard();
                break;

            case R.id.confButton5:
                nextCard();
                break;

            default:
                break;
        }
    }

    private void nextCard() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate();
        }
            onClickCallback.onButtonClick(cardSet);
            backStackCounter++;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onClickCallback = (OnButtonClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonClickListener");
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        if (CardActivity.counter - 1 <= cardSet.size()) {
            setBackCard(cardSet);
        }

        else {
            Toast.makeText(getActivity(), "All cards have been studied!", Toast.LENGTH_LONG).show();
        }
    }

    public void setBackCard(List<Vocabs> cardSet) {
        translationCard.setText(cardSet.get(CardActivity.counter - 1).getTranslation());
        sentence.setText(cardSet.get(CardActivity.counter - 1).getSentence());
    }

//    public void updateBackCard(List<Vocabs> cardSet) {
//        currentCard++;
//        translationCard.setText(cardSet.get(CardActivity.counter - 1).getTranslation());
//        sentence.setText(cardSet.get(CardActivity.counter - 1).getSentence());
//    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
