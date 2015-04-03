package de.pfann.budgetmanager.model;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.pfann.budgetmanager.database.DatabaseContext;
import de.pfann.budgetmanager.database.tables.EntryTable;

/**
 * Created by johannes on 14.03.15.
 */
public class Entry implements EntryTable, Persistent{

    private long id;
    private String name;
    private double sum;
    private String memo;
    private String currentDate;
    private Category category;
    private List<Tag> tags;

    private boolean isDirty;
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
        isDirty = true;
    }

    public Entry(DatabaseContext aDatabaseContext,final String aName,final double aSum, final String aMemo,final Category aCategory,final List<Tag> aTags){
        id = 0;
        name = aName;
        sum = aSum;
        memo = aMemo;
        currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        category = aCategory;
        tags = aTags;
        isDirty = true;
    }

    public Entry(DatabaseContext aDatabaseContext,final long aId,final String aName,final double aSum, final String aMemo, String aCurrentDate, final Category aCategory,final List<Tag> aTags){
        id = aId;
        name = aName;
        sum = aSum;
        memo = aMemo;
        currentDate = aCurrentDate;
        category = aCategory;
        tags = aTags;
        isDirty = false;
    }

    public long getId() {
        return id;
    }

    public void setName(String aName) {
        name = aName;
        isDirty();
    }

    public String getName() {
        return name;
    }

    public void setSum(double aSum) {
        this.sum = aSum;
        isDirty();
    }

    public double getSum(){
        return sum;
    }

    public void setCategory(Category aCategory) {
        category = aCategory;
        isDirty();
    }

    public Category getCategory(){
       return category;
    }

    public long getCategoryId(){
        return category.getId();
    }

    public void setMemo(String memo) {
        this.memo = memo;
        isDirty();
    }

    public String getMemo() {
        return memo;
    }

    public List<Tag> getTags(){
        return tags;
    }

    public boolean isDirty(){
        return isDirty;
    }

    public void addTag(final Tag aTag){
        tags.add(aTag);
        isDirty();
    }

    public void deleteTag(final Tag aTag){
        if(tags.contains(aTag)){
            tags.remove(aTag);
            isDirty();
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

    @Override
    public void setDirty() {
        isDirty = true;
    }

}
