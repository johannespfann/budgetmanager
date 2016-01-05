package de.pfann.budgetmanager.util;

import dagger.Module;
import de.pfann.budgetmanager.view.fragments.navdrawer.NavigationDrawer;

@SuppressWarnings("unused")
@Module(
        injects = {
               NavigationDrawer.class,
        })
public class ModelModule {

    public ModelModule() {

    }
}
