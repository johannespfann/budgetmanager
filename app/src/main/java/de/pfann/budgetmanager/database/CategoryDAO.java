package de.pfann.budgetmanager.database;


import java.sql.SQLException;
import java.util.List;

import de.pfann.budgetmanager.model.Category;

public interface CategoryDAO {

    void persistCategory(final Category aCategory) throws SQLException;
    void updateCategory(final Category aCategory) throws SQLException;
    Category getCategoryByName(final String aName) throws SQLException;
    List<Category> getCategories() throws SQLException;
    void deleteCategory(final Category aCategory) throws SQLException;
}
