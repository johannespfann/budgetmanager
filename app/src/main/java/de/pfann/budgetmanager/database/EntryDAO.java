package de.pfann.budgetmanager.database;


import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import de.pfann.budgetmanager.model.Entry;

public class EntryDAO {

    private Dao<Entry, Long> mEntryDao;

    public EntryDAO(final Context aContext){
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(aContext, DatabaseHelper.class);
        mEntryDao = databaseHelper.getEntryDao();
    }




}
