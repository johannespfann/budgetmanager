package de.pfann.budgetmanager.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

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
import de.pfann.budgetmanager.util.events.NavigationEvent;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "budgetmanager";

    private ActionBarDrawerToggle mActionBarBrawerToggle;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

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
        mEventBus.register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Start App!");
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ButterKnife.bind(this);

        mObjectGraph = ObjectGraph.create(new ModelModule());
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



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id. navigation_drawer);


        mNavigationDrawer = new NavigationDrawer(this, findViewById(android.R.id.content));
        mActionBarBrawerToggle = new ActionBarDrawerToggle
                (
                        this,
                        mDrawerLayout,
                        mToolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close
                )
        {

        };
        mDrawerLayout.setDrawerListener(mActionBarBrawerToggle);
        mActionBarBrawerToggle.syncState();

        mDrawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"wurde geklickt on DrawerLAyout");
            }
        });
        mNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"wurde geklickt");
            }
        });
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
        Log.i(MainActivity.TAG, "MainActivity onEven()");
    }
}
