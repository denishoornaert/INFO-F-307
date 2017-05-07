package be.ac.ulb.infof307.g01.server;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import be.ac.ulb.infof307.g01.server.controller.EmailSender;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
        Response response;
        Logger.getLogger(getClass().getName()).log(Level.INFO, 
                "Insert Marker: {0} - {1} - {2}", 
                new Object[]{marker.getPokemonName(), marker.getUsername(), 
                    marker.getAttack()});
        
        if(DatabaseModel.getInstance().insertMarker(marker)) {
            response = Response.status(Status.OK).entity(marker).build();
        } else {
            response = Response.status(Status.NOT_ACCEPTABLE).build();
        }
        return response;
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
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Update marker: {0} - {1}", 
                new Object[]{marker.getPokemonName(), marker.getLongTimestamp()});
        if(DatabaseModel.getInstance().updateMarker(marker)) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Marker update");
        } else {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Marker not update !");
        }
        return Response.status(Status.OK).entity(marker).build();
    }
    
    @Path("marker/updateReputation") // TODO check que ça marche
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateMarkerReputation(ReputationVoteSendableModel reputationVote) {
        DatabaseModel.getInstance().updateMarkerReputation(reputationVote);
        return Response.status(Status.OK).entity(reputationVote).build();
    }
    
    @Path("pokemon/getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<PokemonSendableModel> getAllPokemons() {
        return DatabaseModel.getInstance().getAllPokemons();
    }
    
    @Path("pokemontype/getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<PokemonTypeSendableModel> getAllPokemonType() {
        return DatabaseModel.getInstance().getAllPokemonTypes();
    }
    
    @Path("user/signin")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response userSignin(UserSendableModel user) {
        boolean successfullySignin = DatabaseModel.getInstance().signin(user);
        if(!successfullySignin) {
            return Response.status(Status.UNAUTHORIZED).build();
        }
        return Response.status(Status.OK).entity(user).build();
    }
    
    @Path("user/confirm")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_HTML)
    public String confirmAccount(@QueryParam("token") String token) {
        String htmlPage = "<html> " + "<title>" + "User Account Conrfirmation" + "</title><body>"
                + "<h1>" + "Validate Account" + "</h1>";
        boolean isValide = DatabaseModel.getInstance().confirmAccount(token);
        if(isValide) {
            htmlPage += "Your account has been validated";
        } else {
            htmlPage += "An error has occured";
        }
        return htmlPage + "</body>" + "</html> ";
    }
    
    @Path("user/signup")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response userSignup(UserSendableModel user) {
        String token = generateToken();
        boolean successfulySignup = DatabaseModel.getInstance().signup(user, token);
        if (!successfulySignup) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        try {
            EmailSender sender = new EmailSender();
            sender.sendConfirmationEmail(user.getEmail(), token);
        } catch (MessagingException ex) {
            Logger.getLogger(ServiceQueryController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.NOT_ACCEPTABLE).build();
        }
        return Response.status(Status.OK).build();
    }
    
    private String generateToken() {
        return UUID.randomUUID().toString();
    }
    
}
