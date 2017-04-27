package be.ac.ulb.infof307.g01.server;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Respond to request of client with jersey
 * 
 * @author Groupe 1
 */
@Path("/query")
public class ServerQueryController {
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("marker/insert")
    public void insertMarker(MarkerSendableModel marker) {
        System.out.println("Réception InsertMarker !!");
        DatabaseModel.getDatabase().insertMarker(marker);
    }
    
}
