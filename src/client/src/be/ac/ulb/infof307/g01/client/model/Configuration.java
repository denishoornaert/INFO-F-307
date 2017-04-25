package be.ac.ulb.infof307.g01.client.model;

import java.io.File;

/**
 * Class used almost everywhere beacause this is the one who contains all the
 * magic numbers and magic string (relative paths, url, ...).
 * The class must be accesible from almst everywhere therefore we design it as a singleton.
 *
 */
public class Configuration {
    
    private static Configuration _configuration = null;
    
    private String _dataBasePath = "../../assets/Database.db";
    private String _testDataBasePath = "../../assets/Database.db";
    private String _stylePath = "../../assets/bootstrap.css";
    
    private Configuration() {}

    public static Configuration getInstance() {			
            if(_configuration == null) {
                _configuration = new Configuration();	
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
    
    public String getStylePath() {
        return getCompletePath(_stylePath);
    }
}