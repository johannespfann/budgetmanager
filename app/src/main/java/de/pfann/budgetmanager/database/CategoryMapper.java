package de.pfann.budgetmanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 29.03.15.
 */
public class CategoryMapper {

    private static final String LOG_TAG = "CategoryMapper";

    private BudgetDBHelper mBudgetDbHelper;
    private SQLiteDatabase mSQLiteDb;

    public CategoryMapper(final BudgetDBHelper aBudgetHelper) {
        mBudgetDbHelper = aBudgetHelper;
    }

    private void openReadAbleDB()
    {
        mSQLiteDb = mBudgetDbHelper.getReadableDatabase();
    }
    private void openWriteAbleDB()
    {
        mSQLiteDb = mBudgetDbHelper.getWritableDatabase();
    }

    private void closeDB(){
        mSQLiteDb.close();
    }

    public Category persistCategory(final String aName) {
        Category newCategory = new Category();
        ContentValues values = new ContentValues();
        values.put(Category.NAME, aName);
        long categoryId = mSQLiteDb.insert(Category.TABLE_NAME, null, values);
        newCategory.setId(categoryId);
        newCategory.setName(aName);
        newCategory.setTags(new ArrayList<Tag>());
        return newCategory;
    }

    public Tag persistTag(final String aName,final Category aCategory){
        Tag newTag = new Tag();
        ContentValues values = new ContentValues();
        values.put(Tag.NAME,aName);
        values.put(Tag.CATEGORY_ID,aCategory.getId());
        long tagId = mSQLiteDb.insert(Tag.TABLE_NAME,null,values);
        newTag.setId(tagId);
        newTag.setName(aName);
        newTag.setCategory_id(aCategory.getId());
        return newTag;
    }

    public List<Category> getAllCategories() {
        openReadAbleDB();
        List<Category> categories = new ArrayList<>();
        Cursor aCursor = mSQLiteDb.rawQuery(Category.TABLE_NAME, null);
        if (aCursor != null && aCursor.moveToFirst()) {
            do {
                categories.add(buildCategory(aCursor));
            } while (aCursor.moveToNext());
        }
        closeDB();
        return categories;
    }

    private Category buildCategory(Cursor aCursor) {
        Category category = new Category();
        category.setId(aCursor.getLong(aCursor.getColumnIndex(Category.CATEGORY_ID)));
        category.setName(aCursor.getString(aCursor.getColumnIndex(Category.NAME)));
        category.setTags(getAllTagsByCategory(category));
        return category;
    }

    private List<Tag> getAllTagsByCategory(final Category aCategory) {
        List<Tag> tags = new ArrayList<>();
        String[] projection = {Tag.TAG_ID
                , Tag.CATEGORY_ID, Tag.NAME};
        String[] selectionArgs = {Category.CATEGORY_ID};
        Cursor aCursor = mSQLiteDb.query(Tag.TABLE_NAME, projection, Tag.CATEGORY_ID, selectionArgs, null, null, null);
        aCursor.moveToFirst();
        do {
            tags.add(buildTag(aCategory, aCursor));
        } while (aCursor.moveToNext());
        return tags;
    }

    private Tag buildTag(Category aCategory, Cursor aCursor) {
        Tag newTag = new Tag();
        newTag.setId(aCursor.getLong(aCursor.getColumnIndex(Tag.TAG_ID)));
        newTag.setName(aCursor.getString(aCursor.getColumnIndex(Tag.NAME)));
        newTag.setCategory_id(aCategory.getId());
        return newTag;
    }
}
