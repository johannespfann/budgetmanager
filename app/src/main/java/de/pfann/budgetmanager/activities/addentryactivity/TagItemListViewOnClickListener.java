package de.pfann.budgetmanager.activities.addentryactivity;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import de.pfann.budgetmanager.activities.MainActivity;

/**
 * Created by johannes on 23.04.15.
 */
public class TagItemListViewOnClickListener implements View.OnClickListener {

    private LinearLayout mLinearLayoutItem;

    public TagItemListViewOnClickListener(final LinearLayout aLinearLayoutItem){
        mLinearLayoutItem = aLinearLayoutItem;
    }

    @Override
    public void onClick(View v) {
        Log.i(MainActivity.LOG_TAG, "clicked: " + mLinearLayoutItem.getId());
        LinearLayout parentLayout = (LinearLayout) mLinearLayoutItem.getParent();
        parentLayout.removeView(mLinearLayoutItem);
    }
}
