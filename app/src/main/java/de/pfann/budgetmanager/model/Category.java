package de.pfann.budgetmanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;


@DatabaseTable(tableName = "Category")
public class Category implements Serializable{

    public static final String ID_TABLE_NAME = "id";
    public static final String NAME_TABLE_NAME = "name";


    @DatabaseField(generatedId = true, columnName = ID_TABLE_NAME)
    private long mID;

    @DatabaseField(columnName = NAME_TABLE_NAME)
    private String mName;

    @ForeignCollectionField
    private Collection<Entry> mEntities;

    @ForeignCollectionField
    private Collection<Tag> mTags;


    public Category(){
        // Default
    }

    public Category(final String aName){
        mName = aName;
        mEntities = new LinkedList<>();
        mTags = new LinkedList<>();
    }

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

    public Collection<Entry> getEntities() {
        return mEntities;
    }

    public void setEntities(Collection<Entry> mEntities) {
        this.mEntities = mEntities;
    }

    public Collection<Tag> getTags() {
        return mTags;
    }

    public void setTags(Collection<Tag> mTags) {
        this.mTags = mTags;
    }
}
