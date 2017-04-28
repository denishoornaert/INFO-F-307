package be.ac.ulb.infof307.g01.server;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Respond to request of client with jersey
 * 
 * @author Groupe 1
 */
@Path("/query")
public class ServiceQueryController {
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("marker/insert")
    public Response insertMarker(MarkerSendableModel marker) {
        System.out.println("Réception InsertMarker !!");
        DatabaseModel.getDatabase().insertMarker(marker);
        
        return Response.status(201).entity(marker).build();
    }
    
}
