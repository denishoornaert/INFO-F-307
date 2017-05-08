package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.common.controller.ConnectionQueryController;
import be.ac.ulb.infof307.g01.common.controller.MarkerQueryController;
import be.ac.ulb.infof307.g01.common.controller.PokemonQueryController;
import be.ac.ulb.infof307.g01.common.controller.PokemonTypeQueryController;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;

/**
 * Connect to server and handles client queries
 */
public class ServerQueryController implements MarkerQueryController, PokemonQueryController, 
        PokemonTypeQueryController, ConnectionQueryController {
    
    /**
     * All post queries a visitor try to send are stored here.
     */
    private final Queue<PostQuery> _visitorPostQueriesQueue;
    
    /**
     * The class logger.
     */
    private static final Logger LOG = Logger.getLogger(ServerQueryController.class.getName());
    
    /**
     * Stores the web resource and the object used in a POST query.
     * We use a inner class because this class is only used to store a POST
     * request inside a queue (for now _visitorPostQueriesQueue). Since this
     * is an implementation detail, we don't put it in a dedicated file.
     */
    private class PostQuery {
        private final WebResource _webResource;
        private final Object _postObject;
        private final String _errorMessage;
        
        public PostQuery(WebResource webResource, Object postObject, String errorMessage) {
            _webResource = webResource;
            _postObject = postObject;
            _errorMessage = errorMessage;
        }

        public WebResource getWebResource() {
            return _webResource;
        }

        public Object getPostObject() {
            return _postObject;
        }

        public String getErrorMessage() {
            return _errorMessage;
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
    private void onUserLogin(String username) {
        for(PostQuery query : _visitorPostQueriesQueue) {
            // We have to check if the object to post is a marker added by a
            // visitor (the username is null in this case). If so, we have to
            // set the marker's username to the username of the freshly
            // connected user
            final Object objectToPost = query.getPostObject();
            // If we send a marker
            if(objectToPost instanceof MarkerSendableModel || objectToPost instanceof MarkerModel) {
                MarkerSendableModel markerToPost = (MarkerSendableModel) objectToPost;
                // If the marker was added by the visitor
                if(markerToPost.getUsername().isEmpty()) {
                    // Set the username of the marker
                    markerToPost.setUsername(username);
                }
            }
            try {
                trySendPostQuery(query, false);
            } catch(ServerException ex) {
            }
        }
    }
    
    @Override
    public void insertMarker(MarkerSendableModel marker) {
        marker = fixTypeMarkerModelToSendable(marker);
        WebResource resource = _webResource.path("marker").path("insert");
        try {
            trySendPostQuery(new PostQuery(resource, marker, "Could not insert marker"), true);
        } catch (ServerException exception) {
            
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
    public void updateMarkerReputation(ReputationVoteSendableModel reputationVote) {
        WebResource resource = _webResource.path("marker").path("updateReputation");
        
        try {
            trySendPostQuery(new PostQuery(resource, reputationVote, "Could not update marker"), true);
        } catch(ServerException exception) {
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
        marker = fixTypeMarkerModelToSendable(marker);
        WebResource resource = _webResource.path("marker").path("update");
        try {
            trySendPostQuery(new PostQuery(resource, marker, "Could not update marker"), true);
            return true;
        } catch(ServerException exception) {
            // Because of the signature of updateMarker, we have to fail silently
            // (however, a popup is displayed)
            return false;
        }
    }

    @Override
    public boolean signin(UserSendableModel user) {
        WebResource resource = _webResource.path("user").path("signin");
        
        try {
            trySendPostQuery(new PostQuery(resource, user, "Could not sign in"), false);
            onUserLogin(user.getUsername());
            return true;
        } catch(ServerException exception) {
            return false;
        }
    }

    @Override
    public boolean signup(UserSendableModel user) {
        WebResource resource = _webResource.path("user").path("signup");
        try {
            trySendPostQuery(new PostQuery(resource, user, "Could not sign up"), false);
            return true;
        } catch(ServerException exception) {
            return false;
        }
    }
    
    private ServerQueryController() {
        _visitorPostQueriesQueue = new LinkedList<>();
        connectClient();
        loadAllTables();
    }
    
    /**
     * Loads all Data (Pokemon and PokemonType).
     */
    private void loadAllTables() {
        PokemonCache.getInstance().loadAllPokemonTypes(getAllPokemonTypes());
        PokemonCache.getInstance().loadAllPokemons(getAllPokemons());
    }
    
    private void connectClient() {
        Client client = Client.create();
        _webResource = client.resource(ClientConfiguration.getInstance().getServerURL());
    }
    
    /**
     * Executes a POST HTTP request, if the user is connected, and stores it
     * if it is a visitor (unless the query is also valid for a visitor, such
     * as a connection query).
     * @param query A POST query object
     * @param bufferIfVisitor True if the query can be buffered if the user is
     * a visitor, false if we should always send the query
     * @throw ServerException if the request was not successful (refused by the
     * server, no network connection, ...)
     */
    private void trySendPostQuery(PostQuery query, boolean bufferIfVisitor) throws ServerException {
        // If we are connected, or if this is a query that should always be sent
        if(UserController.getInstance().isConnected() || !bufferIfVisitor) {
            // Send the query
            ClientResponse response = query.getWebResource().accept(MediaType.APPLICATION_XML)
                    .post(ClientResponse.class, query.getPostObject());
            
            Status status = response.getClientResponseStatus();
            if(status != Status.OK && status != Status.ACCEPTED) {
                try {
                    new MessagePopUpController(Level.WARNING, query.getErrorMessage());
                } catch (InstantiationException ex) {
                    LOG.log(Level.WARNING, "This error couldn't be shown with a popup: {0}",
                            query.getErrorMessage());
                }
                // Throw an exception to notify caller site that something happened
                throw new ServerException(status.getReasonPhrase());
            }
        } else {
            _visitorPostQueriesQueue.add(query);
        }
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
