package de.pfann.budgetmanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 28.03.15.
 */
public class EntryMapper {

    private static final String LOG_TAG = "asdf";


    private BudgetDBHelper mBudgetDbHelper;
    private SQLiteDatabase mSQLiteDb;


    public EntryMapper(final BudgetDBHelper aBudgetHelper){
        mBudgetDbHelper = aBudgetHelper;
    }

    private void openReadAbleDB(){
        mSQLiteDb = mBudgetDbHelper.getReadableDatabase();
    }

    private void openWriteAbleDB(){
        mSQLiteDb = mBudgetDbHelper.getWritableDatabase();
    }

    private void closeDB(){
        mSQLiteDb.close();
    }

    public Entry persistEntry(final String aName, final double aSum, final Category aCategory) {
        ContentValues values = new ContentValues();
        values.put(Entry.NAME,aName);
        values.put(Entry.SUM,aSum);
        values.put(Entry.CATEGORY_ID,aCategory.getId());
        long entryId = mSQLiteDb.insert(Entry.TABLE_NAME,null,values);
        Entry newEntry = new Entry();
        newEntry.setId(entryId);
        newEntry.setName(aName);
        newEntry.setSum(aSum);
        newEntry.setCategory(aCategory);
        newEntry.setTags(new ArrayList<Tag>());
        return newEntry;
    }

    public void deleteEntry(final Entry aEntry) {
        mSQLiteDb.delete(Entry.TABLE_NAME,Entry.ENTRY_ID + " = " + aEntry.getId(), null);
    }

    public void updateEntry(final Entry aEntry) {
        ContentValues values = new ContentValues();
        values.put(Entry.NAME,aEntry.getName());
        values.put(Entry.SUM,aEntry.getSum());
        values.put(Entry.CATEGORY_ID,aEntry.getCategoryId());
        mSQLiteDb.update(Entry.TABLE_NAME,values,Entry.ENTRY_ID + " = " + aEntry.getId(),null);
    }


    public List<Entry> getAllEntries(final Category aCategory) {
        List<Entry> entries = new ArrayList<>();
        Cursor aCursor =  mSQLiteDb.rawQuery(Entry.TABLE_NAME,null);
        if(aCursor != null && aCursor.moveToFirst()){
            do{
                Entry newEntry = new Entry();
                newEntry.setId(aCursor.getLong(aCursor.getColumnIndex(Entry.ENTRY_ID)));
                newEntry.setName(aCursor.getString(aCursor.getColumnIndex(Entry.NAME)));
                newEntry.setSum(aCursor.getLong(aCursor.getColumnIndex(Entry.SUM)));
                newEntry.setCategory(aCategory);
                newEntry.setTags(getAllTagsByEntryId(newEntry.getId()));

            }while(aCursor.moveToNext());
        }



        // Es fehlen die tags ... die will ich mir von der Category holen und prüfen welches Objekt ich mir wo hinhänge...

    }

    private List<Tag> getAllTagsByEntryId(final long aEntryId){
        /*
        1. Alle Tags mit der entryId holen
        2. vergleichen ob in Category?
        3. Tag von Category holen und in Liste packen
         */
    }



}
