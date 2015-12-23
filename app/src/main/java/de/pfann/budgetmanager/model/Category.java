package de.pfann.budgetmanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;

@DatabaseTable(tableName = "Category")
public class Category implements Serializable{

    @DatabaseField(generatedId = true)
    private long mID;

    @DatabaseField(columnName = "name")
    private String mName;

    @ForeignCollectionField
    private List<Entry> mEntities;

    @ForeignCollectionField
    private List<Tag> mTags;

    public long getID() {
        return mID;
    }

    public void setID(long mID) {
        this.mID = mID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public List<Entry> getEntities() {
        return mEntities;
    }

    public void setEntities(List<Entry> mEntities) {
        this.mEntities = mEntities;
    }

    public List<Tag> getTags() {
        return mTags;
    }

    public void setTags(List<Tag> mTags) {
        this.mTags = mTags;
    }
}
