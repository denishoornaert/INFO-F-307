package be.ac.ulb.infof307.g01.server;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Respond to request of client with jersey
 * 
 * @author Groupe 1
 */
@Path("/query")
public class ServiceQueryController {
    
    @Path("marker/insert")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response insertMarker(MarkerSendableModel marker) {
        DatabaseModel.getInstance().insertMarker(marker);
        return Response.status(Status.OK).entity(marker).build();
    }
    
    @Path("marker/getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<MarkerSendableModel> getAllMarker() {
        ArrayList<MarkerSendableModel> arrayListMarker = DatabaseModel.getInstance().getAllMarkers();
        return arrayListMarker;
    }
    
    @Path("marker/update")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateMarker(MarkerSendableModel marker) {
        DatabaseModel.getInstance().updateMarker(marker);
        return Response.status(Status.OK).entity(marker).build();
    }
    
    @Path("marker/updateReputation")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateMarkerReputation(MarkerSendableModel marker) {
        DatabaseModel.getInstance().updateMarkerReputation(marker);
        return Response.status(Status.OK).entity(marker).build();
    }
    
    @Path("pokemon/getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<PokemonSendableModel> loadAllPokemons() {
        ArrayList<PokemonSendableModel> arrayListPokemon = PokemonSendableModel.getAllPokemon();
        if(arrayListPokemon.isEmpty()) {
            DatabaseModel.getInstance().loadAllPokemonTypes();
        }
        arrayListPokemon = PokemonSendableModel.getAllPokemon();
        return arrayListPokemon;
    }
    
    @Path("pokemontype/getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<PokemonTypeSendableModel> getAllPokemonType() {
        ArrayList<PokemonTypeSendableModel> allPokemonType = PokemonTypeSendableModel.getAllPokemonTypes();
        if(allPokemonType.isEmpty()) {
            DatabaseModel.getInstance().loadAllPokemonTypes();
        }
        allPokemonType = PokemonTypeSendableModel.getAllPokemonTypes();
        
        return allPokemonType;
    }
    
    @Path("user/signin")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response userSignin(UserSendableModel user) {
        boolean successfullySignin = DatabaseModel.getInstance().signin(user);
        return Response.status(Status.OK).entity(user).build();
    }
    
    @Path("user/signup")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response userSignup(UserSendableModel user) {
        boolean successfullySignup = DatabaseModel.getInstance().signup(user);
        if(successfullySignup) {
            // TODO send an email
        }
        return Response.status(Status.OK).entity(user).build();
    }
    
}
