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
    
    private final String _serverUrl;
    private final String _stylePath;
    private final String _unknownPokemonImagePath;
    private final String _spritesPath;
    private final String _termsAndConditionPath;
    private final String _applicationTitle;
    private final ArrayList<String> _applicationIconsPaths;
    
    private ClientConfiguration() {
        _termsAndConditionPath = "../../assets/client/terms_and_conditions.txt";
        _spritesPath = "../../assets/client/sprites/";
        _unknownPokemonImagePath = "assets/client/unknown_pokemon.png";
        _stylePath = "../../assets/client/bootstrap.css";
        _serverUrl = "http://localhost:8080/server/rest";
        _applicationTitle = "Gotta Map'Em All !";
        _applicationIconsPaths = new ArrayList<String>(Arrays.asList(
            "../../assets/client/icons/application_icon_16.png",
            "../../assets/client/icons/application_icon_32.png",
            "../../assets/client/icons/application_icon_64.png",
            "../../assets/client/icons/application_icon_128.png",
            "../../assets/client/icons/application_icon_256.png",
            "../../assets/client/icons/application_icon_512.png"
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
        return getCompletePath(_stylePath);
    }
    
    public String getUnknownPokemonImagePath() {
        return _unknownPokemonImagePath;
    }
    
    public String getSpritesPath() {
        return _spritesPath;
    }
    
    public String getApplicationTitle() {
        return _applicationTitle;
    }
    
    public ArrayList<String> getApplicationIconsPaths() {
        return _applicationIconsPaths;
    }
    
    public String getURL() {
        return _serverUrl;
    }
    
    public String getTermsAndContionsPath() {
        return _termsAndConditionPath;
    }
    
}