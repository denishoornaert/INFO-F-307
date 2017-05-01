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
    
    private static final String PATH_TO_ASSETS = "../../assets/client/";
    private static final String SERVER_URL = "http://localhost:8080/server/rest";
    private static final String STYLE_PATH = PATH_TO_ASSETS + "bootstrap.css";
    private static final String UNKNOWN_POKEMON_SPRITE_PATH = PATH_TO_ASSETS + "unknown_pokemon.png";
    private static final String SPRITES_PATH = PATH_TO_ASSETS + "sprites/";
    private static final String TERMS_AND_CONDITIONS_PATH = PATH_TO_ASSETS + "terms_and_conditions.txt";
    private static final String APPLICATION_TITLE = "Gotta Map'Em All !";
    private final ArrayList<String> _applicationIconsPaths; // TODO : vérifier si cet atribut peut être static aussi
    
    private ClientConfiguration() {
        _applicationIconsPaths = new ArrayList<>(Arrays.asList(
            PATH_TO_ASSETS + "icons/application_icon_16.png",
            PATH_TO_ASSETS + "icons/application_icon_32.png",
            PATH_TO_ASSETS + "icons/application_icon_64.png",
            PATH_TO_ASSETS + "icons/application_icon_128.png",
            PATH_TO_ASSETS + "icons/application_icon_256.png",
            PATH_TO_ASSETS + "icons/application_icon_512.png"
        ));
    }

    public static ClientConfiguration getInstance() {			
            if(_configuration == null) {
                _configuration = new ClientConfiguration();	
            }
            return _configuration;
    }
    
    private String getCompletePath(String relativePath) {
        return new File(relativePath).toURI().toString();
    }
    
    public String getStylePath() {
        return getCompletePath(STYLE_PATH);
    }
    
    public String getUnknownPokemonSpritePath() {
        return UNKNOWN_POKEMON_SPRITE_PATH;
    }
    
    public String getSpritePath() {
        return SPRITES_PATH;
    }
    
    public String getApplicationTitle() {
        return APPLICATION_TITLE;
    }
    
    public ArrayList<String> getApplicationIconsPaths() {
        return new ArrayList<>(); //_applicationIconsPaths;
    }
    
    public String getServerURL() {
        return SERVER_URL;
    }
    
    public String getTermsAndContionsPath() {
        return TERMS_AND_CONDITIONS_PATH;
    }
    
}