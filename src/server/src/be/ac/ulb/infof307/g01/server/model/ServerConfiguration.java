package be.ac.ulb.infof307.g01.server.model;

import java.io.File;
import java.net.URL;

/**
 * Singleton class containing the magic numbers and strings required to 
 * configure the server program.
 *
 * @author Groupe01
 */
public class ServerConfiguration {
    
    private static ServerConfiguration _configuration = null;
    
    /** The absolute path to the server's assets folder.**/
    private final String _dataBasePath = "Database.db";
    private final String _testDataBasePath = "TestDatabase.db";
    private final String _sqlPath = "Database.sql";
    
    private ServerConfiguration() { }

    public static ServerConfiguration getInstance() {			
        if(_configuration == null) {
            _configuration = new ServerConfiguration();	
        }
        return _configuration;
    }
    
    private String getAssetServerPath(String fileName) {
        File file = new File("../../assets/server/");
        return file.getAbsolutePath() + File.separatorChar + fileName;
    }
    
    /**
     * Get the database path
     * 
     * @return 
     */
    public String getDataBasePath() {
        String path = _dataBasePath;
        URL classLoader = Thread.currentThread().getContextClassLoader()
                .getResource(_dataBasePath);
        if(classLoader != null) {
            path = classLoader.getPath();
        }
        return path;
    }
    
    public String getTestDataBasePath() {
        return getAssetServerPath(_testDataBasePath);
    }

    public String getSqlPath() {
        String path;
        URL ressource = Thread.currentThread().getContextClassLoader()
                .getResource(_sqlPath);
        if(ressource != null) {
            path = ressource.getPath();
        } else {
            path = getAssetServerPath(_sqlPath);
        }
        
        return path;
    }
    
    public String getTestSqlPath() {
        return getAssetServerPath(_sqlPath);
    }
    
}