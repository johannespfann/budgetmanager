package de.pfann.budgetmanager.database;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 29.03.15.
 */
public class CategoryMapper extends AbstractMapper {

    private static final String LOG_TAG = "CategoryMapper";
    private DatabaseContext mDatabaseContext;

    public CategoryMapper(final DatabaseContext aDatabaseContext, final BudgetDBHelper aBudgetHelper) {
        super(aBudgetHelper);
        mDatabaseContext = aDatabaseContext;

    }

    public Category persistCategory(final String aName) {
        openWriteAbleDB();
        ContentValues values = new ContentValues();
        values.put(Category.NAME, aName);
        long categoryId = mSQLiteDb.insert(Category.TABLE_NAME, null, values);
        Category newCategory = new Category(mDatabaseContext, categoryId, aName, new ArrayList<Entry>(), new ArrayList<Tag>());
        closeDB();
        return newCategory;
    }

    public boolean categoryAlreadyExits(final String aName) {
        openReadAbleDB();
        String[] projection = {Category.CATEGORY_ID, Category.NAME};
        String sortOrder = Category.NAME + " DESC";
        Cursor cursor = mSQLiteDb.query(Category.TABLE_NAME, projection, Category.NAME + " =?", new String[]{aName}, null, null, sortOrder);
        if (cursor != null && cursor.moveToFirst()) {
            closeDB();
            return true;
        }
        closeDB();
        return false;
    }

    public List<Category> getAllCategories() {
        openReadAbleDB();
        List<Category> categories = new ArrayList<>();
        String statement = "SELECT * FROM " +Category.TABLE_NAME;
        Cursor aCursor = mSQLiteDb.rawQuery(statement, null);
        if (aCursor != null && aCursor.moveToFirst()) {
            do {
                categories.add(buildCategory(aCursor));
            } while (aCursor.moveToNext());
        }
        closeDB();
        return categories;
    }

    private Category buildCategory(Cursor aCursor) {
        long id = aCursor.getLong(aCursor.getColumnIndex(Category.CATEGORY_ID));
        String name = aCursor.getString(aCursor.getColumnIndex(Category.NAME));
        Category category = new Category(mDatabaseContext, id, name, new ArrayList<Entry>(), new ArrayList<Tag>());
        return category;
    }


}
