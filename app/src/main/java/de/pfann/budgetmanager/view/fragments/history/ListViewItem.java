package de.pfann.budgetmanager.view.fragments.history;


import de.pfann.budgetmanager.model.Entry;

public class ListViewItem {

    private long mDatabaseId;

    private int mListViewPosition;

    private String mName;

    private Entry mEntry;


    public ListViewItem(Entry aEntry){
        mName = aEntry.getName();
        mDatabaseId = aEntry.getId();
        mEntry = aEntry;
    }

    public String getName(){
        return mName;
    }

    public Entry getEntry(){
        return mEntry;
    }

    public long getDatabaseId(){
        return mDatabaseId;
    }

    public int getListViewPosition() {
        return mListViewPosition;
    }

    public void setListViewPosition(int aListViewPosition) {
        mListViewPosition = aListViewPosition;
    }
}
