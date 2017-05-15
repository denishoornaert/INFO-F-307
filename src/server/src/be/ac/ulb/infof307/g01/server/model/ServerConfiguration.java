package be.ac.ulb.infof307.g01.server.model;

import be.ac.ulb.infof307.g01.common.model.ConfigurationModel;
import java.io.File;
import java.net.URL;

/**
 * Singleton class containing the magic numbers and strings required to 
 * configure the server program.
 *
 * @author Groupe01
 */
public class ServerConfiguration extends ConfigurationModel {
    
    private static ServerConfiguration _configuration = null;
    private static ServerConfiguration _testConfiguration = null;
    
    /** Configuration keys and default values.**/
    private final String DATABASE_NAME_DEFAULT = "Database.db";
    private final String DATABASE_NAME_CONFIG = "database-name";
    private final String TEST_DATABASE_NAME_DEFAULT = "TestDatabase.db";
    private final String TEST_DATABASE_NAME_CONFIG = "test-database-name";
    private final String SQL_SCRIPT_NAME_DEFAULT = "Database.sql";
    private final String SQL_SCRIPT_NAME_CONFIG = "SQL-script";
    
    private ServerConfiguration() { 
        this(false);
    }
    
    private ServerConfiguration(final boolean isTest) { 
        super(isTest);
    }
    
    /**
     * Gets the path to a specific asset file.
     * @param fileName the asset file's name
     * @return the asset file's absolute path
     */
    @Override
    protected String getAssetPath(final String fileName) {
        String result;
        if(_isTest) {
            final File file = new File("../../assets/server/");
            result = file.getAbsolutePath() + File.separatorChar + fileName;
        } else {
            result = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
        }
        return result;
    }
    
    public String getDatabasePath() {
        String path = _propertiesFile.getProperty(DATABASE_NAME_CONFIG, DATABASE_NAME_DEFAULT);
        final URL classLoader = Thread.currentThread().getContextClassLoader()
                .getResource(path);
        if(classLoader != null) {
            path = classLoader.getPath();
        }
        return path;
    }
    
    public String getTestDatabasePath() {
        final String path = _propertiesFile.getProperty(TEST_DATABASE_NAME_CONFIG, TEST_DATABASE_NAME_DEFAULT);
        return getAssetPath(path);
    }

    public String getSqlPath() {
        String path = _propertiesFile.getProperty(SQL_SCRIPT_NAME_CONFIG, SQL_SCRIPT_NAME_DEFAULT);
        final URL ressource = Thread.currentThread().getContextClassLoader()
                .getResource(path);
        if(ressource != null) {
            path = ressource.getPath();
        } else {
            path = getAssetPath(path);
        }
        
        return path;
    }
    
    public String getTestSqlPath() {
        final String path = _propertiesFile.getProperty(SQL_SCRIPT_NAME_CONFIG, SQL_SCRIPT_NAME_DEFAULT);
        return getAssetPath(path);
    }
    
    
    /////////////////// STATIC ///////////////////
    
    public static ServerConfiguration getInstance() {			
        if(_configuration == null) {
            _configuration = new ServerConfiguration();	
        }
        return _configuration;
    }
    
    public static ServerConfiguration getTestInstance() {
        if(_testConfiguration == null) {
            _testConfiguration = new ServerConfiguration(true);
        }
        return _testConfiguration;
    }
    
}