package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.common.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.MarkerQueryModel;
import be.ac.ulb.infof307.g01.common.PokemonQueryModel;
import be.ac.ulb.infof307.g01.common.PokemonTypeQueryModel;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.StringReader;
import java.util.ArrayList;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class ServerQueryController implements MarkerQueryModel, PokemonQueryModel, PokemonTypeQueryModel {
    
    private WebResource _webResource;
    private static ServerQueryController _instance;
    
    private ServerQueryController() {
        connectClient();
    }
    
    public static ServerQueryController getInstance() {
        if(_instance == null) {
            _instance = new ServerQueryController();
        }
        return _instance;
    }
    
    private void connectClient() {
        Client client = Client.create();
        _webResource = client.resource("http://localhost:8080/server/rest");
        
        //String response = webResource.accept(MediaType.APPLICATION_XML)
        //        .get(String.class);
        //PokemonTypeModel pokemonResponse = convertXmlToObject(response, PokemonTypeSendableModel.class);        
    }
    
    private static<T> T convertXmlToObject(String xml, Class<T> className) {
        try {
            JAXBContext context = JAXBContext.newInstance(className);
            Unmarshaller un = context.createUnmarshaller();
            StringReader stringXml = new StringReader(xml);
            T emp = (T) un.unmarshal(stringXml);
            return emp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T sendQuery(WebResource url, Class<T> className) {
        String response = url.accept(MediaType.APPLICATION_XML).get(String.class);
        return convertXmlToObject(response, className);
    }
    
    @Override
    public void insertMarker(MarkerSendableModel marker) {
        WebResource queryPath = _webResource.path("marker").path("insert");
        queryPath.post(Entity.entity(marker, MediaType.APPLICATION_XML));
    }

    @Override
    public ArrayList<MarkerSendableModel> getAllMarkers() {
        // TODO craete a test to check all elements are an instance of MarkerModel
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMarkerReputation(MarkerSendableModel marker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadAllPokemons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadAllPokemonTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
