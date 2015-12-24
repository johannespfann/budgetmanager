package de.pfann.budgetmanager.database;


import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;

import de.pfann.budgetmanager.model.Tag;

public class TagDAOImpl implements TagDAO{

    private Dao<Tag, Long> mTagDao;

    public TagDAOImpl(final Context aContext){
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(aContext, DatabaseHelper.class);
        try {
            mTagDao = databaseHelper.getTagDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTag(Tag aTag) throws SQLException {
        mTagDao.create(aTag);
    }

    @Override
    public void deleteTAg(Tag aTag) throws SQLException {
        mTagDao.delete(aTag);
    }

    @Override
    public void updateTag(Tag aTag) throws SQLException {
        mTagDao.update(aTag);
    }
}
