package de.pfann.budgetmanager.model;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.database.DatabaseContext;
import de.pfann.budgetmanager.database.tables.CategoryTable;


public class Category implements CategoryTable{

    private long id;
    private String name;
    private List<Entry> entries;
    private List<Tag> tags;

    public Category(final DatabaseContext aDatabaseContext,final String aName,final List<Entry> aEntries,final List<Tag> aTags){
        name = aName;
        entries = aEntries;
        tags = aTags;
    }

    public Category(final DatabaseContext aDatabaseContext,final long aId,final String aName,final List<Entry> aEntries,final List<Tag> aTags){
        id = aId;
        name = aName;
        entries = aEntries;
        tags = aTags;
    }

    public long getId() {
        return id;
    }


    public  void setName(final String aName) {
        name = aName;
    }

    public void addEntry(final Entry aEntry){
        if(entries == null){
            entries = new ArrayList<>();
        }
        entries.add(aEntry);
    }

    public void addTag(final Tag aTag){
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
    }

    public List<Tag> getTags() {
        return tags;
    }

}
