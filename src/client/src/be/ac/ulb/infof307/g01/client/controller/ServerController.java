package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.common.PokemonType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.StringReader;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class ServerController {
    
    public static void connectClient() {
        Client client = Client.create();
        WebResource webResource = client
		   .resource("http://localhost:8080/server/rest").path("pokemontype");
        
        String response = webResource.accept(MediaType.APPLICATION_XML)
                .get(String.class);
        PokemonType pokemonResponse = jaxbXMLToPokemonType(response);
        System.out.println("Reponse: " + pokemonResponse.getName() + " - " + 
                pokemonResponse.getId());
        
    }
    
    private static PokemonType jaxbXMLToPokemonType(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(PokemonType.class);
            Unmarshaller un = context.createUnmarshaller();
            StringReader stringXml = new StringReader(xml);
            PokemonType emp = (PokemonType) un.unmarshal(stringXml);
            return emp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
