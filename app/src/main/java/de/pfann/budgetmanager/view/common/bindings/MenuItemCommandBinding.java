package de.pfann.budgetmanager.view.common.bindings;

import android.view.MenuItem;

import de.pfann.budgetmanager.view.common.bindings.Binding;
import de.pfann.budgetmanager.viewmodel.common.commands.Command;
import de.pfann.budgetmanager.viewmodel.common.commands.CommandListener;

public class MenuItemCommandBinding implements Binding<MenuItem,Void>, CommandListener, MenuItem.OnMenuItemClickListener{

    private Command<Void> mCommand;
    private MenuItem mMenuItem;

    @Override
    public void bind(MenuItem aMenuItem, Command<Void> aCommand) {
        mCommand = aCommand;
        mMenuItem = aMenuItem;

        mCommand.setListener(this);
        mMenuItem.setOnMenuItemClickListener(this);
    }

    @Override
    public void onIsAvailableChanged(boolean aNewValue) {
        mMenuItem.setVisible(aNewValue);
    }

    @Override
    public void onIsExecutableChanged(boolean aNewValue) {
        mMenuItem.setEnabled(aNewValue);
    }

    @Override
    public boolean onMenuItemClick(MenuItem aItem) {
        if (mCommand.isExecutable()) {
            mCommand.execute(null);
        }
        return true;
    }
}

