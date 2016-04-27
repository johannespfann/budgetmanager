package de.pfann.budgetmanager.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;
import de.greenrobot.event.EventBus;
import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.database.CategoryDAOImpl;
import de.pfann.budgetmanager.database.EntryDAO;
import de.pfann.budgetmanager.database.EntryDAOImpl;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.util.ModelModule;
import de.pfann.budgetmanager.util.StorageFragment;
import de.pfann.budgetmanager.util.events.NavigationEvent;
import de.pfann.budgetmanager.view.fragments.entry.AddEntryFragment;
import de.pfann.budgetmanager.view.fragments.history.HistoryFragment;
import de.pfann.budgetmanager.view.fragments.home.HomeFragment;
import de.pfann.budgetmanager.view.fragments.navdrawer.NavigationDrawer;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "budgetmanager";

    private static final String HOME_FRAGMENT_LAYOUT_TAG = "home_fragment";
    private static final String HISTORY_FRAGMENT_LAYOUT_TAG = "history_fragment";
    private static final String BALANCE_FRAGMENT_LAYOUT_TAG = "balance_fragment";
    private static final String ADD_CATEGORY_FRAGMENT_LAYOUT_TAG = "add_category_fragment";
    private static final String ADD_ENTRY_FRAGMENT_LAYOUT_TAG = "add_entry_fragment";
    private static final String TAG_STORAGE_FRAGMENT = "storage_fragment";

    private ActionBarDrawerToggle mActionBarBrawerToggle;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawer mNavigationDrawer;

    private ObjectGraph mObjectGraph;
    private EventBus mEventBus = EventBus.getDefault();

    @Override
    protected void onPause(){
        mEventBus.unregister(this);
        super.onPause();
    }


    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        StorageFragment storageFragment = (StorageFragment) getSupportFragmentManager()
                .findFragmentByTag(TAG_STORAGE_FRAGMENT);
        if (storageFragment == null) {
            storageFragment = new StorageFragment();
            getSupportFragmentManager().beginTransaction().add(storageFragment,
                    TAG_STORAGE_FRAGMENT).commit();
            getSupportFragmentManager().executePendingTransactions();
        }
        mObjectGraph = ObjectGraph.create(new ModelModule(storageFragment));

        try {
            CategoryDAOImpl categoryDAO = new CategoryDAOImpl(getApplicationContext());
            Category newCategory = new Category("Arbeit");
            categoryDAO.persistCategory(newCategory);
            Date currentDate = new Date();
            Log.i(TAG, currentDate.toString());
            EntryDAO entryDAO = new EntryDAOImpl(getApplicationContext());
            entryDAO.addEntry(new Entry("Kosten",new Date(),newCategory));

            List<Entry> entries = entryDAO.getEntries();

            for(Entry entry : entries){
                Log.i(TAG,"Entry:");
                Log.i(TAG," -Name: " + entry.getName());
                Log.i(TAG," -Date: " + entry.getCurrentDate().toString());
            }

            List<Category> categories = categoryDAO.getCategories();
            Log.i(TAG,"Size: " + categories.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Drawer and Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawer = new NavigationDrawer(this, findViewById(android.R.id.content));
        mActionBarBrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,0,0);
        mDrawerLayout.setDrawerListener(mActionBarBrawerToggle);
        mActionBarBrawerToggle.syncState();
        Log.i(TAG, "Post Home Event");
        mEventBus.register(this);
        mEventBus.post(NavigationEvent.Home);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
        }

        if (mActionBarBrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void inject(Object object) {
        mObjectGraph.inject(object);
    }

    public void onEvent(NavigationEvent event) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch(event) {
            case Home:
                Log.i(TAG,"Home event");
                HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_LAYOUT_TAG);
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                }
                fragmentTransaction.replace(R.id.container, homeFragment, HOME_FRAGMENT_LAYOUT_TAG).addToBackStack(null).commit();
                break;
            case Add_Entry:
                Log.i(TAG,"Add_Entry event");
                AddEntryFragment addEntryFragment = (AddEntryFragment) getSupportFragmentManager().findFragmentByTag(ADD_ENTRY_FRAGMENT_LAYOUT_TAG);
                if(addEntryFragment == null){
                    addEntryFragment = new AddEntryFragment();
                }
                fragmentTransaction.replace(R.id.container, addEntryFragment, ADD_ENTRY_FRAGMENT_LAYOUT_TAG).addToBackStack(null).commit();
                break;
            case History:
                Log.i(TAG,"History event");
                HistoryFragment historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(HISTORY_FRAGMENT_LAYOUT_TAG);
                if(historyFragment ==  null){
                    historyFragment = new HistoryFragment();
                }
                fragmentTransaction.replace(R.id.container, historyFragment, HISTORY_FRAGMENT_LAYOUT_TAG).addToBackStack(null).commit();
                break;
            case Settings:
                Log.i(TAG,"Settings event");
                break;
            case Profile:
                Log.i(TAG, "Profile event");
                break;
        }
        mNavigationDrawer.closeDrawer();
    }
}
