package de.pfann.budgetmanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "Tag")
public class Tag implements Serializable {

    @DatabaseField(generatedId = true)
    private long mId;

    @DatabaseField(columnName = "category", foreign = true)
    private Category mCategory;

    @DatabaseField(columnName = "name")
    private String mName;

    public Tag(final String aName, final Category aCategory){
        mName = aName;
        mCategory = aCategory;
    }

    public long getId() {
        return mId;
    }

    public void setId(long aId) {
        mId = aId;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category aCategory) {
        mCategory = aCategory;
    }

    public String getName() {
        return mName;
    }

    public void setName(String aName) {
        mName = aName;
    }
}
