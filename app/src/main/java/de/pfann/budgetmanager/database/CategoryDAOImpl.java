package de.pfann.budgetmanager.database;


import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Category;

public class CategoryDAOImpl implements CategoryDAO{

    private Dao<Category, Long> mCategoryDao;

    @Inject
    public CategoryDAOImpl(final Context aContext){
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(aContext,DatabaseHelper.class);
        try {
            mCategoryDao = databaseHelper.getCategoryDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persistCategory(Category aCategory) throws SQLException {
        Log.i(MainActivity.TAG,"Persist Category: " + aCategory.getName());
        mCategoryDao.create(aCategory);
    }

    @Override
    public void updateCategory(Category aCategory) throws SQLException {
        mCategoryDao.update(aCategory);
    }

    @Override
    public Category getCategoryByName(String aName) throws SQLException {
        QueryBuilder<Category, Long> queryBuilder = mCategoryDao.queryBuilder();
        Where where = queryBuilder.where();
        where.eq(Category.NAME_TABLE_NAME,aName);
        PreparedQuery<Category> preparedQuery = queryBuilder.prepare();
        List<Category> categories = mCategoryDao.query(preparedQuery);
        return categories.get(0);
    }

    public List<Category> getCategories() throws SQLException {
        return mCategoryDao.queryForAll();
    }

    @Override
    public void deleteCategory(Category aCategory) throws SQLException {
        mCategoryDao.delete(aCategory);
    }

    public void delete(final Category aCategory) throws SQLException {
        mCategoryDao.delete(aCategory);
    }


}
