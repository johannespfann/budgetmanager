package de.pfann.budgetmanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
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
    private String mCurrentDate;

    @DatabaseField(columnName = "category", foreign = true)
    private Category mCategory;

    @ForeignCollectionField
    private List<Tag> mTags;


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

    public String getCurrentDate() {
        return mCurrentDate;
    }

    public void setCurrentDate(String aCurrentDate) {
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
