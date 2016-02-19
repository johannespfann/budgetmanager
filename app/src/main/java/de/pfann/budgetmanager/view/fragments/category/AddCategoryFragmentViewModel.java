package de.pfann.budgetmanager.view.fragments.category;


import android.util.Log;

import java.sql.SQLException;

import javax.inject.Inject;

import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.database.CategoryDAO;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.viewmodel.common.commands.Command;


public class AddCategoryFragmentViewModel {

    private Listener mListener;

    @Inject
    CategoryDAO mCategoryDAO;

    private final Command<Void> mPersistNewCategoryCommand = new Command<Void>() {
        @Override
        public void execute(Void parameter) {
            Log.i(MainActivity.TAG,"pressed PersistNewCategory");
            if(mListener != null) {
                String categoryName = mListener.onAddCategoryClicked();
                if(categoryName != null){
                    Category category = new Category();
                    category.setName(categoryName);
                    try {
                        mCategoryDAO.persistCategory(category);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    finally {
                        mListener.cleanView();
                    }
                }
            }
        }
    };

    @Inject
    public AddCategoryFragmentViewModel(){
        // Default Constructor
    }


    public void setListener(Listener aListener) {
        mListener = aListener;
    }

    public Command<Void> getPersistNewCategoryCommand() {
        return mPersistNewCategoryCommand;
    }

    public interface Listener {

        String onAddCategoryClicked();
        void cleanView();
    }
}
