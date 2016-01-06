package de.pfann.budgetmanager.view.fragments.navdrawer;


import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.pfann.budgetmanager.util.events.NavigationEvent;
import de.pfann.budgetmanager.viewmodel.common.commands.Command;

public class NavigationDrawerViewModel {

    private Listener mListener;
    private EventBus mEventBus = EventBus.getDefault();

    private final Command<Void> mNavigateToHome = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            mEventBus.post(NavigationEvent.Home);
        }
    };

    private final Command<Void> mNavigateToCategory = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            mEventBus.post(NavigationEvent.Add_Category);
        }
    };

    private final Command<Void> mNavigateToEntry = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            mEventBus.post(NavigationEvent.Add_Entry);
        }
    };


    @Inject
    public NavigationDrawerViewModel() {
        // Default
    }

    public void setListener(Listener aListener) {
        mListener = aListener;
    }

    public Command<Void> getNavigateToEntry() {
        return mNavigateToEntry;
    }

    public Command<Void> getNavigateToCategory() {
        return mNavigateToCategory;
    }

    public Command<Void> getNavigateToHome() {
        return mNavigateToHome;
    }


    public interface Listener {

        void onNavigationDrawerItemSelected(int aItemId);

    }

}