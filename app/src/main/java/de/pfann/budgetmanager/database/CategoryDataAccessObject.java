package de.pfann.budgetmanager.database;

import de.pfann.budgetmanager.model.Category;

/**
 * Created by johannes on 28.03.15.
 */
public interface CategoryDataAccessObject {

    void createCategory(final String aName);

    Category getCategoryById(final long aId);
    Category getCategoryByName(final String aName);

    void deleteCategory(final Category aCategory);
}
