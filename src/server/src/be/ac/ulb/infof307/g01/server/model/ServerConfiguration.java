package be.ac.ulb.infof307.g01.server.model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class containing the magic numbers and strings required to 
 * configure the server program.
 *
 * @author Groupe01
 */
public class ServerConfiguration {
    
    private static ServerConfiguration _configuration = null;
    
    private static final String CONFIG_FILE = "config.properties";
    private static final String FILE_PREFIX = "file:";
    private static final String JAR_PREFIX = "jar:";
    
    /** Configuration keys and default values.**/
    private final String DATABASE_NAME_DEFAULT = "Database.db";
    private final String DATABASE_NAME_CONFIG = "database-name";
    private final String TEST_DATABASE_NAME_DEFAULT = "TestDatabase.db";
    private final String TEST_DATABASE_NAME_CONFIG = "test-database-name";
    private final String SQL_SCRIPT_NAME_DEFAULT = "Database.sql";
    private final String SQL_SCRIPT_NAME_CONFIG = "SQL-script";
    
    private Properties _propertiesFile;
    
    private ServerConfiguration() { 
        loadConfigurationFile(CONFIG_FILE);
    }

    public static ServerConfiguration getInstance() {			
        if(_configuration == null) {
            _configuration = new ServerConfiguration();	
        }
        return _configuration;
    }
    
    private void loadConfigurationFile(String fileName) {
        String clientConfigFilePath = addJarOrFilePrefix(getAssetPath(fileName));
        try {
            URL path = new URL(clientConfigFilePath);
            _propertiesFile = new Properties();
            _propertiesFile.load(path.openStream());
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
    
    /**
     * Gets the path to a specific asset file.
     * @param fileName the asset file's name
     * @return the asset file's absolute path
     */
    private String getAssetPath(String fileName) {
        File file = new File("../../assets/server/");
        return file.getAbsolutePath() + File.separatorChar + fileName;
    }
    
    public String getDatabasePath() {
        String path = _propertiesFile.getProperty(DATABASE_NAME_CONFIG, DATABASE_NAME_DEFAULT);
        URL classLoader = Thread.currentThread().getContextClassLoader()
                .getResource(path);
        if(classLoader != null) {
            path = classLoader.getPath();
        }
        return path;
    }
    
    public String getTestDatabasePath() {
        String path = _propertiesFile.getProperty(TEST_DATABASE_NAME_CONFIG, TEST_DATABASE_NAME_DEFAULT);
        return getAssetPath(path);
    }

    public String getSqlPath() {
        String path = _propertiesFile.getProperty(SQL_SCRIPT_NAME_CONFIG, SQL_SCRIPT_NAME_DEFAULT);
        URL ressource = Thread.currentThread().getContextClassLoader()
                .getResource(path);
        if(ressource != null) {
            path = ressource.getPath();
        } else {
            path = getAssetPath(path);
        }
        
        return path;
    }
    
    public String getTestSqlPath() {
        String path = _propertiesFile.getProperty(SQL_SCRIPT_NAME_CONFIG, SQL_SCRIPT_NAME_DEFAULT);
        return getAssetPath(path);
    }
    
    /**
     * Add the "file:" prefix before file path if it's not already there.
     * 
     * @param filePath the file path
     * @return the new file path
     */
    public static String addFilePrefix(String filePath) {
        if(!filePath.startsWith(FILE_PREFIX)) {
            filePath = FILE_PREFIX + filePath;
        }
        return filePath;
    }
    
    /**
     * Add the "jar:" prefix before imagePath if it begins with the "file:" prefix.
     * 
     * @param imagePath the image path
     * @return the new path
     */
    public static String addJarPrefix(String imagePath) {
        if(imagePath.startsWith(FILE_PREFIX)) {
            imagePath = JAR_PREFIX + imagePath;
        }
        return imagePath;
    }
    
    /**
     * Add "jar:" prefix before imagePath if it begins with the "file:" prefix,
     * otherwise add the "file:" prefix.
     * 
     * @param imagePath the image path
     * @return the new path
     */
    public static String addJarOrFilePrefix(String imagePath) {
        if(imagePath.startsWith(FILE_PREFIX)) {
            imagePath = addJarPrefix(imagePath);
        } else {
            imagePath = addFilePrefix(imagePath);
        }
        return imagePath;
    }
    
}