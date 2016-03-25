package de.pfann.budgetmanager.view.fragments.home;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.Bind;
import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.view.common.bindings.ViewCommandBinding;
import de.pfann.budgetmanager.view.fragments.BaseFragment;

public class HomeFragment extends BaseFragment implements HomeFragmentViewModel.Listener{

    @Inject
    public HomeFragmentViewModel mViewModel;

    @Bind(R.id.home_floating_button)
    public FloatingActionButton mFloatingActionButtion;

    @Override
    public void onStart() {

        super.onStart();
        new ViewCommandBinding().bind(mFloatingActionButtion, mViewModel.getCommandFloatingAddEntryButton());
        mViewModel.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.home_fragment, container, false);




    }


}
