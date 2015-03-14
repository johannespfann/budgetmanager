package de.pfann.budgetmanager.model;

/**
 * Created by johannes on 14.03.15.
 */
public class Category {

    private Integer id = null;
    private String name;

    private boolean isSycronizedWithDB;

    public Category(final String aName){
        isSycronizedWithDB = false;
        name = aName;

    }

    public int getId() {
        if (id == null) {
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
}
