package de.pfann.budgetmanager.view.fragments.history;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.view.common.bindings.ListViewCommandBinding;
import de.pfann.budgetmanager.view.fragments.BaseFragment;

public class HistoryFragment extends BaseFragment implements HistoryFragmentViewModel.Listener {

    @Inject
    public HistoryFragmentViewModel mViewModel;

    @Bind(R.id.history_fragment_listview)
    public ListView mListView;

    private ArrayAdapter<ListViewItem> mListViewAdapter;

    @Override
    public void onStart() {
        super.onStart();
        new ListViewCommandBinding().bind(mListView,mViewModel.getDeleteEntryCommand());
        mViewModel.setListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        List<ListViewItem> listViewItem = convertToListViewItems( mViewModel.getAllEntries());
        ListViewAdapter adapter = new ListViewAdapter(((MainActivity) getActivity()).getApplicationContext(),R.layout.history_listview_item,listViewItem);
        mListViewAdapter = adapter;
        mListView.setAdapter(adapter);

    }

    private List<ListViewItem> convertToListViewItems(List<Entry> allEntries) {
        List<ListViewItem> listVIewItems = new ArrayList<>();
        for(Entry entry : allEntries){
            listVIewItems.add(new ListViewItem(entry));
        }
        return listVIewItems;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.history_fragment, container, false);
    }


    @Override
    public void removeItemFromListView(ListViewItem aListViewItem) {
        mListViewAdapter.remove(aListViewItem);
        mListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public ListViewItem getListViewItemByPosition(int aPosition) {
        return (ListViewItem)mListView.getItemAtPosition(aPosition);
    }
}
