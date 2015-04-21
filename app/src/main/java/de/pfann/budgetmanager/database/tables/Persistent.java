package de.pfann.budgetmanager.database.tables;

import android.content.ContentValues;

/**
 * Created by johannes on 21.04.15.
 */
public interface Persistent {

    void setDirty();
    long persistObject(final String aTableName,final ContentValues aContentValue);
    void updateObject(final long aId,final String aTableName,final ContentValues aContentValue);
    void deleteObject(final long aId,final String aTableName);

}
