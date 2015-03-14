package de.pfann.budgetmanager.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by johannes on 14.03.15.
 */
public class Entry {

    private Integer id = null;
    private String name;
    private double sum;
    private String memo;
    private Timestamp timestamp;

    private Category category;
    private Tag tag;

    private boolean isSycronizedWithDB;

    public Entry(final String aName,final double aSum,final String aMemo,final String aTimestamp, final Category aCategory, final Tag aTag) {
        isSycronizedWithDB = false;
        name = aName;
        sum = aSum;
        memo = aMemo;
        category = aCategory;
        if(aTag == null){
            tag = Tag.getDefaultTag();
        }
        else {
            tag = aTag;
        }
    }

    public int getId() {
        if(id == null) {
            // Missing a perfect exception
            return 0;
        }
        return id;
    }

    public void setName(final String aName) {
        this.name = name;
        isSycronizedWithDB = false;
    }

    public String getName() {
        return name;
    }

    public void setSum(final double aSum) {
        this.sum = sum;
        isSycronizedWithDB = false;
    }

    public double getSum() {
        return sum;
    }

    public void setMemo(final String aMemo) {
        this.memo = memo;
        isSycronizedWithDB = false;
    }

    public String getMemo() {
        return memo;
    }

    public void persist(){
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        timestamp = new java.sql.Timestamp(now.getTime());
    }


}
