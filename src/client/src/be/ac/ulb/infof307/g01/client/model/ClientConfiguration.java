package be.ac.ulb.infof307.g01.client.model;

import java.io.File;

/**
 * Class used almost everywhere beacause this is the one who contains all the
 * magic numbers and magic string (relative paths, url, ...).
 * The class must be accesible from almst everywhere therefore we design it as a singleton.
 *
 * @author Groupe01
 */
public class ClientConfiguration {
    
    private static ClientConfiguration _configuration = null;
    
    private final String _serverUrl = "127.0.0.1";
    private final String _stylePath = "../../assets/bootstrap.css";
    private final String _unknownPokemonImagePath = "assets/unknown_pokemon.png";
    private final String _spritesPath = "../../assets/sprites/";
    
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
        return "../assets/Database.db";
    }
    
    public String getTestDataBasePath() {
        return "../assets/TestDatabase.db";
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
}