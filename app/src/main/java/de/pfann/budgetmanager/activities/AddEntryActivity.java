package de.pfann.budgetmanager.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.database.DBManager;
import de.pfann.budgetmanager.database.DatabaseAccessorFacade;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;

public class AddEntryActivity extends ActionBarActivity {

    private String mName;
    private List<Category> mCategories;
    private String mCategory;
    private double mSum;
    private String mMemo;
    private List<Tag> mTags;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);


        DBManager dbManager = DBManager.getInstance();
        DatabaseAccessorFacade dbAccessor =  dbManager.getDatabaseFacade();
        mCategories = dbAccessor.getAllCategories();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);




        Spinner spinner = (Spinner) findViewById(R.id.addentry_spinner_category);
        List<String> categoriesAsString = new ArrayList<>();
        for(Category category : mCategories){
            categoriesAsString.add(category.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoriesAsString);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.entry_add, menu);
        return true;
    }

    public void change(View aView){
        TextView textview = (TextView) findViewById(R.id.plusminus);
        String currentText = String.valueOf(textview.getText());
        Log.i("","Wurde geklickt: " + currentText);
        if(currentText.contains("-")){
            textview.setText("+");
        }
        if(currentText.contains("+")){
            textview.setText("-");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionbar_settings) {
            return true;
        }

        if(id == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
