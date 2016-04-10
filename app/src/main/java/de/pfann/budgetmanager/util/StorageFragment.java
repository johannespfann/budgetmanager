package de.pfann.budgetmanager.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import de.pfann.budgetmanager.database.CategoryDAO;
import de.pfann.budgetmanager.database.CategoryDAOImpl;
import de.pfann.budgetmanager.database.EntryDAO;
import de.pfann.budgetmanager.database.EntryDAOImpl;
import de.pfann.budgetmanager.database.TagDAO;
import de.pfann.budgetmanager.database.TagDAOImpl;


public class StorageFragment extends Fragment {

    private CategoryDAO mCategoryDAO;
    private EntryDAO mEntryDAO;
    private TagDAO mTagDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mCategoryDAO = new CategoryDAOImpl(getActivity().getApplicationContext());
        mEntryDAO = new EntryDAOImpl(getActivity().getApplicationContext());
        mTagDAO = new TagDAOImpl(getActivity().getApplicationContext());
    }

    public CategoryDAO getCategoryDAO() {
        return mCategoryDAO;
    }

    public EntryDAO getEntryDAO() {
        return mEntryDAO;
    }

    public TagDAO getTagDAO() {
        return mTagDAO;
    }
}
