package de.pfann.budgetmanager.view.fragments.home;


import android.util.Log;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.util.events.NavigationEvent;
import de.pfann.budgetmanager.viewmodel.common.commands.Command;

public class HomeFragmentViewModel {

    private Listener mListener;
    private EventBus mEventBus = EventBus.getDefault();

    private Command<Void> mCommandFloatingAddEntryButton = new Command<Void>(){

        @Override
        public void execute(Void aParameter) {
            Log.i(MainActivity.TAG, "pressed FloatingAddEntryButton");
            if(mListener != null){
                mEventBus.post(NavigationEvent.Add_Entry);
            }
        }
    };

    @Inject
    public HomeFragmentViewModel(){
        // Default Constructor
    }

    public void setListener(Listener aListener) {
        mListener = aListener;
    }



    public Command<Void> getCommandFloatingAddEntryButton() {
        return mCommandFloatingAddEntryButton;
    }

    public interface Listener{

    }
}
