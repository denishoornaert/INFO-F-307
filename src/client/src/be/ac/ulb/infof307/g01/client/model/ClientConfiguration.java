package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.ConfigurationModel;
import java.io.File;
import java.util.ArrayList;

/**
 * Singleton class containing the magic numbers and strings required to 
 * configure the client program.
 *
 * @author Groupe01
 */
public class ClientConfiguration extends ConfigurationModel {
    
    private static ClientConfiguration _configuration = null;
    private static ClientConfiguration _testConfiguration = null;
    private final boolean _isTest;
    
    private ClientConfiguration() {
        this(false);
    }
    
    private ClientConfiguration(boolean isTest) {
        super();
        _isTest = isTest;
    }
    
    @Override
    protected String getAssetPath(String fileName) {
        return getAssetPath(fileName, false);
    }
    
    /**
     * Gets the path to a specific asset file.
     * @param fileName the asset file's name
     * @param forceAddFile True if we would like to have FILE_PREFIX before the path
     * @return the asset file's absolute path
     */
    private String getAssetPath(String fileName, boolean forceAddFile) {
        String result;
        if(_isTest) {
            File file = new File("../../assets/client/");
            result = file.getAbsolutePath() + File.separatorChar + fileName;
        } else {
            result = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
            if(forceAddFile) {
                result = addFilePrefix(result);
            }
        }
        
        return result;
    }
    
    public static ClientConfiguration getInstance() {			
        if(_configuration == null) {
            _configuration = new ClientConfiguration();	
        }
        return _configuration;
    }
    
    public static ClientConfiguration getTestInstance() {
        if(_testConfiguration == null) {
            _testConfiguration = new ClientConfiguration(true);
        }
        return _testConfiguration;
    }
    
    /**
     * Get only the name of the style file
     * 
     * @return the style name file
     */
    public String getStyleFileName() {
        return _propertiesFile.getProperty("bootstrap");
    }
    
    public String getStylePath() {
        return getAssetPath(getStyleFileName());
    }
    
    public String getUnknownPokemonSpritePath() {
        return getAssetPath(_propertiesFile.getProperty("unknown-pokemon"));
    }
    
    public String getSpritePath() {
        return getAssetPath(_propertiesFile.getProperty("sprites"));
    }
    
    public String getApplicationTitle() {
        return _propertiesFile.getProperty("Title");
    }
    
    public ArrayList<String> getApplicationIconsPaths() {
        ArrayList<String> result = new ArrayList<>();
        for (int i=0;i<6;i++) {
            result.add(_propertiesFile.getProperty("Icon"+i));
        }
        
        return result;
    }
    
    public String getServerURL() {
        return _propertiesFile.getProperty("server-url");
    }
    
    public String getTermsAndConditionsPath() {
        return addJarOrFilePrefix(getAssetPath(_propertiesFile.getProperty("term-and-condition")));
    }
    
}