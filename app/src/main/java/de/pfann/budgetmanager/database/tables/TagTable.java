package de.pfann.budgetmanager.database.tables;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 30.03.15.
 */
public interface TagTable {

    public static final String TABLE_NAME = "Tag";
    public static final String TAG_ID = "tag_id";
    public static final String NAME = "tag_name";
    public static final String CATEGORY_ID = "tag_category_id";

    public static final String CREATE_TABLE_TAG_STATEMENT = "CREATE TABLE " + Tag.TABLE_NAME + "(" + Tag.TAG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Tag.NAME + " TEXT NOT NULL," +
            Tag.CATEGORY_ID + " INTEGER," +
            "FOREIGN KEY("+Tag.CATEGORY_ID+") REFERENCES " + Category.TABLE_NAME+"("+Category.CATEGORY_ID+"));";

}
