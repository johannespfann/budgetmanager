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
    private Dao<Entry, Long> mEntry;


    DatabaseHelper(final Context aContext){
        super(aContext, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    public Dao<Tag, Long> getTagDao() {
        return mTagDao;
    }

    public Dao<Category, Long> getCategoryDao() {
        return mCategoryDao;
    }

    public Dao<Entry, Long> getEntry() {
        return mEntry;
    }

    @Override
    public void onCreate(SQLiteDatabase aDatabase, ConnectionSource aConnectionSource) {
        try {
            TableUtils.createTable(aConnectionSource, Tag.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase aDatabase, ConnectionSource aConnectionSource, int aOldVersion, int aNewVersion) {
        try {
            TableUtils.dropTable(aConnectionSource, Tag.class, false);
            onCreate(aDatabase, aConnectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
