package de.pfann.budgetmanager.model;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.database.DatabaseContext;
import de.pfann.budgetmanager.database.tables.CategoryTable;

/**
 * Created by johannes on 14.03.15.
 */
public class Category implements CategoryTable,Persistent{

    private long id;
    private String name;
    private List<Entry> entries;
    private List<Tag> tags;

    private boolean isDirty;

    public Category(final DatabaseContext aDatabaseContext,final String aName,final List<Entry> aEntries,final List<Tag> aTags){
        name = aName;
        entries = aEntries;
        tags = aTags;
        isDirty = true;
    }

    public Category(final DatabaseContext aDatabaseContext,final long aId,final String aName,final List<Entry> aEntries,final List<Tag> aTags){
        id = aId;
        name = aName;
        entries = aEntries;
        tags = aTags;
        isDirty = false;
    }


    public long getId() {
        return id;
    }


    public  void setName(final String aName) {
        name = aName;
        setDirty();
    }

    public void addEntry(final Entry aEntry){
        // Hier muss isDirty nicht gesetzt werden. Keine Änderung in der DB!
        if(entries == null){
            entries = new ArrayList<>();
        }
        entries.add(aEntry);
    }

    public void addTag(final Tag aTag){
        // Hier muss isDirty nicht gesetzt werden. Keine Änderung in der DB!
        if(tags == null){
            tags = new ArrayList<>();
        }
        tags.add(aTag);
    }

    public List<Entry> getAllEntries(){
        return entries;

    }

    public  String getName() {
        return name;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
        setDirty();
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public void setDirty() {
        isDirty = true;
    }

}
