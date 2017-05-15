package be.ac.ulb.infof307.g01.server.controller;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Handles pokemon-related client queries.
 * Network communications follow the RESTFul protocol, using the Jersey library.
 */
@Path("pokemon")
public class PokemonServiceQueyController {
    
    @Path("getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<PokemonSendableModel> getAllPokemons() {
        return DatabaseModel.getInstance().getAllPokemons();
    }
    
}
