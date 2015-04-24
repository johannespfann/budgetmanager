package de.pfann.budgetmanager.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.activities.addentryactivity.CategorySpinnerOnItemSelectedListener;
import de.pfann.budgetmanager.activities.addentryactivity.TagItemListViewOnClickListener;
import de.pfann.budgetmanager.activities.addentryactivity.TagSpinnerOnItemSelectedListener;
import de.pfann.budgetmanager.database.DBManager;
import de.pfann.budgetmanager.database.DatabaseAccessorFacade;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;

public class AddEntryActivity extends ActionBarActivity {

    private String mName;
    private Category mCategory;
    private double mSum;
    private String mMemo;
    private List<Tag> mTags;



    private List<Category> mCategories;
    private Spinner mSpinnerCategory;
    private Spinner mSpinnerTags;

    private LinearLayout mTagListViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        // Hole Daten
        DBManager dbManager = DBManager.getInstance();
        final DatabaseAccessorFacade dbAccessor =  dbManager.getDatabaseFacade();
        mCategories = dbAccessor.getAllCategories();

        mTagListViewLayout = (LinearLayout) findViewById(R.id.addentry_linearlayout_taglist);

        // Erstelle ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);


        // Erstelle Spinner
        mSpinnerCategory = (Spinner) findViewById(R.id.addentry_spinner_category);
        mSpinnerTags = (Spinner) findViewById(R.id.addentry_spinner_tags);

        List<String> categoriesAsString = new ArrayList<>();
        for(Category category : mCategories){
            categoriesAsString.add(category.getName());
        }

        final ArrayAdapter arrayTagAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
        arrayTagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerTags.setAdapter(arrayTagAdapter);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoriesAsString);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCategory.setAdapter(arrayAdapter);
        mSpinnerCategory.setOnItemSelectedListener(new CategorySpinnerOnItemSelectedListener(mSpinnerCategory,mCategories,arrayTagAdapter));
        mSpinnerTags.setOnItemSelectedListener(new TagSpinnerOnItemSelectedListener(mSpinnerTags));




        LinearLayout linearLayoutTagListItem = new LinearLayout(this);
        linearLayoutTagListItem.setOrientation(LinearLayout.VERTICAL);



    }

    public LinearLayout createNewTagListItem(final Tag aTag){
        final LinearLayout linearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.activity_add_entry_tag_listview_item,null);
        linearLayout.setId(new Random().nextInt());
        TextView middleTextView = (TextView) linearLayout.getChildAt(1);
        middleTextView.setText(aTag.getName());
        ImageView rightImageView = (ImageView) linearLayout.getChildAt(2);
        rightImageView.setClickable(true);
        rightImageView.setOnClickListener(new TagItemListViewOnClickListener(linearLayout));

        return linearLayout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.entry_add, menu);
        return true;
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
        if(id == R.id.actionbar_addnewentry){
            if(allInputsCorrect()) {
                DatabaseAccessorFacade accessorFacade = DBManager.getInstance().getDatabaseFacade();
                accessorFacade.persistEntry(mName, mSum, mMemo, mTags, mCategory);

                cleanUserInterface();
            }
            Log.i(MainActivity.LOG_TAG,"clicked add new entry");
        }
        return super.onOptionsItemSelected(item);
    }

    public void cleanUserInterface(){

    }

    public boolean allInputsCorrect(){
        if(mName.isEmpty()){
            return false;
        }
        if(mSum == 0){
            return false;
        }
        if(mCategory != null){
            return false;
        }
        if(mTags != null){
            return false;
        }
        return true;
    }

    public void addNewTag(final View aView){
        Log.i(MainActivity.LOG_TAG,"clicked addNewTag");
        Log.i(MainActivity.LOG_TAG,"Selected Value in Spinner is: " + mSpinnerTags.getSelectedItem().toString().isEmpty());
        if(!mSpinnerTags.getSelectedItem().toString().isEmpty()) {
            for(Category category : mCategories){
                if(category.getName().contains(mSpinnerCategory.getSelectedItem().toString())){
                    Log.i(MainActivity.LOG_TAG,"Selected Category: " + mSpinnerCategory.getSelectedItem().toString() + " found!");
                    mCategory = category;
                    break;
                }
            }

            for(Tag tag : mCategory.getTags()){
                if(tag.getName().contains(mSpinnerTags.getSelectedItem().toString())){
                    Log.i(MainActivity.LOG_TAG,"Selected Tag: " + mSpinnerTags.getSelectedItem().toString() + " found!");
                    mTagListViewLayout.addView(createNewTagListItem(tag));
                }
            }

            Log.i(MainActivity.LOG_TAG, mSpinnerTags.getSelectedItem().toString());
        }
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


}
