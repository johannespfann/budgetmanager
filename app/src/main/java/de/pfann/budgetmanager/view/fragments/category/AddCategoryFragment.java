package de.pfann.budgetmanager.view.fragments.category;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.view.fragments.BaseFragment;

public class AddCategoryFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_category_fragment, container, false);
    }

}
