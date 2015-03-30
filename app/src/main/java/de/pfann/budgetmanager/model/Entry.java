package de.pfann.budgetmanager.model;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    public static final String TAGS = "entry_tags";
    public static final String MEMO = "entry_memo";
    public static final String elementSeperator = "__";

    private long id;
    private String name;
    private double sum;
    private String memo; // notiz ;-)
    private Timestamp timestamp;

    private Category category;
    private List<Tag> tags;

    private String tagsAsString;




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

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public List<Tag> getTags(){
        return tags;
    }

    public void addTag(final Tag aTag){
        tags.add(aTag);
        tagsAsString = convertTagsToString(tags);
    }

    public void deleteTag(final Tag aTag){
        if(tags.contains(aTag)){
            tags.remove(aTag);
            tagsAsString = convertTagsToString(tags);
        }
    }

    public void setTags(List<Tag> aTags) {
        if(aTags.size() > 0){
            tagsAsString = convertTagsToString(aTags);
        }
        tags = aTags;
    }

    public String getTagsAsString() {
        return tagsAsString;
    }


    public String convertTagsToString(List<Tag> aTags){
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0;index < aTags.size();index ++){
            stringBuilder.append(aTags.get(index).getName());
            if(index != aTags.size()-1){
                stringBuilder.append(elementSeperator);
            }
        }
        return stringBuilder.toString();
    }

    public static List<Tag> convertTagsToObjects(final Category aCategory,final String aString){
        List<Tag> tags = new ArrayList<>();
        String[] strings = aString.split(elementSeperator);
        for(String element : strings){
            Tag newTag = new Tag();
            newTag.setCategory_id(aCategory.getId());
            tags.add(newTag);
        }
        return tags;
    }

}
