package de.pfann.budgetmanager.model;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.pfann.budgetmanager.database.DatabaseContext;
import de.pfann.budgetmanager.database.tables.EntryTable;

public class Entry implements EntryTable{

    private long id;
    private String name;
    private double sum;
    private String memo;
    private String currentDate;
    private Category category;
    private List<Tag> tags;

    private DatabaseContext mDatabaseContext;

    public Entry(DatabaseContext aDatabaseContext,final String aName,final double aSum, final String aMemo,final Category aCategory){
        id = 0;
        name = aName;
        sum = aSum;
        memo = aMemo;
        currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        category = aCategory;
        tags = new ArrayList<>();
        mDatabaseContext = aDatabaseContext;
    }

    public Entry(DatabaseContext aDatabaseContext,final String aName,final double aSum, final String aMemo,final Category aCategory,final List<Tag> aTags){
        id = 0;
        name = aName;
        sum = aSum;
        memo = aMemo;
        currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        category = aCategory;
        tags = aTags;
    }

    public Entry(DatabaseContext aDatabaseContext,final long aId,final String aName,final double aSum, final String aMemo, String aCurrentDate, final Category aCategory,final List<Tag> aTags){
        id = aId;
        name = aName;
        sum = aSum;
        memo = aMemo;
        currentDate = aCurrentDate;
        category = aCategory;
        tags = aTags;
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
    }

    public void deleteTag(final Tag aTag){
        if(tags.contains(aTag)){
            tags.remove(aTag);
        }
    }


    public String getTagsAsString() {
        return convertTagsToString(tags);
    }


    public static String convertTagsToString(List<Tag> aTags){
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0;index < aTags.size();index ++){
            stringBuilder.append(aTags.get(index).getName());
            if(index != aTags.size()-1){
                stringBuilder.append(elementSeperator);
            }
        }
        return stringBuilder.toString();
    }

    public static List<Tag> convertStringToTagObject(final Category aCategory,final String aString){
        List<Tag> tags = new ArrayList<>();
        List<Tag> tempTags = aCategory.getTags();
        String[] strings = aString.split(elementSeperator);
        for(String element : strings){
            for(Tag tag : tempTags){
                if(tag.getName().equals(element)){
                    tags.add(tag);
                }
                else{
                    Log.i("","Tag was not known by Category: " + element + " in: " + aCategory.getName());
                }
            }
        }
        return tags;
    }
}
