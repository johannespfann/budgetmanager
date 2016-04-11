package de.pfann.budgetmanager.view.fragments.history;


import de.pfann.budgetmanager.model.Entry;

public class ListViewItem {


    private String mName;

    private Entry mEntry;


    public ListViewItem(Entry aEntry){
        mName = aEntry.getName() + aEntry.getId();
        mEntry = aEntry;
    }

    public String getName(){
        return mName;
    }

    public Entry getEntry(){
        return mEntry;
    }



}
