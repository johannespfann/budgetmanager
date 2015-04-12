package de.pfann.budgetmanager.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.pfann.budgetmanager.R;

/**
 * Created by johannes on 11.04.15.
 */
public class NavigationDrawerItemAdapter extends BaseAdapter{
    private Context mContext;
    private String[] mTitle;
    private int[] mIcon;
    private LayoutInflater inflater;

    public NavigationDrawerItemAdapter(Context aContext, String[] aTitle, int[] aIcon){
    mContext = aContext;
        mTitle = aTitle;
        mIcon = aIcon;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);

        TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
        ImageView imgIcon = (ImageView) itemView.findViewById(R.id.icon);

        txtTitle.setText(mTitle[position]);
        imgIcon.setImageResource(mIcon[position]);

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
