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

    private final Command<Void> mNavigateToBalance = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            mEventBus.post(NavigationEvent.Balance);
        }
    };

    private final Command<Void> mNavigateToEntry = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            mEventBus.post(NavigationEvent.Add_Entry);
        }
    };

    private final Command<Void> mNavigateToHistory = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            mEventBus.post(NavigationEvent.History);
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


    public Command<Void> getNavigateToBalance() {
        return mNavigateToBalance;
    }

    public Command<Void> getNavigateToHome() {
        return mNavigateToHome;
    }

    public Command<Void> getNavigateToHistory(){return mNavigateToHistory;}


    public interface Listener {

        void onNavigationDrawerItemSelected(int aItemId);

    }

}
