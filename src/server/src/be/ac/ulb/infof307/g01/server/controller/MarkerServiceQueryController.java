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
 * Handles marker-related client queries.
 * Network communications follow the RESTFul protocol, using the Jersey library.
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
            Logger.getLogger(getClass().getName()).log(Level.INFO, 
                    "Exception while inserting Marker: {0}", ex.getMessage());
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
        Response response;
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Update marker: {0} - {1}", 
                new Object[]{marker.getPokemonName(), marker.getLongTimestamp()});
        try {
            DatabaseModel.getInstance().updateMarker(marker);
            response = Response.status(Response.Status.OK).entity(marker).build();
        } catch(InvalidParameterException exception) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, 
                    "Exception while updating Marker: {0}", exception.getMessage());
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }
    
    @Path("updateReputation")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateMarkerReputation(ReputationVoteSendableModel reputationVote) {
        Response response;
        try {
            DatabaseModel.getInstance().updateMarkerReputation(reputationVote);
            response = Response.status(Response.Status.OK).entity(reputationVote).build();
        } catch(InvalidParameterException exception) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, 
                    "Exception while updating Marker reputation: {0}", exception.getMessage());
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }
    
}
