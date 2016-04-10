package de.pfann.budgetmanager.util;

import dagger.Module;
import dagger.Provides;
import de.pfann.budgetmanager.database.CategoryDAO;
import de.pfann.budgetmanager.database.EntryDAO;
import de.pfann.budgetmanager.view.fragments.category.AddCategoryFragment;
import de.pfann.budgetmanager.view.fragments.entry.AddEntryFragment;
import de.pfann.budgetmanager.view.fragments.history.HistoryFragment;
import de.pfann.budgetmanager.view.fragments.home.HomeFragment;
import de.pfann.budgetmanager.view.fragments.navdrawer.NavigationDrawer;

@SuppressWarnings("unused")
@Module(
        injects = {
               NavigationDrawer.class, AddEntryFragment.class,AddCategoryFragment.class, HomeFragment.class, HistoryFragment.class
        })
public class ModelModule {

    private StorageFragment mStorageFragment;

    public ModelModule(StorageFragment aStorageFragment) {
        mStorageFragment = aStorageFragment;
    }

    @Provides
    public CategoryDAO getCategoryDAO(){
        return mStorageFragment.getCategoryDAO();
    }


    @Provides
    public EntryDAO getEntryDAO(){
        return mStorageFragment.getEntryDAO();
    }


/*
    @Provides
    public TagDAO getTagDAO(){
        return mStorageFragment.getTagDAO();
    }
*/
}
