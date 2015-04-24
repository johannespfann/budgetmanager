package de.pfann.budgetmanager.activities.addentryactivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 22.04.15.
 */
public class CategorySpinnerOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private List<Category> mCategories;
    private ArrayAdapter mTagArrayAdapter;
    private Spinner mCategorySpinner;

    /**
     * @param aCategorySpinner The spinner which use this listener
     * @param aCategories      List of categories. Needs to know tags from selected category
     * @param aTagArrayAdapter A ArrayAdapter for the spinner of the tags
     */
    public CategorySpinnerOnItemSelectedListener(final Spinner aCategorySpinner, List<Category> aCategories, final ArrayAdapter aTagArrayAdapter) {
        mCategories = aCategories;
        mTagArrayAdapter = aTagArrayAdapter;
        mCategorySpinner = aCategorySpinner;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i(MainActivity.LOG_TAG,"clicked");
        String value = mCategorySpinner.getSelectedItem().toString();
        for (Category category : mCategories) {
            if (category.getName().contains(value)) {
                List<String> tagsAsStrings = new ArrayList<String>();
                tagsAsStrings.add("");
                for (Tag tag : category.getTags()) {
                    tagsAsStrings.add(tag.getName());
                }
                mTagArrayAdapter.clear();
                mTagArrayAdapter.addAll(tagsAsStrings);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i(MainActivity.LOG_TAG, "nothing selected");
    }
}
