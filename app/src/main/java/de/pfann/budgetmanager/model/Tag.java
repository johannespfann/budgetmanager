package de.pfann.budgetmanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "Tag")
public class Tag implements Serializable {

    public static final String ID_TABLE_NAME = "id";
    public static final String NAME_TABLE_NAME = "name";
    public static final String CATEGORY_TABLE_NAME = "category_id";
    public static final String ENTRY_TABLE_NAME = "entry_id";


    @DatabaseField(generatedId = true, columnName = ID_TABLE_NAME)
    private long mId;

    @DatabaseField(columnName = NAME_TABLE_NAME)
    private String mName;

    @DatabaseField(columnName = CATEGORY_TABLE_NAME, foreignAutoRefresh = true,foreign = true)
    private Category mCategory;

    @DatabaseField(columnName = ENTRY_TABLE_NAME, foreignAutoRefresh = true,foreign = true)
    private Entry mEntry;


    public Tag(){
        // Default
    }

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

    public Entry getEntry() {
        return mEntry;
    }

    public void setEntry(Entry aEntry) {
        mEntry = aEntry;
    }
}
