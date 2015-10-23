package com.testapp.saschamelcher.testlogin.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.parse.Parse;
import com.parse.ui.ParseLoginBuilder;
import com.testapp.saschamelcher.testlogin.R;

/**
 * Created by saschamelcher on 06/08/15.
 */
public class AboutFragment extends Fragment {

    public static final String TAG = "about";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("About Us");
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
