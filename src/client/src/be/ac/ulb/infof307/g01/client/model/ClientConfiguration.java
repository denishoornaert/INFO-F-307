package be.ac.ulb.infof307.g01.client.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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
    private static final String SERVER_URL = "http://localhost:8080/server/rest/";
    private static final String STYLE_PATH = "bootstrap.css";
    private static final String UNKNOWN_POKEMON_SPRITE_PATH = "unknown_pokemon.png";
    private static final String SPRITES_PATH = "sprites/";
    private static final String TERMS_AND_CONDITIONS_PATH = "terms_and_conditions.txt";
    private static final String APPLICATION_TITLE = "Gotta Map'Em All !";
    
    private final boolean _isTest;
    private static final ArrayList<String> _applicationIconsPaths = new ArrayList<>(Arrays.asList(
            "icons/application_icon_16.png", "icons/application_icon_32.png",
            "icons/application_icon_64.png", "icons/application_icon_128.png",
            "icons/application_icon_256.png", "icons/application_icon_512.png"));
    
    private ClientConfiguration() {
        this(false);
    }
    
    private ClientConfiguration(boolean isTest) {
        _isTest = isTest;
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
    
    public String getTermsAndContionsPath() {
        return getPath(TERMS_AND_CONDITIONS_PATH);
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