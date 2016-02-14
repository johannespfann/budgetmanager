package de.pfann.budgetmanager.view.fragments.category;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.view.common.bindings.MenuItemCommandBinding;
import de.pfann.budgetmanager.view.fragments.BaseFragment;

public class AddCategoryFragment extends BaseFragment implements AddCategoryFragmentViewModel.Listener {


    @Inject
    AddCategoryFragmentViewModel mViewModel;

    @Bind(R.id.add_category_fragment_name)
    EditText mNameEditText;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        // Inflate the layout for this fragment
        Log.i(MainActivity.TAG,"onCreateView");
        return inflater.inflate(R.layout.add_category_fragment, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        Log.i(MainActivity.TAG, "onCreateOptionsMenu");
        inflater.inflate((R.menu.add_category_menu), menu);

        new MenuItemCommandBinding().bind(menu.findItem(R.id.action_addCategory), mViewModel.getPersistNewCategoryCommand());
        mViewModel.setListener(this);
    }

    @Override
    public String onAddCategoryClicked() {
        Log.i(MainActivity.TAG,"onAddCategoryClicked");
        return mNameEditText.getText().toString();
    }

    @Override
    public void cleanView() {
        mNameEditText.setText("");
    }
}
