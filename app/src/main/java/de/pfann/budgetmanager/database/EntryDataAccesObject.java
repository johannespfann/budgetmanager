package de.pfann.budgetmanager.database;

import java.util.List;

import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 28.03.15.
 */
public interface EntryDataAccesObject {

    void createEntry(final String aName,final double aSum,final long aCategoryId);
    void createEntry(final String aName,final double aSum,final long aCategoryId,final long[] aTagIds);

    Entry getEntryById(final long aEntryId);
    Entry getEntryByName(final String aEntryName);

    void updateEntry(final Entry aEntry);

    void deleteEntry(final Entry aEntry);

}
