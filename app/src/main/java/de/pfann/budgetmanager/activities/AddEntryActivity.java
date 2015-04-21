package de.pfann.budgetmanager.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    private Spinner mSpinnerCategory;
    private Spinner mSpinnerTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        DBManager dbManager = DBManager.getInstance();
        final DatabaseAccessorFacade dbAccessor =  dbManager.getDatabaseFacade();
        mCategories = dbAccessor.getAllCategories();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mSpinnerCategory = (Spinner) findViewById(R.id.addentry_spinner_category);
        mSpinnerTags = (Spinner) findViewById(R.id.addentry_spinner_tags);

        List<String> dummyTags = new ArrayList<>();
        dummyTags.add("Eintrag 1");
        dummyTags.add("Eintrag 2");
        dummyTags.add("Eintrag 3");

        final ArrayAdapter arrayDummAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dummyTags);
        arrayDummAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerTags.setAdapter(arrayDummAdapter);

        List<String> categoriesAsString = new ArrayList<>();
        for(Category category : mCategories){
            categoriesAsString.add(category.getName());
        }
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoriesAsString);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCategory.setAdapter(arrayAdapter);
        mSpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinnerCategory = (Spinner) findViewById(R.id.addentry_spinner_category);
                Spinner spinnerTags = (Spinner) findViewById(R.id.addentry_spinner_tags);
                String value = spinnerCategory.getSelectedItem().toString();

                for(Category category : mCategories){
                    if(category.getName().contains(value)){
                        List<String> tagsAsStrings = new ArrayList<String>();
                        for(Tag tag : category.getTags()){
                            tagsAsStrings.add(tag.getName());
                        }

                        final ArrayAdapter arrayTagAdapter = new ArrayAdapter(view.getContext(),android.R.layout.simple_spinner_item,tagsAsStrings);
                        arrayTagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerTags.setAdapter(arrayTagAdapter);


                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                   Log.i(MainActivity.LOG_TAG,"nothing selected");
            }
        });
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
        int id = item.getItemId();
        if (id == R.id.actionbar_settings) {
            return true;
        }
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
