package de.pfann.budgetmanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import de.pfann.budgetmanager.database.BudgetDBHelper;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BudgetDBHelper db = new BudgetDBHelper(getApplicationContext());

        long category_id = db.createCategory("Lifestyle And More");

        long tag_id1 = db.createTag("war unwichtig",category_id);
        long tag_id2 = db.createTag("naja",category_id);
        long tag_id3 = db.createTag("asdf",category_id);

        long[] longarray = new long[3];
        longarray[0] = tag_id1;
        longarray[1] = tag_id2;
        longarray[2] = tag_id3;
        db.createEntry("Feiern", -25 , category_id, longarray);
        db.showAllTables();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
