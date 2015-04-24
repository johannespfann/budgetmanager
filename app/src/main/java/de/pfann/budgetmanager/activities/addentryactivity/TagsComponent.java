package de.pfann.budgetmanager.activities.addentryactivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 24.04.15.
 */
public class TagsComponent implements CategorySpinnerObserver{

    private LinearLayout mTagListViewLinearLayout;
    private List<TagListViewItem> mListViewItems;
    private Spinner mTagSpinner;
    private Category mCategory;
    private Context mContext;

    public TagsComponent(final Context aContext,final LinearLayout aTagListViewLinearLayout, final Spinner aTagSpinner){
        mTagListViewLinearLayout = aTagListViewLinearLayout;
        mTagSpinner = aTagSpinner;
        mListViewItems = new ArrayList<>();
        mContext = aContext;
    }

    public void cleanTagListView(){
        for(TagListViewItem item : mListViewItems){
            mTagListViewLinearLayout.removeView(item.getTagListViewItem());
        }
        mListViewItems.clear();

    }

    public void addNewTagListViewItem(LinearLayout aLinearLayout){
        if(mCategory == null){
            Log.i(MainActivity.LOG_TAG,"Category was null! Cannot create new ListViewItem of Tag");
            return;
        }
        
        for(Tag tag : mCategory.getTags()){
            if(tag.getName().contains(mTagSpinner.getSelectedItem().toString())){
                Log.i(MainActivity.LOG_TAG, "Selected Tag: " + mTagSpinner.getSelectedItem().toString() + " found!");
                mTagListViewLinearLayout.addView(createNewTagListViewItem(aLinearLayout,tag));
            }
        }
    }

    private LinearLayout createNewTagListViewItem(final LinearLayout aLinearLayout,final Tag aTag){
        aLinearLayout.setId(new Random().nextInt());
        TextView middleTextView = (TextView) aLinearLayout.getChildAt(1);
        middleTextView.setText(aTag.getName());
        ImageView rightImageView = (ImageView) aLinearLayout.getChildAt(2);
        rightImageView.setClickable(true);
        rightImageView.setOnClickListener(new TagItemListViewOnClickListener(aLinearLayout));
        return aLinearLayout;
    }

    public List<Tag> getAllSelectedTags(){
        List<Tag> allTags = new ArrayList<>();
        if(mCategory == null){
            return allTags;
        }

        for(TagListViewItem item : mListViewItems){
            allTags.add(item.getTag());
        }
        for(Tag tag : mCategory.getTags()){
            if(tag.getName().contains(mTagSpinner.getSelectedItem().toString())){
                if(!allTags.contains(tag)){
                    allTags.add(tag);
                }
            }
        }
        return allTags;

    }

    @Override
    public void update(Category aCategory) {
        mCategory = aCategory;
        List<String> values = new ArrayList<>();
        values.add("");
        ArrayAdapter adapter = (ArrayAdapter)mTagSpinner.getAdapter();
        for (Tag tag : aCategory.getTags()) {
            values.add(tag.getName());
        }
        adapter.clear();
        adapter.addAll(values);
        cleanTagListView();
    }


    private class TagListViewItem {
        private LinearLayout mTagListViewItem;
        private Tag mTag;

        private TagListViewItem(final LinearLayout aTagListViewItem, final Tag aTag){
            mTagListViewItem = aTagListViewItem;
            mTag = aTag;
        }

        public Tag getTag(){
            return mTag;
        }

        public LinearLayout getTagListViewItem(){
            return mTagListViewItem;
        }
    }
}
