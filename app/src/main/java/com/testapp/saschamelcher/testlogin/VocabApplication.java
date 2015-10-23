package com.testapp.saschamelcher.testlogin;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.testapp.saschamelcher.testlogin.model.Vocabs;

/**
 * Created by saschamelcher on 23/08/15.
 */

public class VocabApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Vocabs.class);
        Parse.enableLocalDatastore(this);
        // Required - Initialize the Parse SDK
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));


        ParseInstallation.getCurrentInstallation().addUnique("channels", "Push");
        ParseInstallation.getCurrentInstallation().saveInBackground();


        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        ParseFacebookUtils.initialize(this);

    }
}
