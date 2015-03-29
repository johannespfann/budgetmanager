package de.pfann.budgetmanager.model;

/**
 * Created by johannes on 14.03.15.
 */
public class Tag {

    public static final String TABLE_NAME = "Tag";
    public static final String TAG_ID = "tag_id";
    public static final String NAME = "tag_name";
    public static final String CATEGORY_ID = "tag_category_id";


    private long id;
    private long category_id;
    private String name;


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

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public long getCategory_id() {
        return category_id;
    }
}
