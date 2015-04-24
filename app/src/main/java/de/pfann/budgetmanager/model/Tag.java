package de.pfann.budgetmanager.model;

import de.pfann.budgetmanager.database.DatabaseContext;
import de.pfann.budgetmanager.database.tables.TagTable;

/**
 * Created by johannes on 14.03.15.
 */
public class Tag implements TagTable{

    private long id;
    private Category category;
    private String name;

    public Tag(DatabaseContext aDatabaseContext,final String aName,final Category aCategory){
        id = 0;
        name = aName;
        category = aCategory;
    }

    public Tag(DatabaseContext aDatabaseContext,final long aId,final String aName,final Category aCategory){
        id = aId;
        name = aName;
        category = aCategory;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

}
