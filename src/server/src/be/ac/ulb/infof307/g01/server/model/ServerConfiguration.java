package be.ac.ulb.infof307.g01.server.model;

import java.io.File;

/**
 * Class used almost everywhere beacause this is the one who contains all the
 * magic numbers and magic string (relative paths, url, ...).
 * The class must be accesible from almst everywhere therefore we design it as a singleton.
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
    
    public String getDataBasePath() {
        return Thread.currentThread().getContextClassLoader().getResource(_dataBasePath).getPath();
    }
    
    public String getTestDataBasePath() {
        return getAssetServerPath(_testDataBasePath);
    }

    public String getSqlPath() {
        return getAssetServerPath(_sqlPath);
    }
    
}