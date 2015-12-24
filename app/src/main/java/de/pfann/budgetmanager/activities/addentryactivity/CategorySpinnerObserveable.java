package de.pfann.budgetmanager.activities.addentryactivity;

import de.pfann.budgetmanager.model.Category;


public interface CategorySpinnerObserveable {

    void deleteObserver(final CategorySpinnerObserver aObserver);
    void addObserver(final CategorySpinnerObserver aObserver);
    void notifyObservers(final Category aCategory);
}
