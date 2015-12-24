package de.pfann.budgetmanager.database;


import java.sql.SQLException;

import de.pfann.budgetmanager.model.Tag;

public interface TagDAO {

    void addTag(final Tag aTag) throws SQLException;
    void deleteTAg(final Tag aTag) throws SQLException;
    void updateTag(final Tag aTag) throws SQLException;



}
