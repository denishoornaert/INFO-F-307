package be.ac.ulb.infof307.g01.server;

import java.io.File;
import java.nio.file.FileSystems;

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
    private final String _assetFolder = FileSystems.getDefault().getPath("../../assets/server/").toAbsolutePath().normalize().toString() + "/";
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
        return _assetFolder + _dataBasePath;
    }
    
    public String getTestDataBasePath() {
        return _assetFolder + _testDataBasePath;
    }
    
}
