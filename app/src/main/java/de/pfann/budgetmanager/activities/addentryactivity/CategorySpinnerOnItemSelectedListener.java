package de.pfann.budgetmanager.activities.addentryactivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Category;

/**
 * Created by johannes on 22.04.15.
 */
public class CategorySpinnerOnItemSelectedListener implements AdapterView.OnItemSelectedListener, CategorySpinnerObserveable {

    private List<Category> mCategories;
    private Spinner mCategorySpinner;

    List<CategorySpinnerObserver> mObservers;

    /**
     * @param aCategorySpinner The spinner which use this listener
     * @param aCategories      List of categories. Needs to know tags from selected category
     */
    public CategorySpinnerOnItemSelectedListener(final Spinner aCategorySpinner, List<Category> aCategories) {
        mCategories = aCategories;
        mCategorySpinner = aCategorySpinner;
        mObservers = new ArrayList<>();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i(MainActivity.LOG_TAG,"clicked");
        String value = mCategorySpinner.getSelectedItem().toString();
        for (Category category : mCategories) {
            if (category.getName().contains(value)) {
                notifyObservers(category);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i(MainActivity.LOG_TAG, "nothing selected");
    }

    @Override
    public void deleteObserver(CategorySpinnerObserver aObserver) {
        mObservers.remove(aObserver);
    }

    @Override
    public void addObserver(CategorySpinnerObserver aObserver) {
        mObservers.add(aObserver);
    }

    @Override
    public void notifyObservers(final Category aCategory) {
        for(CategorySpinnerObserver observer : mObservers){
            observer.update(aCategory);
        }
    }
}
