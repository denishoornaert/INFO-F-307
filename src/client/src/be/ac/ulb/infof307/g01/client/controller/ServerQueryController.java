package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.common.model.ConnectionQueryModel;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.MarkerQueryModel;
import be.ac.ulb.infof307.g01.common.model.PokemonQueryModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeQueryModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;

/**
 * Connect to server and handles client queries
 */
public class ServerQueryController implements MarkerQueryModel, PokemonQueryModel, 
        PokemonTypeQueryModel, ConnectionQueryModel {
    
    private WebResource _webResource;
    private static ServerQueryController _instance;
    
    private ServerQueryController() {
        connectClient();
        loadAllTables();
    }
    
    public static ServerQueryController getInstance() {
        if(_instance == null) {
            _instance = new ServerQueryController();
        }
        return _instance;
    }
    
    private void connectClient() {
        Client client = Client.create();
        _webResource = client.resource(ClientConfiguration.getInstance().getServerURL()).path("query");
    }
    
    /**
     * Executes a POST HTTP request
     * @param url the web resource's url
     * @param postObject the object to post
     * @return true if the request was accepted, false otherwise
     */
    private <T> boolean sendPostQuery(WebResource url, T postObject) {
        ClientResponse response = url.accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, postObject);
        
        switch (response.getClientResponseStatus()) {
            case OK:
            case ACCEPTED: 
                return true;
              
            default:
                Logger.getLogger(getClass().getName()).log(Level.WARNING, 
                    "Receive {0}", response.getClientResponseStatus().getReasonPhrase());
                break;
                
        }
        return false;
    }
    
    /**
     * Load all Data (Pokemon and PokemonType)
     */
    private void loadAllTables() {
        PokemonCache.getInstance().loadAllPokemonTypes(getAllPokemonTypes());
        PokemonCache.getInstance().loadAllPokemons(getAllPokemons());
    }
    
    @Override
    public boolean insertMarker(MarkerSendableModel marker) {
        marker = fixTypeMarkerModelToSendable(marker);
        boolean result = true;
        WebResource resource = _webResource.path("marker").path("insert");
        if (!sendPostQuery(resource, marker)) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not insert marker");
            result = false;
        }
        return result;
    }

    @Override
    public ArrayList<MarkerSendableModel> getAllMarkers() {
        WebResource resource = _webResource.path("marker").path("getall");
        List<MarkerSendableModel> result = resource.get(
                new GenericType<List<MarkerSendableModel>>(){});
        return new ArrayList<>(result);
    }

    @Override
    public void updateMarkerReputation(ReputationVoteSendableModel reputationVote) {
        WebResource resource = _webResource.path("marker").path("updateReputation");
        
        if (!sendPostQuery(resource, reputationVote)) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not update marker");
        }
    }

    @Override
    public List<PokemonSendableModel> getAllPokemons() {
        WebResource resource = _webResource.path("pokemon").path("getall");
        return resource.get(new GenericType<List<PokemonSendableModel>>(){});
    }

    @Override
    public List<PokemonTypeSendableModel> getAllPokemonTypes() {
        WebResource resource = _webResource.path("pokemontype").path("getall");
        return resource.get(new GenericType<List<PokemonTypeSendableModel>>(){});
    }
    
    @Override
    public boolean updateMarker(MarkerSendableModel marker) {
        boolean result = true;
        marker = fixTypeMarkerModelToSendable(marker);
        WebResource resource = _webResource.path("marker").path("update");
        
        if (!sendPostQuery(resource, marker)) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not update marker");
            result = false;
        }
        return result;
    }

    @Override
    public boolean signin(UserSendableModel user) {
        boolean result = true;
        WebResource resource = _webResource.path("user").path("signin");
        
        if (!sendPostQuery(resource, user)) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not sign in");
            result = false;
        }
        return result;
    }

    @Override
    public boolean signup(UserSendableModel user) {
        boolean result = true;
        WebResource resource = _webResource.path("user").path("signup");
        
        if (!sendPostQuery(resource, user)) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not sign up");
            result = false;
        }
        return result;
    }
    
    /**
     * MarkerModel could not be send with jersey. This function change Marker
     * to MarkerSendable
     * 
     * @param marker the maker
     * @return a real MarkerSendableModel
     */
    private MarkerSendableModel fixTypeMarkerModelToSendable(MarkerSendableModel marker) {
        if(marker instanceof MarkerModel) {
            marker = ((MarkerModel) marker).getSendable();
        }
        return marker;
    }
    
}
