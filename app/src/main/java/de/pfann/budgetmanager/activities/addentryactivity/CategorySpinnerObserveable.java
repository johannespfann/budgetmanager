package de.pfann.budgetmanager.activities.addentryactivity;

import de.pfann.budgetmanager.model.Category;

/**
 * Created by johannes on 24.04.15.
 */
public interface CategorySpinnerObserveable {

    void deleteObserver(final CategorySpinnerObserver aObserver);
    void addObserver(final CategorySpinnerObserver aObserver);
    void notifyObservers(final Category aCategory);
}
