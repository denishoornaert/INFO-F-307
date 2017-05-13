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
    
    /** Configuration keys and default values.**/
    private final String BOOTSTRAP_DEFAULT = "bootstrap.css";
    private final String BOOTSTRAP_CONFIG = "bootstrap";
    private final String UNKNOWN_POKEMON_SPRITE_DEFAULT = "unknown_pokemon.png";
    private final String UNKNOWN_POKEMON_SPRITE_CONFIG = "unknown-pokemon";
    private final String SPRITE_FOLDER_DEFAULT = "sprites/";
    private final String SPRITE_FOLDER_CONFIG = "sprites";
    private final String TITLE_DEFAULT = "Gotta Map'Em All !";
    private final String TITLE_CONFIG = "title";
    private final String SERVER_URL_DEFAULT = "http://localhost:8080/server/rest/";
    private final String SERVER_URL_CONFIG = "server-url";
    private final String TERMS_AND_CONDITIONS_DEFAULT = "terms_and_conditions.txt";
    private final String TERMS_AND_CONDITIONS_CONFIG = "term-and-condition";
    private final String ICON_PATH_DEFAULT = "application_icon_";
    private final String ICON_PATH_DEFAULT_SUFFIX = ".png";
    private final String ICON_PATH_CONFIG = "icon";
    
    private ArrayList<String> _allResolution;
    
    private ClientConfiguration() {
        this(false);
    }
    
    private ClientConfiguration(boolean isTest) {
        super();
        _isTest = isTest;
        
        _allResolution = new ArrayList<>();
        _allResolution.add("16");
        _allResolution.add("32");
        _allResolution.add("64");
        _allResolution.add("128");
        _allResolution.add("256");
        _allResolution.add("512");
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
    
    /**
     * Get only the name of the style file
     * 
     * @return the style name file
     */
    public String getStyleFileName() {
        return _propertiesFile.getProperty(BOOTSTRAP_CONFIG, BOOTSTRAP_DEFAULT);
    }
    
    public String getStylePath() {
        return getAssetPath(getStyleFileName());
    }
    
    public String getUnknownPokemonSpritePath() {
        return getAssetPath(_propertiesFile.getProperty(UNKNOWN_POKEMON_SPRITE_CONFIG, 
                UNKNOWN_POKEMON_SPRITE_DEFAULT));
    }
    
    public String getSpritePath() {
        return getAssetPath(_propertiesFile.getProperty(SPRITE_FOLDER_CONFIG,
                SPRITE_FOLDER_DEFAULT));
    }
    
    public String getApplicationTitle() {
        return _propertiesFile.getProperty(TITLE_CONFIG, TITLE_DEFAULT);
    }
    
    public ArrayList<String> getApplicationIconsPaths() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < _allResolution.size(); ++i) {
            String number = _allResolution.get(i);
            
            result.add(getAssetPath(_propertiesFile.getProperty(ICON_PATH_CONFIG + i,
                ICON_PATH_DEFAULT + number + ICON_PATH_DEFAULT_SUFFIX), true));
        }
        
        return result;
    }
    
    public String getServerURL() {
        return _propertiesFile.getProperty(SERVER_URL_CONFIG, SERVER_URL_DEFAULT);
    }
    
    public String getTermsAndConditionsPath() {
        return addJarOrFilePrefix(getAssetPath(_propertiesFile.getProperty(
                TERMS_AND_CONDITIONS_CONFIG, TERMS_AND_CONDITIONS_DEFAULT)));
    }
    
    
    /////////////////// STATIC ///////////////////
    
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
}