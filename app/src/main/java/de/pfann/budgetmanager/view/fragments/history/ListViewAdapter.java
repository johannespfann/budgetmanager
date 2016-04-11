package de.pfann.budgetmanager.view.fragments.history;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.pfann.budgetmanager.R;

public class ListViewAdapter extends ArrayAdapter<ListViewItem> {


    public ListViewAdapter(Context aContext, int aTextViewResourceId, List<ListViewItem> aValues){
        super(aContext, aTextViewResourceId, aValues);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.history_listview_item, null);
        }

        ListViewItem listViewItem = getItem(position);
        listViewItem.setListViewPosition(position);

        if (listViewItem != null) {
            TextView name = (TextView) view.findViewById(R.id.history_fragment_listview_item_name);


            if (name != null) {
                name.setText(listViewItem.getName());
            }
        }

        return view;
    }


}
