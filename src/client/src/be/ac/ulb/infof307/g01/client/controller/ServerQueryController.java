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
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;

/**
 * Connect to server and handles client queries
 */
public class ServerQueryController implements MarkerQueryModel, PokemonQueryModel, 
        PokemonTypeQueryModel, ConnectionQueryModel {
    
    /**
     * All post queries a visitor try to send are stored here.
     */
    private final Queue<PostQuery> _visitorPostQueriesQueue;
    
    /**
     * All post queries a user try to send when the application is not
     * connected to the network are stored here.
     */
    private final Queue<PostQuery> _erroredPostQueriesQueue;
    
    /**
     * Stores the web resource and the object used in a POST query.
     */
    private class PostQuery {
        private final WebResource _webResource;
        private final Object _postObject;
        
        public PostQuery(WebResource webResource, Object postObject) {
            _webResource = webResource;
            _postObject = postObject;
        }

        public WebResource getWebResource() {
            return _webResource;
        }

        public Object getPostObject() {
            return _postObject;
        }
    }
    
    
    private WebResource _webResource;
    private static ServerQueryController _instance;
    
    public static ServerQueryController getInstance() {
        if(_instance == null) {
            _instance = new ServerQueryController();
        }
        return _instance;
    }
    
    /**
     * Call this function when the user logs in. It tries to send all
     * bufferised POST queries.
     */
    public void onUserLogin() {
        tryToSendPostQueries(_visitorPostQueriesQueue);
    }
    
    /**
     * Call this function when the network is available back. It tries to send
     * all bufferised POST queries.
     */
    public void onNetworkRecovery() {
        tryToSendPostQueries(_erroredPostQueriesQueue);
    }
    
    @Override
    public void insertMarker(MarkerSendableModel marker) {
        marker = fixTypeMarkerModelToSendable(marker);
        WebResource resource = _webResource.path("marker").path("insert");
        if (!sendPostQuery(new PostQuery(resource, marker))) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not insert marker");
        }
    }

    @Override
    public ArrayList<MarkerSendableModel> getAllMarkers() {
        WebResource resource = _webResource.path("marker").path("getall");
        List<MarkerSendableModel> result = resource.get(
                new GenericType<List<MarkerSendableModel>>(){});
        return new ArrayList<>(result);
    }

    @Override
    public void updateMarkerReputation(MarkerSendableModel marker) {
        marker = fixTypeMarkerModelToSendable(marker);
        WebResource resource = _webResource.path("marker").path("updateReputation");
        
        if(!sendPostQuery(new PostQuery(resource, marker))) {
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
        
        if(!sendPostQuery(new PostQuery(resource, marker))) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not update marker");
            result = false;
        }
        return result;
    }

    @Override
    public boolean signin(UserSendableModel user) {
        boolean result = true;
        WebResource resource = _webResource.path("user").path("signin");
        
        if (!sendPostQuery(new PostQuery(resource, user))) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not sign in");
            result = false;
        }
        return result;
    }

    @Override
    public boolean signup(UserSendableModel user) {
        boolean result = true;
        WebResource resource = _webResource.path("user").path("signup");
        
        if (!sendPostQuery(new PostQuery(resource, user))) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not sign up");
            result = false;
        }
        return result;
    }
    
    private ServerQueryController() {
        _visitorPostQueriesQueue = new LinkedList();
        _erroredPostQueriesQueue = new LinkedList();
        connectClient();
        loadAllTables();
    }
    
    private void tryToSendPostQueries(Queue<PostQuery> queries) {
        for(PostQuery query : queries) {
            sendPostQuery(query);
        }
    }
    
    /**
     * Loads all Data (Pokemon and PokemonType).
     */
    private void loadAllTables() {
        List<PokemonTypeSendableModel> types = getAllPokemonTypes();
        System.out.println("LOADING " + Arrays.toString(types.toArray()));
        PokemonCache.getInstance().loadAllPokemonTypes(types);
        PokemonCache.getInstance().loadAllPokemons(getAllPokemons());
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
    private <T> boolean sendPostQuery(PostQuery query) {
        ClientResponse response = query.getWebResource().accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, query.getPostObject());
        
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
