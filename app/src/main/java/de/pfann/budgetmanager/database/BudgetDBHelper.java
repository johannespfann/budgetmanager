package de.pfann.budgetmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import de.pfann.budgetmanager.database.tables.CategoryTable;
import de.pfann.budgetmanager.database.tables.EntryTable;
import de.pfann.budgetmanager.database.tables.TagTable;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 26.03.15.
 */
public class BudgetDBHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = "db";
    private static final String DATABASE_NAME = "budget.db";
    private static final int DATABASE_VERSION = 1;


    public BudgetDBHelper(final Context aContext){
        super(aContext, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG_TAG,"Create new tables!");
        Log.i(LOG_TAG, CategoryTable.CREATE_TABLE_CATEGORY_STATEMENT);
        db.execSQL(CategoryTable.CREATE_TABLE_CATEGORY_STATEMENT);
        Log.i(LOG_TAG, EntryTable.CREATE_TABLE_ENTRY_STATEMENT);
        db.execSQL(EntryTable.CREATE_TABLE_ENTRY_STATEMENT);
        Log.i(LOG_TAG, TagTable.CREATE_TABLE_TAG_STATEMENT);
        db.execSQL(TagTable.CREATE_TABLE_TAG_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LOG_TAG,"Deleting all tables!");
        db.execSQL("DROP TABLE IF EXISTS " + EntryTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CategoryTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TagTable.TABLE_NAME);
        Log.i(LOG_TAG,"Deleted tables!");
        onCreate(db);
    }


    public long createCategory(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Category.NAME,name);
        long category_id = db.insert(Category.TABLE_NAME,null,values);
        db.close();
        return category_id;
    }



    public void showAllTables(){
        println(Entry.TABLE_NAME);
        println(Tag.TABLE_NAME);
        println(Category.TABLE_NAME);
    }


    private void println(String tablename){
        String selectQuery = "SELECT * FROM " + tablename;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            String spalteTrennzeichen = " | ";
            String zeileTrennzeichen = "-----------------------------------------";
            String zeile = spalteTrennzeichen;

            for (String name : cursor.getColumnNames()) {
                zeile = zeile + name + spalteTrennzeichen;
            }

            Log.i(LOG_TAG, zeileTrennzeichen);
            Log.i(LOG_TAG, zeile);
            Log.i(LOG_TAG, zeileTrennzeichen);

            do {
                zeile = spalteTrennzeichen;
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    String neu = cursor.getString(i);

                    if (neu == null) {
                        neu = "null";
                    } else {
                        if (neu.length() > 20) {
                            neu = neu.substring(0, 19);
                            neu = neu + "...";
                        }
                    }

                    zeile = zeile + neu;
                    zeile = zeile + spalteTrennzeichen;
                }
                Log.i(LOG_TAG, zeile);
            } while (cursor.moveToNext());
            Log.i(LOG_TAG, zeileTrennzeichen);
        }
        db.close();
    }

}

