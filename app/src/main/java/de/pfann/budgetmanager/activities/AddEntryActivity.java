package de.pfann.budgetmanager.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
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
import de.pfann.budgetmanager.activities.addentryactivity.TagsComponent;
import de.pfann.budgetmanager.database.DBManager;
import de.pfann.budgetmanager.database.DatabaseAccessorFacade;
import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Tag;



public class AddEntryActivity extends ActionBarActivity{

    private String mName;
    private Category mCategory;
    private double mSum;
    private String mMemo;
    private List<Tag> mTags;

    private List<Category> mCategories;
    private Spinner mCategorySpinner;
    private Spinner mTagSpinner;

    private LinearLayout mTagListViewLayout;
    private TagsComponent mTagsComponent;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        // Hole Daten
        DBManager dbManager = DBManager.getInstance();
        final DatabaseAccessorFacade dbAccessor =  dbManager.getDatabaseFacade();
        mCategories = dbAccessor.getAllCategories();

        // Erstelle Spinner
        mCategorySpinner = (Spinner) findViewById(R.id.addentry_spinner_category);
        mTagSpinner = (Spinner) findViewById(R.id.addentry_spinner_tags);


        final ArrayAdapter tagArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
        tagArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTagSpinner.setAdapter(tagArrayAdapter);

        // SetUp TagListView
        mTagListViewLayout = (LinearLayout) findViewById(R.id.addentry_linearlayout_taglist);


        mTagsComponent = new TagsComponent(getApplicationContext(),mTagListViewLayout, mTagSpinner);


        // Erstelle ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);




        List<String> categoriesAsString = new ArrayList<>();
        for(Category category : mCategories){
            categoriesAsString.add(category.getName());
        }


        final ArrayAdapter categoryArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoriesAsString);
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategorySpinner.setAdapter(categoryArrayAdapter);


        CategorySpinnerOnItemSelectedListener categorySpinnerListener = new CategorySpinnerOnItemSelectedListener(mCategorySpinner,mCategories);
        categorySpinnerListener.addObserver(mTagsComponent);
        mCategorySpinner.setOnItemSelectedListener(categorySpinnerListener);
        mTagSpinner.setOnItemSelectedListener(new TagSpinnerOnItemSelectedListener(mTagSpinner));

    }

    public LinearLayout createLinearLayout(){
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout aLinearLayout = (LinearLayout)inflater.inflate(R.layout.activity_add_entry_tag_listview_item,null);
        aLinearLayout.setId(new Random().nextInt());
        TextView middleTextView = (TextView) aLinearLayout.getChildAt(1);
        middleTextView.setText("asdfads");
        ImageView rightImageView = (ImageView) aLinearLayout.getChildAt(2);
        rightImageView.setClickable(true);
        rightImageView.setOnClickListener(new TagItemListViewOnClickListener(aLinearLayout));
        return aLinearLayout;
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

            }
            Log.i(MainActivity.LOG_TAG,"clicked add new entry");
        }
        return super.onOptionsItemSelected(item);
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
        if(mTagSpinner.getSelectedItem().toString().isEmpty()) {
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.activity_add_entry_tag_listview_item,null);
        mTagsComponent.addNewTagListViewItem(linearLayout);
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
