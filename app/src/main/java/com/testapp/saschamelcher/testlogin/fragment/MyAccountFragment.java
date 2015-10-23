package com.testapp.saschamelcher.testlogin.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SaveCallback;
import com.parse.ui.ParseLoginBuilder;
import com.testapp.saschamelcher.testlogin.R;

/**
 * Created by saschamelcher on 06/08/15.
 */

public class MyAccountFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String TAG = "myAccount";

    EditTextPreference namePreference;
    EditTextPreference emailPreference;
    SwitchPreference switchPreference;
//    ListPreference name, email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);


        namePreference = (EditTextPreference) findPreference("name_preference");
        emailPreference = (EditTextPreference) findPreference("email_preference");
        switchPreference = (SwitchPreference) findPreference("switch_preference");

        ParseUser user = ParseUser.getCurrentUser();
        String name_preference = user.getString("name");
        String email_preference = user.getEmail();

//        SharedPreferences prefs = this.getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        namePreference.setTitle(name_preference);
        emailPreference.setTitle(email_preference);

        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (switchPreference.isChecked()) {
                } else {
                    ParsePush push = new ParsePush();
                    push.setChannel("Push");
                    push.setMessage("It's time to study!");
                    push.sendInBackground();
                }
                return true;
            }
        });

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        updatePreference(key);
    }

    private void updatePreference(String key) {
        if (key.equals("email_preference")) {
            Preference preference = findPreference(key);
            if (preference instanceof EditTextPreference) {
                EditTextPreference editTextPreference = (EditTextPreference) preference;
                if (editTextPreference.getText().trim().length() > 0) {
                    editTextPreference.setTitle(editTextPreference.getText());
                    editTextPreference.setDefaultValue(editTextPreference.getText());

                    ParseUser user = ParseUser.getCurrentUser();
                    user.setEmail(editTextPreference.getText());
                    user.setUsername(editTextPreference.getText());

                    SharedPreferences prefs = this.getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", editTextPreference.getText());
                    editor.commit();

                    user.saveInBackground(new SaveCallback() {
                        public void done(ParseException e) {
                            if (e != null) {
                                // Saved successfully
                            } else {
                                // ParseException
                            }
                        }
                    });

                }
            }
            Toast.makeText(getActivity().getApplicationContext(), "Saved!",
                    Toast.LENGTH_SHORT).show();
        }


        if (key.equals("name_preference")) {
            Preference preference = findPreference(key);
            if (preference instanceof EditTextPreference) {
                EditTextPreference editTextPreference = (EditTextPreference) preference;
                if (editTextPreference.getText().trim().length() > 0) {
                    editTextPreference.setTitle(editTextPreference.getText());
                    editTextPreference.setDefaultValue(editTextPreference.getText());

                    ParseUser user = ParseUser.getCurrentUser();
                    user.put("name", editTextPreference.getText());

                    SharedPreferences prefs = this.getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("name", editTextPreference.getText());
                    editor.commit();

                    user.saveInBackground(new SaveCallback() {
                        public void done(ParseException e) {
                            if (e != null) {
                                // Saved successfully
                            } else {
                                // ParseException
                            }
                        }
                    });

                }
            }
            Toast.makeText(getActivity().getApplicationContext(), "Saved!",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout v = (LinearLayout) super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.pref_layout, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Account Settings");

        Button btn = new Button(getActivity().getApplicationContext());
        btn.setText("Log out");

        v.addView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //create log out function
                ParseUser user = ParseUser.getCurrentUser();
                user.logOut();

                ParseLoginBuilder builder = new ParseLoginBuilder(getActivity().getApplicationContext());
                startActivityForResult(builder.build(), 0);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen()
                .getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen()
                .getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
