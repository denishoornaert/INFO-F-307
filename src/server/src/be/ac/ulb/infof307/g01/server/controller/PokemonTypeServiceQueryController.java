package be.ac.ulb.infof307.g01.server.controller;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Groupe 1
 */
@Path("pokemontype")
public class PokemonTypeServiceQueryController {
    
    @Path("getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<PokemonTypeSendableModel> getAllPokemonType() {
        return DatabaseModel.getInstance().getAllPokemonTypes();
    }
}
