package de.pfann.budgetmanager.activities;


import android.util.Log;

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

    private final Command<Void> mNavigateToFirst = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            mEventBus.post(NavigationEvent.First);
        }
    };

    private final Command<Void> mNavigateToSecond = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            mEventBus.post(NavigationEvent.Second);
        }
    };


    @Inject
    public NavigationDrawerViewModel() {
        mEventBus.register(this);
    }

    public void setListener(Listener aListener) {
        mListener = aListener;
    }

    public Command<Void> getNavigateToSecond() {
        return mNavigateToSecond;
    }

    public Command<Void> getNavigateToFirst() {
        return mNavigateToFirst;
    }

    public Command<Void> getNavigateToHome() {
        return mNavigateToHome;
    }


    public void onEvent(NavigationEvent event) {
        Log.i(MainActivity.TAG,"NavigationDrawerViewModel onEven()");
    }

    public interface Listener {

        void onNavigationDrawerItemSelected(int aItemId);

    }

}
