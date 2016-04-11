package de.pfann.budgetmanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

@DatabaseTable(tableName = "Entry")
public class Entry implements Serializable {

    public static final String ID_TABLE_NAME = "id";
    public static final String NAME_TABLE_NAME = "name";
    public static final String SUM_TABLE_NAME = "sum";
    public static final String MEMO_TABLE_NAME = "memo";
    public static final String DATE_TABLE_NAME = "current_date";
    public static final String CATEGORY_TABLE_NAME = "category_id";

    @DatabaseField(generatedId = true, columnName = ID_TABLE_NAME)
    private long mId;

    @DatabaseField(columnName = NAME_TABLE_NAME)
    private String mName;

    @DatabaseField(columnName = SUM_TABLE_NAME)
    private double mSum;

    @DatabaseField(columnName = MEMO_TABLE_NAME)
    private String mMemo;

    @DatabaseField(columnName = DATE_TABLE_NAME)
    private Date mCurrentDate;

    @DatabaseField(columnName = CATEGORY_TABLE_NAME, foreign = true)
    private Category mCategory;

    @ForeignCollectionField
    private Collection<Tag> mTags;

    public Entry(){
        // Default
    }

    public Entry(final String aName, final Date aCurrentDate,final Category aCategory){
        mName = aName;
        mCurrentDate = aCurrentDate;
        mCategory = aCategory;
        mTags = new LinkedList<>();
    }


    public Entry(final String aName, final Date aCurrentDate,final Category aCategory, final Collection<Tag> aTags){
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
        this.mName = aName;
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

    public Collection<Tag> getTags() {
        return mTags;
    }

    public void setTags(Collection<Tag> aTags) {
        mTags = aTags;
    }
}
