package de.pfann.budgetmanager.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by johannes on 14.03.15.
 */
public class Entry {

    public static final String TABLE_NAME = "Entry";
    public static final String ENTRY_ID = "entry_id";
    public static final String NAME = "entry_name";
    public static final String SUM = "entry_sum";
    public static final String TIMESTAMP = "entry_timestamp";
    public static final String CATEGORY_ID = "entry_category_id";

    private long id;
    private String name;
    private double sum;
    private String memo; // notiz ;-)
    private Timestamp timestamp;

    private Category category;
    private List<Tag> tags;


    public void setId(long aId) {
       id = aId;
    }

    public long getId() {
        return id;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getName() {
        return name;
    }

    public void setSum(double aSum) {
        this.sum = aSum;
    }

    public double getSum(){
        return sum;
    }

    public void setCategory(Category aCategory) {
        category = aCategory;
    }

    public Category getCategory(){
       return category;
    }

    public long getCategoryId(){
        return category.getId();
    }

    public List<Tag> getTags(){
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


}
