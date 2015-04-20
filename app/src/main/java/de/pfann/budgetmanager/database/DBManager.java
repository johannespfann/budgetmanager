package de.pfann.budgetmanager.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Persistent;

/**
 * Created by johannes on 28.03.15.
 */
public class DBManager {

    private static DBManager instance;

    private static final  String LOG_TAG = "DBManger";

    private DatabaseAccessorFacade mDbFacade;
    private DatabaseContext mDatabaseContext;
    private BudgetDBHelper mBudgetDBHelper;


    private DBManager(final BudgetDBHelper aBudgetDBHelper){
        mDatabaseContext = new DatabaseContextImp();
        mBudgetDBHelper = aBudgetDBHelper;
        EntryMapper entryMapper = new EntryMapper(mDatabaseContext,mBudgetDBHelper);
        CategoryMapper categoryMapper = new CategoryMapper(mDatabaseContext,mBudgetDBHelper);
        TagMapper tagMapper = new TagMapper(mDatabaseContext,mBudgetDBHelper);
        mDbFacade = new DatabaseAccessorFacade(categoryMapper,entryMapper, tagMapper);
    }

    public static DBManager initialize(final BudgetDBHelper aBudgetDBHelper){
        if(instance == null){
            instance = new DBManager(aBudgetDBHelper);
            Log.i(LOG_TAG,"Created DBManager for the first time!");
            return instance;
        }
        Log.i(LOG_TAG,"Already created dbmanager!");
        return instance;
    }

    public static DBManager getInstance(){
        return instance;
    }

    public DatabaseAccessorFacade getDatabaseFacade(){
        Log.i(MainActivity.LOG_TAG,"geDatabaseFacade");
        return mDbFacade;
    }


    private class DatabaseContextImp implements DatabaseContext{



    }



}
