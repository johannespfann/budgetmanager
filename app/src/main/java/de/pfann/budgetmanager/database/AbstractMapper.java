package de.pfann.budgetmanager.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by johannes on 31.03.15.
 */
public class AbstractMapper {

    protected BudgetDBHelper mBudgetDbHelper;
    protected SQLiteDatabase mSQLiteDb;


    public AbstractMapper(BudgetDBHelper aBudgetDBHelper){
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
}
