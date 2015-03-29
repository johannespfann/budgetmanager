package de.pfann.budgetmanager.model;

import java.util.List;

/**
 * Created by johannes on 14.03.15.
 */
public class Category {

    public static final String TABLE_NAME = "Category";
    public static final String CATEGORY_ID = "category_id";
    public static final String NAME = "category_name";

    private long id;
    private String name;
    private List<Entry> entries;
    private List<Tag> tags;


    public void setId(final long aId){
        id = aId;
    }

    public long getId() {
        return id;
    }


    public  void setName(final String aName) {
        name = aName;
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
