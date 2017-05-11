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
    
    private static final String FILE_PREFIX = "file:";
    
    private Properties _propertiesFile;
    
    private final boolean _isTest;
    private ArrayList<String> _applicationIconsPaths =  new ArrayList<>();
    
    private ClientConfiguration() {
        this(false);
    }
    
    private ClientConfiguration(boolean isTest) {
        _isTest = isTest;
        
        loadConfigurationFile("config.properties");
        
    }
    
    private String getPath(String fileName) {
        return getPath(fileName, false);
    }
    
    private void loadConfigurationFile(String fileName) {
        String clientConfigFilePath = addJarOrFileBeforePath(getPath(fileName));
        try {
            URL path = new URL(clientConfigFilePath);
            _propertiesFile = new Properties();
            _propertiesFile.load(path.openStream());
            loadConfigurationInformations();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
    
    private void loadConfigurationInformations() {
        // TODO simplifier tout ça ?
        for (int i=0;i<6;i++) {
            _applicationIconsPaths.add(_propertiesFile.getProperty("Icon"+i));
        }
    }
    
    /**
     * Get the path to a specific file
     * 
     * @param fileName the file
     * @param forceAddFile True if we would like to have FILE_PREFIX before the path
     * @return The path
     */
    private String getPath(String fileName, boolean forceAddFile) {
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
        return getPath(getStyleFileName());
    }
    
    public String getUnknownPokemonSpritePath() {
        return getPath(_propertiesFile.getProperty("unknown-pokemon"));
    }
    
    public String getSpritePath() {
        return getPath(_propertiesFile.getProperty("sprites"));
    }
    
    public String getApplicationTitle() {
        return _propertiesFile.getProperty("Title");
    }
    
    public ArrayList<String> getApplicationIconsPaths() {
        ArrayList<String> result = new ArrayList<>();
        for(String icon : _applicationIconsPaths) {
            result.add(getPath(icon, true));
        }
        
        return result;
    }
    
    public String getServerURL() {
        return _propertiesFile.getProperty("server-url");
    }
    
    public String getTermsAndConditionsPath() {
        return addJarOrFileBeforePath(getPath(_propertiesFile.getProperty("term-and-condition")));
    }
    
    /**
     * Add FILE_PREFIX prefixe before file path if not exist
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
     * Add "jar:" prefix before imagePath if it's begin with FILE_PREFIX
     * 
     * @param imagePath the image path
     * @return the new path
     */
    public static String addJarBeforeImagePath(String imagePath) {
        if(imagePath.startsWith(FILE_PREFIX)) {
            imagePath = "jar:" + imagePath;
        }
        return imagePath;
    }
    
    /**
     * Add "jar:" prefix before imagePath witch begin with FILE_PREFIX
     * If not, prefix FILE_PREFIX is add
     * 
     * @param imagePath the image path
     * @return the new path
     */
    public static String addJarOrFileBeforePath(String imagePath) {
        if(imagePath.startsWith(FILE_PREFIX)) {
            imagePath = addJarBeforeImagePath(imagePath);
        } else {
            imagePath = addFilePrefix(imagePath);
        }
        return imagePath;
    }
    
}