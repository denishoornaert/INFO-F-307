package be.ac.ulb.infof307.g01.server;

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
    
    // Example:
    // private final String _assetFolder = "/home/remy/Documents/" +
    //        "BA3/GénieLogiciel/Groupe01/assets/server/";
    private final String _assetFolder = "/Users/Nathan/Documents/BA3/INFOF307/Groupe01/assets/server/"; // TODO Must be change (GlassFish like only absolute path)
    private final String _dataBasePath = "Database.db";
    private final String _testDataBasePath = "TestDatabase.db";
    
    private ServerConfiguration() {}

    public static ServerConfiguration getInstance() {			
            if(_configuration == null) {
                _configuration = new ServerConfiguration();	
            }
            return _configuration;
    }
    
    private String getCompletePath(String relativePath) {
        return new File(relativePath).toURI().toString();
    }
    
    public String getDataBasePath() {
        return _dataBasePath;
    }
    
    public String getTestDataBasePath() {
        return _assetFolder + _testDataBasePath;
    }
    
}
