package de.pfann.budgetmanager.view.fragments.entry;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.Bind;
import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.view.common.bindings.MenuItemCommandBinding;
import de.pfann.budgetmanager.view.fragments.BaseFragment;

public class AddEntryFragment  extends BaseFragment implements AddEntryFragmentViewModel.Listener{

    @Bind(R.id.addentry_add_another_tag)
    public ImageView mAddAnotherTagView;


    @Inject
    public AddEntryFragmentViewModel mViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        // Inflate the layout for this fragment
        Log.i(MainActivity.TAG, "onCreateView");
        return inflater.inflate(R.layout.add_entry_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        Log.i(MainActivity.TAG, "onCreateOptionsMenu");
        inflater.inflate((R.menu.add_entry_menu), menu);

        new MenuItemCommandBinding().bind(menu.findItem(R.id.action_addCategory), mViewModel.getAddNewEntryCommand());
        mViewModel.setListener(this);
    }


    public void addNewTag(final View aView){
        Log.i(MainActivity.TAG,"pressed addNewTag");
    }


}
