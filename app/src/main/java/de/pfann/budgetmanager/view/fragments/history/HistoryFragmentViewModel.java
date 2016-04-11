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


    @Inject
    public HistoryFragmentViewModel(){
        // Default
    }

    public void deleteEntry(Entry aEntry){
        try {
            mEntryDAO.deleteEntry(aEntry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    }
}
