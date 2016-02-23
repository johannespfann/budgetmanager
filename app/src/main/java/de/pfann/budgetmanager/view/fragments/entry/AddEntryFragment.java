package de.pfann.budgetmanager.view.fragments.entry;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import de.pfann.budgetmanager.R;
import de.pfann.budgetmanager.activities.MainActivity;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.view.common.bindings.MenuItemCommandBinding;
import de.pfann.budgetmanager.view.common.bindings.ViewCommandBinding;
import de.pfann.budgetmanager.view.fragments.BaseFragment;

public class AddEntryFragment  extends BaseFragment implements AddEntryFragmentViewModel.Listener{

    @Bind(R.id.addentry_add_another_tag)
    public ImageView mAddAnotherTagView;

    @Bind(R.id.addentry_name)
    public EditText mNameEditText;

    @Bind(R.id.addentry_spinner_category)
    public Spinner mCategorySpinner;

    @Bind(R.id.addentry_plusminus)
    public TextView mPlusMinusTextView;

    @Bind(R.id.addentry_memo)
    public EditText mMemoEditText;

    @Bind(R.id.addentry_amount)
    public EditText mAmountEditText;


    @Inject
    public AddEntryFragmentViewModel mViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        // Inflate the layout for this fragment
        Log.i(MainActivity.TAG, "onCreateView");

        return inflater.inflate(R.layout.add_entry_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        Log.i(MainActivity.TAG, "onCreateOptionsMenu");
        inflater.inflate((R.menu.add_entry_menu), menu);

        new MenuItemCommandBinding().bind(menu.findItem(R.id.action_addCategory), mViewModel.getAddNewEntryCommand());


    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.setListener(this);
    }

    public void addNewTag(final View aView){
        Log.i(MainActivity.TAG, "pressed addNewTag");
    }


    @Override
    public Entry getEntry() {
        Entry entry = new Entry();
        entry.setName(mNameEditText.getText().toString());
        entry.setSum(getSum());
        entry.setMemo(mMemoEditText.getText().toString());
        entry.setCurrentDate(new Date());
        entry.setTags(null);
        entry.setCategory(null);
        return entry;
    }

    private double getSum() {
        double amount = Double.parseDouble(mAmountEditText.getText().toString());
        if(!isAmountPositiv()){
            amount = 0 - amount;
        }
        return amount;
    }

    private boolean isAmountPositiv() {
        switch (mPlusMinusTextView.getText().toString()){
            case "+":
                return true;
            case "-":
                return false;
            default:
                Log.i(MainActivity.TAG,"war wohl nix");
        }
        return false;
    }

    @Override
    public void chanceSign() {
        if (("-").equals(mPlusMinusTextView.getText().toString())){
            mPlusMinusTextView.setText("+");
        }
        else{
            mPlusMinusTextView.setText("-");
        }
    }
}
