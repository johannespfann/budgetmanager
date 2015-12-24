package de.pfann.budgetmanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@DatabaseTable(tableName = "Entry")
public class Entry implements Serializable {

    @DatabaseField(generatedId = true)
    private long mId;

    @DatabaseField(columnName = "name")
    private String mName;

    @DatabaseField(columnName = "sum")
    private double mSum;

    @DatabaseField(columnName = "memo")
    private String mMemo;

    @DatabaseField(columnName = "current_date")
    private Date mCurrentDate;

    @DatabaseField(columnName = "category", foreign = true)
    private Category mCategory;

    @ForeignCollectionField
    private List<Tag> mTags;

    public Entry(final String aName, final Date aCurrentDate,final Category aCategory){
        mName = aName;
        mCurrentDate = aCurrentDate;
        mCategory = aCategory;
        mTags = new LinkedList<>();
    }


    public Entry(final String aName, final Date aCurrentDate,final Category aCategory, final List<Tag> aTags){
        mName = aName;
        mCurrentDate = aCurrentDate;
        mCategory = aCategory;
        mTags = aTags;
    }


    public long getId() {
        return mId;
    }

    public void setId(long aId) {
        mId = aId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String aName) {
        this.mName = mName;
    }

    public double getSum() {
        return mSum;
    }

    public void setSum(double aSum) {
        mSum = aSum;
    }

    public String getMemo() {
        return mMemo;
    }

    public void setMemo(String aMemo) {
        mMemo = aMemo;
    }

    public Date getCurrentDate() {
        return mCurrentDate;
    }

    public void setCurrentDate(Date aCurrentDate) {
        mCurrentDate = aCurrentDate;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category aCategory) {
        mCategory = aCategory;
    }

    public List<Tag> getTags() {
        return mTags;
    }

    public void setTags(List<Tag> aTags) {
        mTags = aTags;
    }
}
