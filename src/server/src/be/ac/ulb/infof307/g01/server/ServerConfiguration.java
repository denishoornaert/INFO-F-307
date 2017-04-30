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
    
    private final String _dataBasePath = "../../assets/Database.db";
    private final String _testDataBasePath = "../../assets/Database.db";
    
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
        return _testDataBasePath;
    }
    
}
