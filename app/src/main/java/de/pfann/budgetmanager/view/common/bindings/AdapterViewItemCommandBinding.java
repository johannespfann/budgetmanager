package de.pfann.budgetmanager.view.common.bindings;


import android.view.View;
import android.widget.AdapterView;

import de.pfann.budgetmanager.viewmodel.common.commands.Command;
import de.pfann.budgetmanager.viewmodel.common.commands.CommandListener;

public class AdapterViewItemCommandBinding implements Binding<View,Void>, CommandListener, AdapterView.OnItemClickListener {

    private Command<Void> mCommand;
    private View mView;

    @Override
    public void bind(View view, Command<Void> aCommand) {
        mCommand = aCommand;
    }

    @Override
    public void onIsAvailableChanged(boolean aNewValue) {
        mView.setVisibility(aNewValue ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onIsExecutableChanged(boolean aNewValue) {
        mView.setEnabled(aNewValue);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mCommand.isExecutable()){
            mCommand.execute(null);
        }
    }
}
