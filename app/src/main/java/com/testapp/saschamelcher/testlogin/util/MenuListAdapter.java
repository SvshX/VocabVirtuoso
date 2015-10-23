package com.testapp.saschamelcher.testlogin.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapp.saschamelcher.testlogin.R;

/**
 * Created by saschamelcher on 04/08/15.
 */
public class MenuListAdapter extends BaseAdapter {

    private Context context;
    private String[] mTitle;
    private LayoutInflater inflater;

    public MenuListAdapter(Context pContext, String[] pTitle) {
        context = pContext;
        mTitle = pTitle;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        TextView txtTitle = (TextView) itemView.findViewById(R.id.title);


        txtTitle.setText(mTitle[position]);


        return itemView;
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public Object getItem(int position) {
        return mTitle[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
