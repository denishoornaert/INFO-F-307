package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.MarkerQueryModel;
import be.ac.ulb.infof307.g01.common.model.PokemonQueryModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeQueryModel;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Connect to server and handles client queries
 */
public class ServerQueryController implements MarkerQueryModel, PokemonQueryModel, PokemonTypeQueryModel {
    
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
        _webResource = client.resource(ClientConfiguration.getInstance().getURL()).path("query");
    }
    
    private static<T> T convertXmlToObject(String xml, Class<T> className) {
        try {
            JAXBContext context = JAXBContext.newInstance(className);
            Unmarshaller un = context.createUnmarshaller();
            StringReader stringXml = new StringReader(xml);
            T res = (T) un.unmarshal(stringXml);
            return res;
        } catch (JAXBException e) {
            // TODO catch error
        }
        return null;
    }

    /**
     * Executes a GET HTTP request
     * @param <T> the type of the object to get
     * @param url the web resource's url
     * @param className the class name of the expected object
     * @return the expected object
     */
    private <T> T sendGetQuery(WebResource url, Class<T> className) {
        String response = url.accept(MediaType.APPLICATION_XML).get(String.class);
        return convertXmlToObject(response, className);
    }
    
    /**
     * Executes a POST HTTP request
     * @param url the web resource's url
     * @param postObject the object to post
     * @return true if the request was accepted, false otherwise
     */
    private boolean sendPostQuery(WebResource url, Object postObject) {
        ClientResponse response = url.accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, postObject);
        switch (response.getClientResponseStatus()) {
            case OK:
            case ACCEPTED: 
                return true;
        }
        return false;
    }
    
    /**
     * Load all Data (Pokemon and PokemonType)
     */
    private void loadAllTables() {
        loadAllPokemonTypes();
        loadAllPokemons();
    }
    
    @Override
    public boolean insertMarker(MarkerSendableModel marker) {
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
    public void updateMarkerReputation(MarkerSendableModel marker) {
        WebResource resource = _webResource.path("marker").path("updateReputation");
        
        if (!sendPostQuery(resource, marker)) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not update marker");
        }
    }

    @Override
    public void loadAllPokemons() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadAllPokemonTypes() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateMarker(MarkerSendableModel marker) {
        boolean result = true;
        WebResource resource = _webResource.path("marker").path("update");
        
        if (!sendPostQuery(resource, marker)) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not update marker");
            result = false;
        }
        return result;
    }
    
}
