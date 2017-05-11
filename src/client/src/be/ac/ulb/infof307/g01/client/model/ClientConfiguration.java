package be.ac.ulb.infof307.g01.client.model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class containing the magic numbers and strings required to 
 * configure the client program.
 *
 * @author Groupe01
 */
public class ClientConfiguration {
    
    private static ClientConfiguration _configuration = null;
    private static ClientConfiguration _testConfiguration = null;
    private final boolean _isTest;
    
    private static final String CONFIG_FILE = "config.properties";
    private static final String FILE_PREFIX = "file:";
    private static final String JAR_PREFIX = "jar:";
    
    private Properties _propertiesFile;
    
    private ClientConfiguration() {
        this(false);
    }
    
    private ClientConfiguration(boolean isTest) {
        _isTest = isTest;
        
        loadConfigurationFile(CONFIG_FILE);
        
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
    
    private String getAssetPath(String fileName) {
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