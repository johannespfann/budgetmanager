package de.pfann.budgetmanager.activities.addentryactivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import de.pfann.budgetmanager.activities.MainActivity;

/**
 * Created by johannes on 22.04.15.
 */
public class TagSpinnerOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private Spinner mTagSpinner;

    public TagSpinnerOnItemSelectedListener(final Spinner aTagSpinner){
        mTagSpinner = aTagSpinner;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i(MainActivity.LOG_TAG,mTagSpinner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
