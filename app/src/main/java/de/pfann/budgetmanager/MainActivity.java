package de.pfann.budgetmanager;


import android.app.Activity;

import android.app.Notification;
import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;


import de.pfann.budgetmanager.database.DBInput;



public class MainActivity extends ActionBarActivity {

    private static final String LOG_TAG = "BudgetManager_Main";

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;;
    private ListView mDrawerList;
    private int[] mIcons;
    private String mTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DBInput().invoke(getApplicationContext());


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);


        mTitle = "test";

        mPlanetTitles = new String[]{"Erste Page","Zweite Page","Dritte Page"};
        mIcons = new int[]{R.drawable.ic_action_next_item,R.drawable.ic_action_next_item,R.drawable.ic_action_next_item};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        MenuListAdapter mMenuAdapter = new MenuListAdapter(this,mPlanetTitles,mIcons);

        mDrawerList.setAdapter(mMenuAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menue, menu);
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.bla){
            Log.i(LOG_TAG,"bla was pressed");
            boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
            if(isDrawerOpen){
                mDrawerLayout.closeDrawer(mDrawerList);
            }
            else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class DrawerItemClickListener implements ListView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.i(LOG_TAG,"Position: " + position);
       }



    }




}
