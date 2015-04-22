package de.pfann.budgetmanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 31.03.15.
 */
public class TagMapper extends AbstractDBAccessor {

    private DatabaseContext mDatabaseContext;

    public TagMapper(final DatabaseContext aDatabaseContext,final BudgetDBHelper aBudgetDBHelper) {
        super(aBudgetDBHelper);
        mDatabaseContext = aDatabaseContext;
    }

    public Tag persistTag(final String aName, final Category aCategory) {
        ContentValues values = new ContentValues();
        values.put(Tag.NAME, aName);
        values.put(Tag.CATEGORY_ID, aCategory.getId());
        long tagId = persistObject(Tag.TABLE_NAME,values);
        Tag newTag = new Tag(mDatabaseContext, tagId, aName, aCategory);
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
                , Tag.NAME, Tag.CATEGORY_ID};
        String[] selectionArgs = {String.valueOf(aCategory.getId())};
        Cursor aCursor = mSQLiteDb.query(Tag.TABLE_NAME, projection, Tag.CATEGORY_ID + " =?", selectionArgs, null, null, null);
        if(aCursor != null && aCursor.moveToFirst()) {
            do {
                tags.add(buildTag(aCategory, aCursor));
            } while (aCursor.moveToNext());
        }
        closeDB();
        return tags;
    }

    public void updateTag(final Tag aTag){
        ContentValues values = new ContentValues();
        values.put(Tag.NAME, aTag.getName());
        values.put(Tag.CATEGORY_ID, aTag.getCategory().getId());
        updateObject(Tag.TABLE_NAME,aTag.TAG_ID,aTag.getId(),values);
    }

    private Tag buildTag(Category aCategory, Cursor aCursor) {
        long id = aCursor.getLong(aCursor.getColumnIndex(Tag.TAG_ID));
        String name = aCursor.getString(aCursor.getColumnIndex(Tag.NAME));
        Tag newTag = new Tag(mDatabaseContext, id, name, aCategory);
        return newTag;
    }


}
