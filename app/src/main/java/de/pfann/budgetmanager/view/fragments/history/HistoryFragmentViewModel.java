package de.pfann.budgetmanager.view.fragments.history;


import android.util.Log;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.database.CategoryDAO;
import de.pfann.budgetmanager.database.EntryDAO;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.viewmodel.common.commands.Command;

public class HistoryFragmentViewModel {

    private Listener mListener;
    private EventBus mEventBus = EventBus.getDefault();

    @Inject
    public CategoryDAO mCategoryDAO;

    @Inject
    public EntryDAO mEntryDAO;


    private final Command<Integer> mDeleteEntryCommand = new Command<Integer>(){

        @Override
        public void execute(Integer parameter) {
            if(mListener != null) {
                try {
                    ListViewItem listViewItem = mListener.getListViewItemByPosition(parameter);
                    mEntryDAO.deleteEntry(listViewItem.getEntry());
                    mListener.removeItemFromListView(listViewItem);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    @Inject
    public HistoryFragmentViewModel(){
        // Default
    }

    public void setListener(Listener aListener) {
        mListener = aListener;
    }

    public Command<Integer> getDeleteEntryCommand() {
        return mDeleteEntryCommand;
    }


    public List<Entry> getAllEntries(){
        try {
            return mEntryDAO.getEntries();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new LinkedList<Entry>();
    }


    public interface Listener{

        void removeItemFromListView(ListViewItem aListViewItem);
        ListViewItem getListViewItemByPosition(int aPosition);

    }
}
