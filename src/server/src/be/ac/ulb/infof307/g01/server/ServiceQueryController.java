package be.ac.ulb.infof307.g01.server;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
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
    
    @POST
    @Path("marker/insert")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response insertMarker(MarkerSendableModel marker) {
        System.out.println("Réception InsertMarker !!");
        System.out.println("Marker: " + marker.getDatabaseId());
//        DatabaseModel.getDatabase().insertMarker(marker);
        
        return Response.status(Status.OK).entity(marker).build();
    }
    
    @POST
    @Path("marker/getall")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList getAllMarker() {
        return new ArrayList<MarkerSendableModel>();
    }
    
}
