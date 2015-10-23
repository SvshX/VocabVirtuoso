package com.testapp.saschamelcher.testlogin.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.sax.Element;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.testapp.saschamelcher.testlogin.R;
import com.testapp.saschamelcher.testlogin.activity.CardActivity;
import com.testapp.saschamelcher.testlogin.activity.MainActivity;
import com.testapp.saschamelcher.testlogin.model.FlashcardEngine;
import com.testapp.saschamelcher.testlogin.model.Vocabs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by saschamelcher on 17/08/15.
 */

public class NativeFragment extends Fragment implements View.OnClickListener {

    FragmentCommunicator listCallback;
    CardActivity cardActivity;
    public static final String TAG = "native";
    View view;
    private TextView nativeCard;
    Button tapButton;
    public List<Vocabs> cardSet;
    public Integer currentCard = 0;
    public Integer set;


    public NativeFragment() {

    }

    public interface FragmentCommunicator {
        void onFragmentCommunicator(List<Vocabs> cardSet);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_native, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Studying");

        CardActivity activity = (CardActivity) getActivity();
        cardSet = activity.getCardSet();

        tapButton = (Button) view.findViewById(R.id.tapButton);
        tapButton.setOnClickListener(this);

        nativeCard = (TextView) view.findViewById(R.id.nativeCard);
        set = getArguments().getInt("Set");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        flipCard(v);
//        listCallback.onFragmentCommunicator(cardSet);

    }

    public void flipCard(View v) {

        switch (v.getId()) {
            case R.id.tapButton:
                showBackCard();

                break;

            default:
                break;
        }
    }

    public void setFrontCard(List<Vocabs> cardSet) {
            nativeCard.setText(cardSet.get(this.currentCard).getNativeWord());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listCallback = (FragmentCommunicator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentCommunicator");
        }
    }

    public void updateFrontCard(List<Vocabs> cardSet) {

        if (currentCard + 1 < cardSet.size()) {
            currentCard++;
            nativeCard.setText(cardSet.get(this.currentCard).getNativeWord());
        }
        else {
            nativeCard.setText(cardSet.get(this.currentCard).getNativeWord());
            Toast.makeText(getActivity(), "All cards have been studied!", Toast.LENGTH_LONG).show();
//            cardActivity.finishStudying();
        }
    }

    private void showBackCard() {
        ((CardActivity)getActivity()).startTranslationFragment();
//        listCallback.onFragmentCommunicator(cardSet);

    }

}
