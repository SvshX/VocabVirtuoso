package com.testapp.saschamelcher.testlogin.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.widget.GameRequestDialog;
import com.testapp.saschamelcher.testlogin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saschamelcher on 06/08/15.
 */

public class DuelModeFragment extends Fragment {

    public static final String TAG = "duel";
    View view;
    private ArrayList<String> content = new ArrayList<>();
    AccessTokenTracker accessTokenTracker;
    GameRequestDialog requestDialog;
    CallbackManager callbackManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_duel, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Duel");
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);

        callbackManager = CallbackManager.Factory.create();
        requestDialog = new GameRequestDialog(getActivity());
        requestDialog.registerCallback(callbackManager, new FacebookCallback<GameRequestDialog.Result>() {
            public void onSuccess(GameRequestDialog.Result result) {
                String id = result.getRequestId();
            }

            public void onCancel() {
            }

            public void onError(FacebookException error) {
            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

            }
        };

        GraphRequestBatch batch = new GraphRequestBatch(
                GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject jsonObject,
                                    GraphResponse response) {
                                // Application code for user
                            }
                        }),
                GraphRequest.newMyFriendsRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONArrayCallback() {
                            @Override
                            public void onCompleted(
                                    JSONArray jsonArray,
                                    GraphResponse response) {
                                // Application code for users friends

                                for(int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                      String name = jsonObject.getString("name");
                                        content.add(name);
                                        Log.d("Friend's List Count", Integer.toString(content.size()));
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        })
        );
        batch.addCallback(new GraphRequestBatch.Callback() {
            @Override
            public void onBatchCompleted(GraphRequestBatch graphRequests) {
                // Application code for when the batch finishes
                Button challengeButton = (Button) getActivity().findViewById(R.id.challButton);
                challengeButton.setOnClickListener(new View.OnClickListener() {

                                                       @Override
                                                       public void onClick(View v) {
                                                           GameRequestContent content = new GameRequestContent.Builder()
                                                                   .setMessage("Come study with me!")
                                                                   .build();
                                                           requestDialog.show(content);
                                                       }
                                                   }


                );
//                Button challengeButton = (Button) getActivity().findViewById(R.id.challengeButton);
//                setListAdapter(new MyListAdapter(getActivity(), R.layout.challenge_list, content));
//                Log.d("Facebook friend:  ", content.toString());
            }
        });

        batch.executeAsync();

        return view;

    }

//    private void onClickRequestButton() {
//        GameRequestContent content = new GameRequestContent.Builder()
//                .setMessage("Come play this level with me")
//                .build();
//        requestDialog.show(content);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
//        @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
////        List<String> content = new ArrayList<>();
////        content.add("list");
////        content.add("list");
////        content.add("list");
////        content.add("list");
////        content.add("list");
////        content.add("list");
////        content.add("list");
////        ListView listView = (ListView) getActivity().findViewById(R.id.listView);
////        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, content));
//    }

