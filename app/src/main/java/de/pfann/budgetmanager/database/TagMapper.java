package de.pfann.budgetmanager.database;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 31.03.15.
 */
public class TagMapper extends AbstractMapper {

    private DatabaseContext mDatabaseContext;

    public TagMapper(final DatabaseContext aDatabaseContext,final BudgetDBHelper aBudgetDBHelper) {
        super(aBudgetDBHelper);
        mDatabaseContext = aDatabaseContext;
    }

    public Tag persistTag(final String aName, final Category aCategory) {
        openWriteAbleDB();
        ContentValues values = new ContentValues();
        values.put(Tag.NAME, aName);
        values.put(Tag.CATEGORY_ID, aCategory.getId());
        long tagId = mSQLiteDb.insert(Tag.TABLE_NAME, null, values);
        Tag newTag = new Tag(mDatabaseContext, tagId, aName, aCategory);
        closeDB();
        return newTag;
    }

    public boolean tagAlreadyExists(final String aName, final Category aCategory){
        openReadAbleDB();
        String selectQuery = "SELECT  * FROM " + Tag.TABLE_NAME
                + " WHERE " + Tag.NAME + " = ? AND " + Tag.CATEGORY_ID + " = ?";
        Cursor cursor = mSQLiteDb.rawQuery(selectQuery, new String[]{String.valueOf(aName), String.valueOf(aCategory.getId())});

        if(cursor != null && cursor.moveToFirst()){
            closeDB();
            return true;
        }
        closeDB();
        return false;
    }

    public List<Tag> getAllTagsByCategory(final Category aCategory) {
        openReadAbleDB();
        List<Tag> tags = new ArrayList<>();
        String[] projection = {Tag.TAG_ID
                , Tag.CATEGORY_ID, Tag.NAME};
        String[] selectionArgs = {String.valueOf(aCategory.getId())};
        Cursor aCursor = mSQLiteDb.query(Tag.TABLE_NAME, projection, Tag.CATEGORY_ID, selectionArgs, null, null, null);
        aCursor.moveToFirst();
        do {
            tags.add(buildTag(aCategory, aCursor));
        } while (aCursor.moveToNext());
        closeDB();
        return tags;
    }

    private Tag buildTag(Category aCategory, Cursor aCursor) {
        long id = aCursor.getLong(aCursor.getColumnIndex(Tag.TAG_ID));
        String name = aCursor.getString(aCursor.getColumnIndex(Tag.NAME));
        Tag newTag = new Tag(mDatabaseContext, id, name, aCategory);
        return newTag;
    }


}
