package de.pfann.budgetmanager.database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.Tag;

public class OrmLiteDatabaseConfigUtil extends OrmLiteConfigUtil {

    private final static String ROOT_DIR_PROPERTY = "user.dir";
    private final static String CONFIG_PATH = "/app/src/main/res/raw/";
    private final static String CONFIG_NAME = "ormlite_config.txt";

    private static final Class<?>[] mClasses = new Class[] {
            Tag.class, Category.class, Entry.class
    };

    public static void main(String[] args) throws IOException, SQLException{
        File configFile = new File(getFullConfigPath(CONFIG_PATH,CONFIG_NAME,ROOT_DIR_PROPERTY));
        if(configFile.exists()) {
            configFile.delete();
            configFile = new File(getFullConfigPath(CONFIG_PATH,CONFIG_NAME,ROOT_DIR_PROPERTY));
        }
        writeConfigFile(configFile, mClasses);
    }

    private static String getFullConfigPath(final String aConfigPath, final String aConfigName, final String aProjectRootSystemProperty){
        String projectRoot = System.getProperty(aProjectRootSystemProperty);
        return projectRoot + aConfigPath + aConfigName;
    }

}
