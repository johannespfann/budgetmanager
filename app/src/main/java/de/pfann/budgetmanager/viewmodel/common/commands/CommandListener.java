package de.pfann.budgetmanager.viewmodel.common.commands;


public interface CommandListener {

    void onIsAvailableChanged(boolean newValue);

    void onIsExecutableChanged(boolean newValue);
}
