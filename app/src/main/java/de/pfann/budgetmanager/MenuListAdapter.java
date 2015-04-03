package de.pfann.budgetmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by johannes on 02.04.15.
 */
public class MenuListAdapter extends BaseAdapter {
    private Context context;
    private String[] mTitle;
    private int[] mIcon;
    private LayoutInflater inflater;

    public MenuListAdapter(Context pContext, String[] pTitle, int[] pIcon) {
        context = pContext;
        mTitle = pTitle;
        mIcon = pIcon;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.drawer_layout_item, parent, false);

        TextView txtTitle = (TextView) itemView.findViewById(R.id.drawer_layout_item_textview);
        ImageView imgIcon = (ImageView) itemView.findViewById(R.id.drawer_layout_item_icon);

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
