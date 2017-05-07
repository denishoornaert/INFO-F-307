package be.ac.ulb.infof307.g01.server.controller;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Groupe 1
 */
@Path("marker")
public class MarkerServiceQueryController {
    
    @Path("insert")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response insertMarker(MarkerSendableModel marker) {
        Response response;
        Logger.getLogger(getClass().getName()).log(Level.INFO, 
                "Insert Marker: {0} - {1} - {2}", 
                new Object[]{marker.getPokemonName(), marker.getUsername(), 
                    marker.getAttack()});
        try {
            DatabaseModel.getInstance().insertMarker(marker);
            response = Response.status(Response.Status.OK).entity(marker).build();
        } catch(InvalidParameterException ex) {
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }
    
    @Path("getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<MarkerSendableModel> getAllMarker() {
        ArrayList<MarkerSendableModel> arrayListMarker = DatabaseModel.getInstance().getAllMarkers();
        return arrayListMarker;
    }
    
    @Path("update")
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
        return Response.status(Response.Status.OK).entity(marker).build();
    }
    
    @Path("updateReputation") // TODO check que ça marche
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateMarkerReputation(ReputationVoteSendableModel reputationVote) {
        DatabaseModel.getInstance().updateMarkerReputation(reputationVote);
        return Response.status(Response.Status.OK).entity(reputationVote).build();
    }
    
}
