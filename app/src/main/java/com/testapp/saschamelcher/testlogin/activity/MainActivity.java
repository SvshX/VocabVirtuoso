package com.testapp.saschamelcher.testlogin.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;
import com.testapp.saschamelcher.testlogin.fragment.AboutFragment;
import com.testapp.saschamelcher.testlogin.fragment.DuelModeFragment;
import com.testapp.saschamelcher.testlogin.fragment.FriendsFragment;
import com.testapp.saschamelcher.testlogin.fragment.LeaderboardFragment;
import com.testapp.saschamelcher.testlogin.fragment.MyAccountFragment;
import com.testapp.saschamelcher.testlogin.util.NavigationDrawerCallbacks;
import com.testapp.saschamelcher.testlogin.fragment.NavigationDrawerFragment;
import com.testapp.saschamelcher.testlogin.R;
import com.testapp.saschamelcher.testlogin.fragment.RewardsFragment;
import com.testapp.saschamelcher.testlogin.fragment.StudyModeFragment;
import com.testapp.saschamelcher.testlogin.model.FlashcardEngine;
import com.testapp.saschamelcher.testlogin.model.Vocabs;

import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks, StudyModeFragment.PassSet {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private FlashcardEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        // Enable Local Datastore.
//        Parse.enableLocalDatastore(this);

//        ParseUser user = ParseUser.getCurrentUser();
//        user.deleteInBackground();

        ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
        startActivityForResult(builder.build(), 0);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);


        ParseUser user = ParseUser.getCurrentUser();
        String name_preference = user.getString("name");
        String email_preference = user.getEmail();

        SharedPreferences prefs = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", name_preference);
        editor.putString("email", email_preference);
        editor.commit();

        engine = new FlashcardEngine();
        engine.startQuery();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer,
                (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData(name_preference,
                email_preference, BitmapFactory.decodeResource(getResources(),
                R.drawable.avatar));

   }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

      Fragment fragment;

        switch (position)  {
            case 0: //Study Decks
                startDashboard();
                break;

            case 1: //Duel Mode


                GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject fbUser,
                                                    GraphResponse response) {



                                ParseUser parseUser = ParseUser.getCurrentUser();
                                if (fbUser != null && parseUser != null) {
                                    startDuel();
                                }
                                else {
                                    new AlertDialog.Builder(MainActivity.this, R.style.myDialog)
                                            .setTitle("Duel mode")
                                            .setMessage("You must be logged in with Facebook to start a duel! " +
                                                    "Log in with Facebook and challenge your friends!")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            })

                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .show();
                                }

                            }
                        }
                ).executeAsync();

                                            break;

                                            case 2: //Leaderboard
                                            fragment = getFragmentManager().findFragmentByTag(LeaderboardFragment.TAG);
                                            if (fragment == null) {
                                                fragment = new LeaderboardFragment();
                                            }
                                            getFragmentManager().beginTransaction().replace(R.id.container, fragment, LeaderboardFragment.TAG).commit();
                                            break;

                                            case 3: //Rewards
                                            fragment = getFragmentManager().findFragmentByTag(RewardsFragment.TAG);
                                            if (fragment == null) {
                                                fragment = new RewardsFragment();
                                            }
                                            getFragmentManager().beginTransaction().replace(R.id.container, fragment, RewardsFragment.TAG).commit();
                                                break;

                                            case 4: //Friends
                                            fragment = getFragmentManager().findFragmentByTag(FriendsFragment.TAG);
                                            if (fragment == null) {
                                                fragment = new FriendsFragment();
                                            }
                                            getFragmentManager().beginTransaction().replace(R.id.container, fragment, FriendsFragment.TAG).commit();
                                            break;

                                            case 5: //MyAccount
                                            fragment = getFragmentManager().findFragmentByTag(MyAccountFragment.TAG);
                                            if (fragment == null) {
                                                fragment = new MyAccountFragment();
                                            }
                                            getFragmentManager().beginTransaction().replace(R.id.container, fragment, MyAccountFragment.TAG).commit();
                                            break;

                                            case 6: //About
                                            fragment = getFragmentManager().findFragmentByTag(AboutFragment.TAG);
                                            if (fragment == null) {
                                                fragment = new AboutFragment();
                                            }
                                            getFragmentManager().beginTransaction().replace(R.id.container, fragment, AboutFragment.TAG).commit();
                                            break;

                                            default:

                                            fragment = getFragmentManager().findFragmentByTag(StudyModeFragment.TAG);
                                            if (fragment == null) {
                                                fragment = new StudyModeFragment();
                                            }
                                            getFragmentManager().beginTransaction().replace(R.id.container, fragment, StudyModeFragment.TAG).commit();
break;
                                        }

                                    }


                                    @Override
                                    public void onBackPressed () {
                                        if (mNavigationDrawerFragment.isDrawerOpen())
                                            mNavigationDrawerFragment.closeDrawer();
                                        else
                                            super.onBackPressed();
                                    }


                                    @Override
                                    public boolean onCreateOptionsMenu (Menu menu){
                                        if (!mNavigationDrawerFragment.isDrawerOpen()) {
                                            // Only show items in the action bar relevant to this screen
                                            // if the drawer is not showing. Otherwise, let the drawer
                                            // decide what to show in the action bar.
                                            getMenuInflater().inflate(R.menu.main, menu);
                                            return true;
                                        }
                                        return super.onCreateOptionsMenu(menu);
                                    }


                                    @Override
                                    public boolean onOptionsItemSelected (MenuItem item){
                                        // Handle action bar item clicks here. The action bar will
                                        // automatically handle clicks on the Home/Up button, so long
                                        // as you specify a parent activity in AndroidManifest.xml.
                                        int id = item.getItemId();


                                        return super.onOptionsItemSelected(item);
                                    }

    private void startDuel() {
        Fragment fragment;
        fragment = getFragmentManager().findFragmentByTag(DuelModeFragment.TAG);
        if (fragment == null) {
            fragment = new DuelModeFragment();
        }
        getFragmentManager().beginTransaction().replace(R.id.container, fragment, DuelModeFragment.TAG).commit();
    }

    public void startDashboard() {
        Fragment fragment;
        fragment = getFragmentManager().findFragmentByTag(StudyModeFragment.TAG);
        if (fragment == null) {
            fragment = new StudyModeFragment();
        }
        getFragmentManager().beginTransaction().replace(R.id.container,
                fragment, StudyModeFragment.TAG).commit();
    }

    @Override
    public void onSetSelected(int set) {
        SharedPreferences sharedPref = this.getSharedPreferences("preferences",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("VocabSet", set);
        editor.commit();
    }
}