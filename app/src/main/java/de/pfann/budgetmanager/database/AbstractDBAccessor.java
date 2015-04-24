package de.pfann.budgetmanager.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by johannes on 22.04.15.
 */
public class AbstractDBAccessor {

    protected BudgetDBHelper mBudgetDbHelper;
    protected SQLiteDatabase mSQLiteDb;

    public AbstractDBAccessor(final BudgetDBHelper aBudgetDBHelper){
        mBudgetDbHelper = aBudgetDBHelper;
    }

    protected void openReadAbleDB(){
        mSQLiteDb = mBudgetDbHelper.getReadableDatabase();
    }

    protected void openWriteAbleDB(){
        mSQLiteDb = mBudgetDbHelper.getWritableDatabase();
    }

    protected void closeDB(){
        mSQLiteDb.close();
    }

    public long persistObject(final String aTableName,final ContentValues aContentValue){
        openWriteAbleDB();
        long entryId = mSQLiteDb.insert(aTableName,null,aContentValue);
        closeDB();
        return entryId;

    }

    public void updateObject(final String aTableName, final String aIdColumName,final long id,final ContentValues aContentValues){
        openWriteAbleDB();
        mSQLiteDb.update(aTableName,aContentValues,aIdColumName + " = " + id,null);
        closeDB();
    }


}
