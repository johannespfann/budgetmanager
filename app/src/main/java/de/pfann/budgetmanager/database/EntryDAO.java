package de.pfann.budgetmanager.database;


import java.sql.SQLException;
import java.util.List;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;

public interface EntryDAO {

    void addEntry(final Entry aEntry) throws SQLException;
    void deleteEntry(final Entry aEntry) throws SQLException;
    void updateEntry(final Entry aEntry) throws SQLException;
    List<Entry> getEntries() throws SQLException;
    List<Entry> getEntriesByCategory(final Category aCategory) throws SQLException;

}
