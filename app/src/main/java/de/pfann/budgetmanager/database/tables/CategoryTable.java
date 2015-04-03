package de.pfann.budgetmanager.database.tables;

import de.pfann.budgetmanager.model.Category;

/**
 * Created by johannes on 30.03.15.
 */
public interface CategoryTable {

    public static final String TABLE_NAME = "Category";
    public static final String CATEGORY_ID = "category_id";
    public static final String NAME = "category_name";

    public static final String CREATE_TABLE_CATEGORY_STATEMENT = "CREATE TABLE "+ Category.TABLE_NAME + "("+Category.CATEGORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Category.NAME +" TEXT NOT NULL)";

}
