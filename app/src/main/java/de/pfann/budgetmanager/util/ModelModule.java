package de.pfann.budgetmanager.util;

import dagger.Module;
import de.pfann.budgetmanager.activities.NavigationDrawer;

@SuppressWarnings("unused")
@Module(
        injects = {
               NavigationDrawer.class,
        })
public class ModelModule {

    public ModelModule() {

    }
}