//    private class MyListAdapter extends ArrayAdapter<String> {
//
//        private int layout;
////        private final List<String> objects;
////        private final Context context;
//
//
//        public MyListAdapter(Context context, int resource, List<String> objects) {
//            super(context, resource, objects);
//            layout = resource;
////            this.context = context;
////            this.objects = objects;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//
////          ViewHolder mainViewHolder = null;
//ViewHolder holder = null;
//
//            if (convertView == null) {
//
//                LayoutInflater inflater = LayoutInflater.from(getContext());
////                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(layout, parent, false);
//
//
////                TextView fbFriend = (TextView) convertView.findViewById(R.id.fbFriend);
////                Button challengeButton = (Button) convertView.findViewById(R.id.challengeButton);
////                fbFriend.setText(getItem(position));
////                challengeButton.setOnClickListener(new View.OnClickListener() {
////
////                                                                  @Override
////                                                                  public void onClick(View v) {
////                                                                      GameRequestContent content = new GameRequestContent.Builder()
////                                                                              .setMessage("Come study with me!")
////                                                                              .build();
////                                                                      requestDialog.show(content);
////                                                                  }
////                                                              }
////
////
////                );
//holder = new ViewHolder();
////                ViewHolder viewHolder = new ViewHolder();
//                holder.fbFriend = (TextView) convertView.findViewById(R.id.fbFriend);
//
//                holder.challengeButton = (Button) convertView.findViewById(R.id.challengeButton);
////                viewHolder.challengeButton.setOnClickListener(new View.OnClickListener() {
//
////                                                                  @Override
////                                                                  public void onClick(View v) {
////                                                                      Toast.makeText(getContext(), "Button was clicked!" + position, Toast.LENGTH_SHORT).show();
////                                                                  }
////                                                              }
////
////
////                );
//               convertView.setTag(holder);
////
//           }
//            else {
//                holder = (ViewHolder) convertView.getTag();
//            }
////                objects.toArray();
////                String[] s = new String [objects.size()];
////                s = objects.toArray(s);
////                for (String string : s)
//                    holder.fbFriend.setText(getItem(position));
////
//                holder.challengeButton.setOnClickListener(new View.OnClickListener() {
//
//                                                                  @Override
//                                                                  public void onClick(View v) {
//                                                                      GameRequestContent content = new GameRequestContent.Builder()
//                                                                              .setMessage("Come study with me!")
//                                                                              .build();
//                                                                      requestDialog.show(content);
//                                                                  }
//                                                              }
//
//
//                );
//
////            }
//            return convertView;
//        }
//
//        @Override
//        public String getItem(int position) {
//            return content.get(position);
//        }
//
//    }
//
//    public class ViewHolder {
//
//        TextView fbFriend;
//        Button challengeButton;
//    }
//    private NavigationDrawerFragment mNavigationDrawerFragment;
//    private Toolbar mToolbar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_duelmode);
//        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
//        setSupportActionBar(mToolbar);
//
//        // Enable Local Datastore.
//        Parse.enableLocalDatastore(this);
//
//        Parse.initialize(this, "lx4z948Cay8QpQWkX0cWhifVcSABGwynXsHLT3Nz", "Lw5Qne57mftKOCyAcJFOY6e4UG9tMP1scCjqwHx3");
//
//
//        FontHelper.applyFont(this, findViewById(R.id.container),
//                "fonts/Helvetica-Neue-25-Ultra-Light.ttf");
//
//        mNavigationDrawerFragment = (NavigationDrawerFragment)
//                getFragmentManager().findFragmentById(R.id.fragment_drawer);
//
//        // Set up the drawer.
//        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
//        // populate the navigation drawer
//        mNavigationDrawerFragment.setUserData("Sascha Melcher", "saschamelcher@gmail.com", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
//    }
//
//    @Override
//    public void onNavigationDrawerItemSelected(int position) {
//        //  android.app.Fragment fragment;
//        switch (position) {
//            case 0: //Study Decks//
//                startActivity(new Intent(DuelModeActivity.this,
//                        MainActivity.class));
//                break;
//            case 1: //Duel Mode
//       //         mNavigationDrawerFragment.closeDrawer();
//
////
////
////
////
//                break;
//            case 2: //Leaderboard
//                startActivity(new Intent(DuelModeActivity.this,
//                        LeaderboardActivity.class));
//                break;
//            case 3: //Rewards
//                startActivity(new Intent(DuelModeActivity.this,
//                        RewardsActivity.class));
//            case 4: //Friends
//                startActivity(new Intent(DuelModeActivity.this,
//                        FriendsActivity.class));
//                break;
//            case 5: //MyAccount
//                startActivity(new Intent(DuelModeActivity.this,
//                        MyAccountActivity.class));
//                break;
//            case 6: //About
//                startActivity(new Intent(DuelModeActivity.this,
//                        AboutActivity.class));
//
//        }
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        if (mNavigationDrawerFragment.isDrawerOpen())
//            mNavigationDrawerFragment.closeDrawer();
//        else
//            super.onBackPressed();
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main, menu);
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//
//
//        return super.onOptionsItemSelected(item);
//    }
}
