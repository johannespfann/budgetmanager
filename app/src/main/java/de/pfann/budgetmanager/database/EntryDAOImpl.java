package de.pfann.budgetmanager.database;


import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;

public class EntryDAOImpl implements EntryDAO{

    private Dao<Entry, Long> mEntryDao;

    public EntryDAOImpl(final Context aContext){
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(aContext, DatabaseHelper.class);
        try {
            mEntryDao = databaseHelper.getEntryDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEntry(Entry aEntry, Category aCategory) throws SQLException {
        aEntry.setCategory(aCategory);
        mEntryDao.create(aEntry);
    }

    @Override
    public void deleteEntry(Entry aEntry) throws SQLException{
        mEntryDao.delete(aEntry);
    }

    @Override
    public void updateEntry(Entry aEntry) throws SQLException {
        mEntryDao.update(aEntry);
    }

    @Override
    public List<Entry> getEntries() throws SQLException {
        return mEntryDao.queryForAll();
    }

    @Override
    public List<Entry> getEntriesByCategory(Category aCategory) throws SQLException {
        return null;
    }
}
