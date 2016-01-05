package de.pfann.budgetmanager.view.common.bindings;


import de.pfann.budgetmanager.viewmodel.common.commands.Command;

/***
 * Interface for Bindings
 */
public interface Binding<View, CommandParameter> {
    void bind(View view, Command<CommandParameter> command);
}