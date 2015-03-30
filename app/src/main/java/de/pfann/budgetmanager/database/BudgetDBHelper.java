package de.pfann.budgetmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.EntryTagTable;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 26.03.15.
 */
public class BudgetDBHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = "db";
    private static final String DATABASE_NAME = "budget.db";
    private static final int DATABASE_VERSION = 1;


    private static final String CREATE_TABLE_ENTRY_STATEMENT = "CREATE TABLE "+ Entry.TABLE_NAME+" ("+Entry.ENTRY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Entry.NAME+ " TEXT NOT NULL," +
            Entry.SUM + " NUMERIC NOT NULL," +
            Entry.TIMESTAMP + " DATETIME DEFAULT CURRENT_DATE," +
            Entry.CATEGORY_ID + " INTEGER," +
            Entry.TAGS + "TEXT," +
            Entry.MEMO + "TEXT," +
            "FOREIGN KEY("+Entry.CATEGORY_ID+") REFERENCES "+ Category.TABLE_NAME+"("+Category.CATEGORY_ID+"));";
    private static final String CREATE_TABLE_CATEGORY_STATEMENT = "CREATE TABLE "+ Category.TABLE_NAME + "("+Category.CATEGORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Category.NAME +" TEXT NOT NULL)";
    private static final String CREATE_TABLE_TAG_STATEMENT = "CREATE TABLE " + Tag.TABLE_NAME + "(" + Tag.TAG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Tag.NAME + " TEXT NOT NULL," +
            Tag.CATEGORY_ID + " INTEGER," +
            "FOREIGN KEY("+Tag.CATEGORY_ID+") REFERENCES " + Category.TABLE_NAME+"("+Category.CATEGORY_ID+"));";





    public BudgetDBHelper(final Context aContext){
        super(aContext, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG_TAG,"Create new tables!");
        Log.i(LOG_TAG,CREATE_TABLE_CATEGORY_STATEMENT);
        db.execSQL(CREATE_TABLE_CATEGORY_STATEMENT);
        Log.i(LOG_TAG,CREATE_TABLE_ENTRY_STATEMENT);
        db.execSQL(CREATE_TABLE_ENTRY_STATEMENT);
        Log.i(LOG_TAG,CREATE_TABLE_TAG_STATEMENT);
        db.execSQL(CREATE_TABLE_TAG_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LOG_TAG,"Deleting all tables!");
        db.execSQL("DROP TABLE IF EXISTS " + Entry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Category.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Tag.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EntryTagTable.TABLE_NAME);
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

    public long createEntry(String name,int sum,long category_id,long[] tags){
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Entry.NAME,name);
        values.put(Entry.SUM,sum);
        values.put(Entry.CATEGORY_ID,category_id);
        long entry_id = db.insert(Entry.TABLE_NAME,null,values);


        for(int index = 0; index < tags.length; index++) {
            ContentValues valuesET = new ContentValues();
            valuesET.put(EntryTagTable.ENTRY_ID, entry_id);
            valuesET.put(EntryTagTable.TAG_ID, tags[index]);
            db.insert(EntryTagTable.TABLE_NAME,null,valuesET);
        }
        db.close();

        return entry_id;
    }

    public long createTag(String name, long aCategory_id){
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tag.NAME,name);
        values.put(Tag.CATEGORY_ID,aCategory_id);
        long category_id = db.insert(Tag.TABLE_NAME,null,values);
        db.close();
        return category_id;
    }

    public void showAllTables(){
        println(Entry.TABLE_NAME);
        println(Tag.TABLE_NAME);
        println(Category.TABLE_NAME);
        println(EntryTagTable.TABLE_NAME);
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

