package de.pfann.budgetmanager.view.fragments.navdrawer;


import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.view.common.bindings.MenuItemCommandBinding;


public class NavigationDrawer implements NavigationDrawerViewModel.Listener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_drawer)
    NavigationView mNavigationView;

    @Inject
    NavigationDrawerViewModel mViewModel;


    private Activity mActivity;

    public NavigationDrawer(final MainActivity aActivity, View aView){
        ButterKnife.bind(this, aView);
        aActivity.inject(this);


        mActivity = aActivity;

        if(mViewModel == null) {

        }
        mViewModel.setListener(this);

        Menu menu = mNavigationView.getMenu();

        new MenuItemCommandBinding().bind(menu.findItem(R.id.drawer_item_home),mViewModel.getNavigateToHome());
        new MenuItemCommandBinding().bind(menu.findItem(R.id.drawer_item_category),mViewModel.getNavigateToCategory());
        new MenuItemCommandBinding().bind(menu.findItem(R.id.drawer_item_entry),mViewModel.getNavigateToEntry());
    }

    @Override
    public void onNavigationDrawerItemSelected(int aItemId) {
        MenuItem item = mNavigationView.getMenu().findItem(aItemId);
        if(item != null) {
            item.setChecked(true);
        }
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
}