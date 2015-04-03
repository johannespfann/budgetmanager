package de.pfann.budgetmanager.database.tables;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;

/**
 * Created by johannes on 30.03.15.
 */
public interface EntryTable {

    public static final String TABLE_NAME = "Entry";
    public static final String ENTRY_ID = "entry_id";
    public static final String NAME = "entry_name";
    public static final String SUM = "entry_sum";
    public static final String TIMESTAMP = "entry_timestamp";
    public static final String CATEGORY_ID = "entry_category_id";
    public static final String TAGS = "entry_tags";
    public static final String MEMO = "entry_memo";
    public static final String elementSeperator = "__";

    public static final String CREATE_TABLE_ENTRY_STATEMENT = "CREATE TABLE "+ Entry.TABLE_NAME+" ("+Entry.ENTRY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Entry.NAME+ " TEXT NOT NULL," +
            Entry.SUM + " NUMERIC NOT NULL," +
            Entry.TIMESTAMP + " DATETIME DEFAULT CURRENT_DATE," +
            Entry.CATEGORY_ID + " INTEGER," +
            Entry.TAGS + " TEXT," +
            Entry.MEMO + " TEXT," +
            "FOREIGN KEY("+Entry.CATEGORY_ID+") REFERENCES "+ Category.TABLE_NAME+"("+Category.CATEGORY_ID+"));";
}
