package de.pfann.budgetmanager.view.fragments.navigationdrawer;


import de.greenrobot.event.EventBus;
import de.pfann.budgetmanager.viewmodel.common.commands.Command;

public class NavigationDrawerViewModel {

    private Listener mListener;
    private EventBus mEventBus;




    public NavigationDrawerViewModel(){
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }

    public void setListener(final Listener aListener){
        mListener = aListener;
    }


    public interface Listener{

        void onNavigationDrawerItemSelected(final int aItemID);
    }
}
