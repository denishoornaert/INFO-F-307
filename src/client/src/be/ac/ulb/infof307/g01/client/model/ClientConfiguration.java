package be.ac.ulb.infof307.g01.client.model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used almost everywhere because this is the one who contains all the
 * magic numbers and magic string (relative paths, url, ...).
 * The class must be accessible from almost everywhere therefore we designed it as a singleton.
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
    
    // TODO See for Refactor David
    private ClientConfiguration(boolean isTest) {
        _isTest = isTest;
        
        String clientConfigFilePath = addJarOrFileBeforePath(getPath("config.properties"));
        System.out.println("Config path: " + clientConfigFilePath);
        try {
            URL path = new URL(clientConfigFilePath);
            loadConfigurationFile(path);
        } catch (IOException ex) {
            Logger.getLogger(ClientConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getPath(String fileName) {
        return getPath(fileName, false);
    }
    
    private void loadConfigurationFile(URL path) throws IOException {
        _propertiesFile = new Properties();
        _propertiesFile.load(path.openStream());
        
        // TODO simplifier tout ça ?
        for (int i=0;i<6;i++) {
            _applicationIconsPaths.add(_propertiesFile.getProperty("Icon"+i));
            System.out.println("Application Icon :" + _applicationIconsPaths);
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
        String file = _propertiesFile.getProperty("bootstrap");
        System.out.println("Get bootstrap: " + file);
        return file;
    }
    
    public String getStylePath() {
        String file = getPath(getStyleFileName());
        System.out.println("Get bootstrap path: " + file);
        return file;
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