package de.pfann.budgetmanager.view.fragments.entry;


import android.util.Log;

import java.sql.SQLException;

import javax.inject.Inject;

import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.database.EntryDAO;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.viewmodel.common.commands.Command;

public class AddEntryFragmentViewModel {

    private Listener mListener;

    @Inject
    public EntryDAO mEntryDAO;

    private final Command<Void> mAddNewEntryCommand = new Command<Void>(){
        @Override
        public void execute(Void aParameter) {
            Log.i(MainActivity.TAG,"pressed addNewEntry");
            if(mListener != null){
                try {
                    mEntryDAO.addEntry(mListener.getEntry());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    private final Command<Void> mChanceSignCommand = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            if (mListener != null){
                Log.i(MainActivity.TAG,"pressed chanceSign");
                mListener.chanceSign();
            }

        }
    };

    @Inject
    public AddEntryFragmentViewModel(){
        // Default
    }

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    public Command<Void> getAddNewEntryCommand() {
        return mAddNewEntryCommand;
    }

    public Command<Void> getChanceSignCommand() {
        return mChanceSignCommand;
    }

    public interface Listener{

        Entry getEntry();
        void chanceSign();
    }
}
