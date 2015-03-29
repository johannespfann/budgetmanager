package de.pfann.budgetmanager.database;

/**
 * Created by johannes on 28.03.15.
 */
public class DBManager {

    private static DBManager instance;

    private EntryDataAccesObject;

    private CategoryDataAccessObject;


    private DBManager(){
        // Default
    }

    public static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }

}
