package de.pfann.budgetmanager.activities.addentryactivity;

/**
 * Created by johannes on 24.04.15.
 */
public interface TagSpinnerObserveable {

    void deleteObserver(final TagSpinnerObserver aObserver);
    void addObserver(final TagSpinnerObserver aObserver);
    void notifyObservers();
}
