package de.pfann.budgetmanager.activities.addentryactivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 24.04.15.
 * A TagViewWrapper includes a TagSpinner and TagListView
 */
public class TagViewWrapper implements CategorySpinnerObserver, View.OnClickListener {

    private LinearLayout mTagListViewLinearLayout;
    private List<TagListViewItem> mListViewItems;
    private Spinner mTagSpinner;
    private Category mCategory;

    public TagViewWrapper(final LinearLayout aTagListViewLinearLayout, final Spinner aTagSpinner) {
        mTagListViewLinearLayout = aTagListViewLinearLayout;
        mTagSpinner = aTagSpinner;
        mListViewItems = new ArrayList<>();
    }

    public void cleanTagListView() {
        for (TagListViewItem item : mListViewItems) {
            mTagListViewLinearLayout.removeView(item.getTagListViewItem());
        }
        mListViewItems.clear();
    }

    public void addNewTagListViewItem(final LinearLayout aLinearLayout) {
        if (mCategory == null) {
            Log.d(MainActivity.LOG_TAG, "Category was null! Cannot create new ListViewItem of Tag");
            return;
        }
        Log.i(MainActivity.LOG_TAG, "Selected Tag: " + mTagSpinner.getSelectedItem().toString() + " found!");
        Tag tag = getSelectedTag();
        TagListViewItem listViewItem = new TagListViewItem(aLinearLayout, tag);
        listViewItem.setOnClickListener(this);
        mTagListViewLinearLayout.addView(aLinearLayout);
        mListViewItems.add(listViewItem);
        updateTagSpinner();
    }

    public Tag getSelectedTag() {
        for (Tag tag : mCategory.getTags()) {
            if (tag.getName().contains(mTagSpinner.getSelectedItem().toString())){
                return tag;
            }
        }
        throw new RuntimeException();
    }

    public List<Tag> getAllSelectedTags() {
        List<Tag> allTags = new ArrayList<>();
        if (mCategory == null) {
            return allTags;
        }

        for (TagListViewItem item : mListViewItems) {
            allTags.add(item.getTag());
        }
        for (Tag tag : mCategory.getTags()) {
            if (tag.getName().contains(mTagSpinner.getSelectedItem().toString())) {
                if (!allTags.contains(tag)) {
                    allTags.add(tag);
                }
            }
        }
        return allTags;
    }



    public void updateTagSpinner() {
        Log.i(MainActivity.LOG_TAG, "UpdateTagSpinner");
        List<String> values = new ArrayList<>();
        values.add("");
        if (mCategory == null) {
            return;
        }
        for (Tag tag : mCategory.getTags()) {
            if (!alreadyInListViewItem(tag, mListViewItems)) {
                values.add(tag.getName());
            }
        }
        ArrayAdapter adapter = (ArrayAdapter) mTagSpinner.getAdapter();
        adapter.clear();
        adapter.addAll(values);
    }

    private boolean alreadyInListViewItem(final Tag aTag, List<TagListViewItem> aListViewItems) {
        boolean alreadyExists = false;
        for (TagListViewItem item : mListViewItems) {
            if (item.getTag().equals(aTag)) {
                alreadyExists = true;
            }
        }
        return alreadyExists;
    }

    @Override
    public void update(Category aCategory) {
        mCategory = aCategory;
        updateTagSpinner();
        cleanTagListView();
    }

    /**
     * Is called when a ListItem have been clicked
     */
    @Override
    public void onClick(final View aView) {
        LinearLayout itemLinearLayout = (LinearLayout) aView.getParent();
        mTagListViewLinearLayout.removeView(itemLinearLayout);
        removeTagListViewItem(itemLinearLayout.getId());
        updateTagSpinner();
    }

    public void removeTagListViewItem(final int aLayoutId) {
        try {
            mListViewItems.remove(findListItemById(aLayoutId));
        } catch (ClassNotFoundException e) {
            Log.i(MainActivity.LOG_TAG,e.toString());
        }
    }

    public TagListViewItem findListItemById(final int aLayoutId) throws ClassNotFoundException{
        for(TagListViewItem item : mListViewItems){
            if(item.getLayoutId() == aLayoutId){
                return item;
            }
        }
        throw new ClassNotFoundException();
    }


    private class TagListViewItem {
        private LinearLayout mTagListViewItem;
        private Tag mTag;

        private int mLayoutId;
        private TextView mTagTextView;
        private ImageView mDeleteImageView;

        private TagListViewItem(final LinearLayout aTagListViewItem, final Tag aTag) {
            mTagListViewItem = aTagListViewItem;
            mTag = aTag;
            mLayoutId = new Random().nextInt();
            mTagListViewItem.setId(mLayoutId);
            mTagTextView = (TextView) mTagListViewItem.getChildAt(1);
            mDeleteImageView = (ImageView) mTagListViewItem.getChildAt(2);
            setText(aTag.getName());
        }

        public void setOnClickListener(View.OnClickListener aOnClickListener) {
            mDeleteImageView.setOnClickListener(aOnClickListener);
        }

        public void setText(final String aString) {
            mTagTextView.setText(aString);
        }

        public int getLayoutId() {
            return mLayoutId;
        }

        public Tag getTag() {
            return mTag;
        }

        public LinearLayout getTagListViewItem() {
            return mTagListViewItem;
        }
    }
}
