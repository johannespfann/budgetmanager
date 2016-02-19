package de.pfann.budgetmanager.view.fragments.entry;


import android.util.Log;

import javax.inject.Inject;

import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.database.EntryDAO;
import de.pfann.budgetmanager.viewmodel.common.commands.Command;

public class AddEntryFragmentViewModel {

    private Listener mListener;

    @Inject
    public EntryDAO mEntryDAO;

    private final Command<Void> mAddNewEntryCommand = new Command<Void>(){
        @Override
        public void execute(Void aParameter) {
            if(mListener != null){
                Log.i(MainActivity.TAG,"pressed addNewEntry");
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



    public interface Listener{

    }
}
