package de.pfann.budgetmanager.view.fragments.history;


import de.pfann.budgetmanager.model.Entry;

public class ListViewItem {


    private String mName;


    public ListViewItem(){
        // Default
    }

    public ListViewItem(Entry aEntry){
        mName = aEntry.getName() + aEntry.getId();
    }

    public String getName(){
        return mName;
    }



}
