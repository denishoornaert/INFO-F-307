package be.ac.ulb.infof307.g01.server;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * TODO description
 * @author Groupe 1
 */
@Path("/pokemontype")
@Deprecated
public class PokemonTypeService {
    
    
    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Jersey 0";
    }
    
    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey 1" + "</hello>";
    }
    
    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello Jerseyyyy" + "</title>"
                + "<body><h1>" + "Hello Jersey 2" + "</h1></body>" + "</html> ";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public PokemonTypeSendableModel getAllTypes() {
        PokemonTypeSendableModel pokemonType = new PokemonTypeSendableModel();
        // TODO
        return pokemonType;
    }
    
}
