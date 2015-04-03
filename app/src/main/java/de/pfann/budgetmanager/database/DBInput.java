package de.pfann.budgetmanager.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 02.04.15.
 */
public class DBInput {

    public void invoke(Context context) {
        BudgetDBHelper budgetDBHelper = new BudgetDBHelper(context);
        DBManager dbManager = DBManager.initialize(budgetDBHelper);
        DatabaseAccessorFacade dbFacade = dbManager.getDatabaseFacade();

        if(!dbFacade.categoryAlreadyExists("Wohnkosten")) {
            Category category1 = dbFacade.persistCategory("Wohnkosten");
        }

        if(!dbFacade.categoryAlreadyExists("Versicherungen")) {
            Category category2 = dbFacade.persistCategory("Versicherungen");
        }

        if(!dbFacade.categoryAlreadyExists("Lebensmittel")) {
            Category category3 = dbFacade.persistCategory("Lebensmittel");
        }

        if(!dbFacade.categoryAlreadyExists("Freizeit")) {
            Category category4 = dbFacade.persistCategory("Freizeit");
        }

        if(!dbFacade.categoryAlreadyExists("Einnahmen")) {
            Category category5 = dbFacade.persistCategory("Einnahmen");
            Tag tag5_1 = dbFacade.persistTag("Gehalt - Novum", category5);
            Tag tag5_2 = dbFacade.persistTag("App Verkäufe", category5);
            Tag tag5_3 = dbFacade.persistTag("unregelmäßig",category5);
            List<Tag> tags5_1 = new ArrayList<Tag>();
            tags5_1.add(tag5_1);

            List<Tag> tags5_2 = new ArrayList<>();
            tags5_2.add(tag5_2);
            tags5_2.add(tag5_3);
            Entry entry5_1 = dbFacade.persistEntry("Gehalt - Novum", 2300, "Monatliches Gehalt",tags5_1,category5);
            Entry entry5_2 = dbFacade.persistEntry("Verkauf - Googlestore", 50, "MathBob", tags5_2,category5);
        }
        budgetDBHelper.showAllTables();
    }
}
