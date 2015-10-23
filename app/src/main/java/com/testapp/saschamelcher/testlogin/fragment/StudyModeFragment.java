package com.testapp.saschamelcher.testlogin.fragment;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.testapp.saschamelcher.testlogin.R;
import com.testapp.saschamelcher.testlogin.activity.CardActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saschamelcher on 07/08/15.
 */

public class StudyModeFragment extends ListFragment {

    PassSet setCallback;
    public static final String TAG = "study";

    public interface PassSet {
        void onSetSelected(int set);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_study, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Dashboard");

        String[] values = new String[] { "Vocab Set 1", "Vocab Set 2", "Vocab Set 3",
                "Vocab Set 4", "Vocab Set 5" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this.getActivity(),
                android.R.layout.simple_list_item_1, list);
        setListAdapter(adapter);


return rootView;

    }


    @Override
    public void onListItemClick(ListView listview, View view, int position, long id) {

        switch (position) {
            case 0:
                setCallback.onSetSelected(position + 1);
                startCardActivity();
                break;

            case 1:
                setCallback.onSetSelected(position + 1);
                startCardActivity();
                break;

            case 2:
                setCallback.onSetSelected(position + 1);
                startCardActivity();
                break;

            case 3:
                setCallback.onSetSelected(position + 1);
                startCardActivity();
                break;

            case 4:
                setCallback.onSetSelected(position + 1);
                startCardActivity();
                break;

            default:
                break;
        }
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            setCallback = (PassSet) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement PassSet");
        }
    }


    public void startCardActivity() {
        Intent intent = new Intent(getActivity(), CardActivity.class);
        startActivity(intent);

    }

    }

