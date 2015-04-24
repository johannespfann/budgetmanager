package de.pfann.budgetmanager.activities.addentryactivity;

import de.pfann.budgetmanager.model.Category;

/**
 * Created by johannes on 24.04.15.
 */
public interface CategorySpinnerObserver {
    void update(final Category aCategory);
}
