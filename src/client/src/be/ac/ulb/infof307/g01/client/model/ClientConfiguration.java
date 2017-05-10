package be.ac.ulb.infof307.g01.client.model;

import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
    private String clientConfigurationFilePath;
    private final Properties properties = new Properties();
    private static final String FILE_PREFIX = "file:";
    private String SERVER_URL;
    private String STYLE_PATH;
    private String UNKNOWN_POKEMON_SPRITE_PATH;
    private String SPRITES_PATH;
    private String TERMS_AND_CONDITIONS_PATH;
    private String APPLICATION_TITLE;
    
    private final boolean _isTest;
    private ArrayList<String> _applicationIconsPaths =  new ArrayList<>();
    
    private ClientConfiguration() {
        this(false);
    }
    
    // TODO See for Refactor David
    private ClientConfiguration(boolean isTest) {
        _isTest = isTest;
        clientConfigurationFilePath = getPath("config.properties");
        clientConfigurationFilePath = addJarOrFileBeforeImagePath(clientConfigurationFilePath);
        try {
            URL path = new URL(clientConfigurationFilePath);
            properties.load(path.openStream());
            SERVER_URL = properties.getProperty("server-url");
            STYLE_PATH = properties.getProperty("bootstrap");
            UNKNOWN_POKEMON_SPRITE_PATH = properties.getProperty("unknown-pokemon");
            SPRITES_PATH = properties.getProperty("sprites");
            System.out.println("Sprite string : "+SPRITES_PATH +"|path = "+getPath(SPRITES_PATH) );
            TERMS_AND_CONDITIONS_PATH = properties.getProperty("term-and-condition");
            APPLICATION_TITLE = properties.getProperty("Title");
            for (int i=0;i<6;i++) {
                _applicationIconsPaths.add(properties.getProperty("Icon"+i));
                System.out.println("Application Icon :" + _applicationIconsPaths);
            }
            
            
        } catch (IOException exeption) {
            Logger.getLogger(ClientConfiguration.class.getName()).log(Level.SEVERE, null, exeption);
        }
    }
    
    private String getPath(String fileName) {
        return getPath(fileName, false);
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
        return STYLE_PATH;
    }
    
    public String getStylePath() {
        return getPath(STYLE_PATH);
    }
    
    public String getUnknownPokemonSpritePath() {
        return getPath(UNKNOWN_POKEMON_SPRITE_PATH);
    }
    
    public String getSpritePath() {
        return getPath(SPRITES_PATH);
    }
    
    public String getApplicationTitle() {
        return APPLICATION_TITLE;
    }
    
    public ArrayList<String> getApplicationIconsPaths() {
        ArrayList<String> result = new ArrayList<>();
        for(String icon : _applicationIconsPaths) {
            result.add(getPath(icon, true));
        }
        
        return result;
    }
    
    public String getServerURL() {
        return SERVER_URL;
    }
    
    public String getTermsAndConditionsPath() {
        return addJarOrFileBeforeImagePath(getPath(TERMS_AND_CONDITIONS_PATH));
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
    public static String addJarOrFileBeforeImagePath(String imagePath) {
        if(imagePath.startsWith(FILE_PREFIX)) {
            imagePath = addJarBeforeImagePath(imagePath);
        } else {
            imagePath = addFilePrefix(imagePath);
        }
        return imagePath;
    }
    
}