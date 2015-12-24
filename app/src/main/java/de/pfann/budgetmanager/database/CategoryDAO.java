package de.pfann.budgetmanager.database;


import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import de.pfann.budgetmanager.model.Category;

public class CategoryDAO {

    private Dao<Category, Long> mCategoryDao;

    public CategoryDAO(final Context aContext){
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(aContext,DatabaseHelper.class);
        mCategoryDao = databaseHelper.getCategoryDao();
    }

    public void addCategory(final Category aCategory) throws SQLException {
        mCategoryDao.create(aCategory);
    }

    public List<Category> getCategories() throws SQLException {
        return mCategoryDao.queryForAll();
    }

    public void delete(final Category aCategory) throws SQLException {
        mCategoryDao.delete(aCategory);
    }


}
