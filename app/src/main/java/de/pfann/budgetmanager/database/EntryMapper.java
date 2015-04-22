package de.pfann.budgetmanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 28.03.15.
 */
public class EntryMapper extends AbstractDBAccessor{

    private static final String LOG_TAG = "EntityMapper";

    private DatabaseContext mDatabaseContext;

    public EntryMapper(final DatabaseContext aDatabaseContext,BudgetDBHelper aBudgetDBHelper){
        super(aBudgetDBHelper);
        mDatabaseContext = aDatabaseContext;
    }

    public Entry persistEntry(final String aName, final double aSum,final String aMemo,final  List<Tag> tags, final Category aCategory) {
        ContentValues values = new ContentValues();
        values.put(Entry.NAME,aName);
        values.put(Entry.SUM,aSum);
        values.put(Entry.CATEGORY_ID,aCategory.getId());
        values.put(Entry.MEMO,aMemo);
        values.put(Entry.TAGS,Entry.convertTagsToString(tags));
        long entryId = persistObject(Entry.TABLE_NAME,values);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Entry newEntry = new Entry(mDatabaseContext,entryId,aName,aSum,aMemo,currentDate,aCategory,tags);
        return newEntry;
    }

    public void deleteEntry(final Entry aEntry) {
        openWriteAbleDB();
        mSQLiteDb.delete(Entry.TABLE_NAME,Entry.ENTRY_ID + " = " + aEntry.getId(), null);
        closeDB();
    }

    public void updateEntry(final Entry aEntry) {
        ContentValues values = new ContentValues();
        values.put(Entry.NAME,aEntry.getName());
        values.put(Entry.SUM,aEntry.getSum());
        values.put(Entry.CATEGORY_ID,aEntry.getCategoryId());
        values.put(Entry.MEMO,aEntry.getMemo());
        values.put(Entry.TAGS,aEntry.getTagsAsString());
        updateObject(Entry.TABLE_NAME,Entry.ENTRY_ID,aEntry.getId(),values);
    }


    public List<Entry> getAllEntries(final Category aCategory) {
        openReadAbleDB();
        List<Entry> entries = new ArrayList<>();
        Cursor aCursor =  mSQLiteDb.rawQuery(Entry.TABLE_NAME,null);
        if(aCursor != null && aCursor.moveToFirst()){
            do{
                long id = aCursor.getLong(aCursor.getColumnIndex(Entry.ENTRY_ID));
                String name = aCursor.getString(aCursor.getColumnIndex(Entry.NAME));
                double sum = aCursor.getLong(aCursor.getColumnIndex(Entry.SUM));
                String memo = aCursor.getString(aCursor.getColumnIndex(Entry.MEMO));
                String tagsAsString = aCursor.getString(aCursor.getColumnIndex(Entry.TAGS));
                List<Tag> tags = Entry.convertStringToTagObject(aCategory,tagsAsString);
                String currentDate = aCursor.getString(aCursor.getColumnIndex(Entry.TIMESTAMP));
                Entry newEntry = new Entry(mDatabaseContext,id,name,sum,memo,currentDate,aCategory,tags);
            }while(aCursor.moveToNext());
        }
        closeDB();
        return entries;
    }




}
