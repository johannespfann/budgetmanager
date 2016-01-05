package de.pfann.budgetmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.Tag;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "budget.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Tag, Long> mTagDao;
    private Dao<Category, Long> mCategoryDao;
    private Dao<Entry, Long> mEntryDao;

    public DatabaseHelper(final Context aContext){
        super(aContext, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    public Dao<Tag, Long> getTagDao() throws SQLException{
        if(mTagDao == null){
            mTagDao = getDao(Tag.class);
        }
        return mTagDao;
    }

    public Dao<Category, Long> getCategoryDao() throws SQLException{
        if(mCategoryDao == null){
            mCategoryDao = getDao(Category.class);
        }
        return mCategoryDao;
    }

    public Dao<Entry, Long> getEntryDao() throws SQLException{
        if(mEntryDao == null){
            mEntryDao = getDao(Entry.class);
        }
        return mEntryDao;
    }

    @Override
    public void onCreate(final SQLiteDatabase aDatabase, final ConnectionSource aConnectionSource) {
        try {
            TableUtils.createTable(aConnectionSource, Entry.class);
            TableUtils.createTable(aConnectionSource, Category.class);
            TableUtils.createTable(aConnectionSource, Tag.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(final SQLiteDatabase aDatabase, final ConnectionSource aConnectionSource, final int aOldVersion,final int aNewVersion) {
        try {
            TableUtils.dropTable(aConnectionSource, Tag.class, false);
            onCreate(aDatabase, aConnectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
