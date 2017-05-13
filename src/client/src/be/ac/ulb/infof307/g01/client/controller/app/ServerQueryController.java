package be.ac.ulb.infof307.g01.client.controller.app;

import be.ac.ulb.infof307.g01.client.model.PokemonCache;
import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.common.controller.ConnectionQueryController;
import be.ac.ulb.infof307.g01.common.controller.FilterQueryController;
import be.ac.ulb.infof307.g01.common.controller.MarkerQueryController;
import be.ac.ulb.infof307.g01.common.controller.PokemonQueryController;
import be.ac.ulb.infof307.g01.common.controller.PokemonTypeQueryController;
import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.ws.rs.core.MediaType;

/**
 * Connects to the server and handles the client's queries.
 * This class implements the various interfaces in common.controller. If an
 * error occurs in a request, this class shows a popup explaining the error, or
 * throws an InvalidParameterException, depending on the nature of the query.
 */
public class ServerQueryController implements MarkerQueryController, PokemonQueryController, 
        PokemonTypeQueryController, ConnectionQueryController, FilterQueryController {
    
    private WebResource _webResource;
    private static ServerQueryController _instance;
    
    /** All POST queries a visitor tries to send are stored here. */
    private final Queue<PostQuery> _visitorPostQueriesQueue;
    
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
    
    public static ServerQueryController getInstance() {
        if(_instance == null) {
            _instance = new ServerQueryController();
        }
        return _instance;
    }
    
    private ServerQueryController() {
        _visitorPostQueriesQueue = new LinkedList<>();
        connectClient();
        loadCache();
    }
    
    /**
     * Loads all of the frequently used data into a cache.
     */
    private void loadCache() {
        PokemonCache.getInstance().loadAllPokemonTypes(getAllPokemonTypes());
        PokemonCache.getInstance().loadAllPokemons(getAllPokemons());
    }
    
    /**
     * Creates the Jersey web client and its required web resources.
     */
    private void connectClient() {
        Client client = Client.create();
        _webResource = client.resource(ClientConfiguration.getInstance().getServerURL());
    }
    
    /**
     * Sends a POST HTTP request to the server.
     * If bufferIfVisitor is true and the user is a visitor, stores the query
     * instead.
     * @param query the POST query to send
     * @param bufferIfVisitor true if the query should be stored if the user
     * is a visitor, false otherwise
     * @throws InvalidParameterException if the query was not successful 
     * (refused by the server, no Internet connection...)
     */
    private void sendPostQuery(PostQuery query, boolean bufferIfVisitor) throws InvalidParameterException {
        sendPostQuery(query, bufferIfVisitor, void.class);
    }
    
    /**
     * Sends a POST HTTP request to the server.
     * If bufferIfVisitor is true and the user is a visitor, stores the query
     * instead.
     * @param query A POST query object
     * @param bufferIfVisitor true if the query should be stored if the user
     * is a visitor, false otherwise
     * @param classResult the expected result of this request
     * @throw InvalidParameterException if the request was not successful
     * (refused by the server, no network connection, ...).
     */
    private <T> T sendPostQuery(PostQuery query, boolean bufferIfVisitor, Class<T> classResult) 
            throws InvalidParameterException {
        T result = null;
        // If we are connected, or if this is a query that should always be sent
        if(UserController.getInstance().isConnected() || !bufferIfVisitor) {
            // Send the query
            ClientResponse response = query.getWebResource().accept(MediaType.APPLICATION_XML)
                    .post(ClientResponse.class, query.getPostObject());
            if(classResult != void.class) {
                try {
                    result = response.getEntity(classResult);
                } catch(ClientHandlerException ex) {
                    // No message
                }
            }
            // Check the return status
            Status status = response.getClientResponseStatus();
            if(! statusIsPositive(status)) {
                throw new InvalidParameterException(query.getErrorMessage());
            }
        } else {
            _visitorPostQueriesQueue.add(query);
        }
        return result;
    }
    
    /**
     * Checks if an HTTP response status is positive.
     * @param status the HTTP response status to check
     * @return true if the status is "OK" or "ACCEPTED", false otherwise
     */
    private boolean statusIsPositive(Status status) {
        return status == Status.OK || status == Status.ACCEPTED;
    }
    
    /**
     * Changes a MarkerSendableModel that's not really a MarkerSendableModel
     * so that it really is a MarkerSendableModel.
     * @note required by Jersey
     * @param marker the fake MarkerSendableModel
     * @return a real MarkerSendableModel
     */
    private MarkerSendableModel fixTypeMarkerModelToSendable(MarkerSendableModel marker) {
        if(marker instanceof MarkerModel) {
            marker = ((MarkerModel) marker).getSendable();
        }
        return marker;
    }
    
    /**
     * Sends all the POST requests stored while the user was a visitor.
     * Call this function when the user logs in.
     * @param username the username of the logged-in user
     * @throws InvalidParameterException if any of the requests fail
     */
    private void onUserLogin(String username) throws InvalidParameterException {
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
            sendPostQuery(query, false);
        }
        _visitorPostQueriesQueue.clear();
    }
    
    @Override
    public void insertMarker(MarkerSendableModel marker) throws InvalidParameterException {
        marker = fixTypeMarkerModelToSendable(marker);
        WebResource resource = _webResource.path("marker").path("insert");
        sendPostQuery(new PostQuery(resource, marker, "Could not insert marker"), true);
    }

    @Override
    public ArrayList<MarkerSendableModel> getAllMarkers() {
        WebResource resource = _webResource.path("marker").path("getall");
        List<MarkerSendableModel> result = resource.get(
                new GenericType<List<MarkerSendableModel>>(){});
        return new ArrayList<>(result);
    }

    @Override
    public void updateMarkerReputation(ReputationVoteSendableModel reputationVote)
            throws InvalidParameterException {
        WebResource resource = _webResource.path("marker").path("updateReputation");
        sendPostQuery(new PostQuery(resource, reputationVote, "Could not update marker"), true);
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
    public void updateMarker(MarkerSendableModel marker) throws InvalidParameterException {
        marker = fixTypeMarkerModelToSendable(marker);
        WebResource resource = _webResource.path("marker").path("update");
        sendPostQuery(new PostQuery(resource, marker, "Could not update marker"), true);
    }

    @Override
    public void signin(UserSendableModel user) throws InvalidParameterException {
        WebResource resource = _webResource.path("user").path("signin");
        
        UserSendableModel result = sendPostQuery(
                new PostQuery(resource, user, "Could not sign in"), false,
                UserSendableModel.class);
        if(result != null) {
            user.setEmail(result.getEmail());
        }
        onUserLogin(user.getUsername());
    }

    @Override
    public void signup(UserSendableModel user) throws InvalidParameterException {
        WebResource resource = _webResource.path("user").path("signup");
        sendPostQuery(new PostQuery(resource, user, "Could not sign up"), false);
    }
    
    @Override
    public List<FilterSendableModel> getAllFilter() {
        WebResource resource = _webResource.path("filter").path("getall");
        return resource.get(new GenericType<List<FilterSendableModel>>(){});
    }

    @Override
    public void insertFilter(FilterSendableModel filter) throws InvalidParameterException {
        WebResource resource = _webResource.path("filter").path("insert");
        sendPostQuery(new PostQuery(resource, filter, 
            "Could not insert this filter"), true);
    }
}