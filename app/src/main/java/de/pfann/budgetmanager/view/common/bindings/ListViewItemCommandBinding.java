package de.pfann.budgetmanager.view.common.bindings;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import de.pfann.budgetmanager.viewmodel.common.commands.Command;
import de.pfann.budgetmanager.viewmodel.common.commands.CommandListener;

public class ListViewItemCommandBinding implements Binding<ListView,Void>, CommandListener, ListView.OnItemClickListener {

    private Command<Void> mCommand;
    private ListView mView;

    @Override
    public void bind(ListView view, Command<Void> aCommand) {
        mCommand = aCommand;
        mView = view;

        mCommand.setListener(this);
        mView.setOnItemClickListener(this);
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

    }
}
