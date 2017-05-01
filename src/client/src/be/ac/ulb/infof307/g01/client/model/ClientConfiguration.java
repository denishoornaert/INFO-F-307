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
    
    private final String _serverUrl = "http://localhost:8080/server/rest";
    private final String _stylePath = "../../assets/client/bootstrap.css";
    private final String _unknownPokemonImagePath = "assets/client/unknown_pokemon.png";
    private final String _spritesPath = "../../assets/client/sprites/";
    private final String _applicationTitle = "Gotta Catch'em All !";
    private final ArrayList<String> _applicationIconsPaths = new ArrayList<String>(Arrays.asList(
        "../../assets/client/icons/application_icon_16.png",
        "../../assets/client/icons/application_icon_32.png",
        "../../assets/client/icons/application_icon_64.png",
        "../../assets/client/icons/application_icon_128.png",
        "../../assets/client/icons/application_icon_256.png",
        "../../assets/client/icons/application_icon_512.png"
    ));
    
    private ClientConfiguration() {}

    public static ClientConfiguration getInstance() {			
            if(_configuration == null) {
                _configuration = new ClientConfiguration();	
            }
            return _configuration;
    }
    
    private String getCompletePath(String relativePath) {
        return new File(relativePath).toURI().toString();
    }
    
    public String getDataBasePath() {
        return "../../assets/Database.db";
    }
    
    public String getTestDataBasePath() {
        return "../../assets/TestDatabase.db";
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
}