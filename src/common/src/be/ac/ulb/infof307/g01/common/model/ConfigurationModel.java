package be.ac.ulb.infof307.g01.common.model;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Groupe 1
 */
public abstract class ConfigurationModel {
    
    private static final String FILE_PREFIX = "file:";
    private static final String JAR_PREFIX = "jar:";
    
    private static final String CONFIG_FILE = "config.properties";
    protected Properties _propertiesFile; 
    
    protected ConfigurationModel() {
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
    
    protected abstract String getAssetPath(String fileName);
    
    
    ////////////////// STATIC //////////////////
    
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
