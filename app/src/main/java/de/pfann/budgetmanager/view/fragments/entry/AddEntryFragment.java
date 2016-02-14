package de.pfann.budgetmanager.view.fragments.entry;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.view.fragments.BaseFragment;

public class AddEntryFragment  extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        // Inflate the layout for this fragment
        Log.i(MainActivity.TAG, "onCreateView");
        return inflater.inflate(R.layout.add_entry_fragment, container, false);
    }
}
