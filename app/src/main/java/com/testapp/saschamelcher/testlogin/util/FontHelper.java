package com.testapp.saschamelcher.testlogin.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.testapp.saschamelcher.testlogin.R;
import com.testapp.saschamelcher.testlogin.model.Vocabs;

import java.util.List;

/**
 * Created by saschamelcher on 04/08/15.
 */

public class FontHelper {

    private static final String TAG = FontHelper.class.getSimpleName();

    /**
     * Apply specified font for all text views (including nested ones) in the
     * specified root view.
     *
     * @param context
     *            Context to fetch font asset.
     * @param root
     *            Root view that should have specified font for all it's nested
     *            text views.
     * @param fontPath
     *            Font path related to the assets folder (e.g.
     *            "fonts/YourFontName.ttf").
     */
    public static void applyFont(final Context context, final View root,
                                 final String fontPath) {
        try {
            if (root instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) root;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++)
                    applyFont(context, viewGroup.getChildAt(i), fontPath);
            } else if (root instanceof TextView)
                ((TextView) root).setTypeface(Typeface.createFromAsset(
                        context.getAssets(), fontPath));
        } catch (Exception e) {
            Log.e(TAG, String.format(
                    "Error occured when trying to apply %s font for %s view",
                    fontPath, root));
            e.printStackTrace();
        }
    }

    /**
     * Created by saschamelcher on 16/08/15.
     */

    public static class ListViewAdapter extends BaseAdapter
    {
        public List<Vocabs> mVocabs;
        Activity activity;

        public ListViewAdapter(Activity activity, List<Vocabs> mVocabs) {
            super();
            this.activity = activity;
            this.mVocabs = mVocabs;
        }



        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mVocabs.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mVocabs.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        private class ViewHolder {
            TextView txtFirst;
            TextView txtSecond;
            TextView txtThird;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            // TODO Auto-generated method stub
            ViewHolder holder;
            LayoutInflater inflater =  activity.getLayoutInflater();

            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.card_list, null);
                holder = new ViewHolder();
                holder.txtFirst = (TextView) convertView.findViewById(R.id.naitveWord);
                holder.txtSecond = (TextView) convertView.findViewById(R.id.translation);
                holder.txtThird = (TextView) convertView.findViewById(R.id.sentence);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            Vocabs card = mVocabs.get(position);
    //        holder.txtFirst.setText(mVocabs.get());
    //        HashMap map = list.get(position);
    //        holder.txtFirst.setText(map.put(nWords));
    //        holder.txtSecond.setText(map.get(SECOND_COLUMN));
    //        holder.txtThird.setText(map.get(THIRD_COLUMN));


            return convertView;
        }

    }
}
