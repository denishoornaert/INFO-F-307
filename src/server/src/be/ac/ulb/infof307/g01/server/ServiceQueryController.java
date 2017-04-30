package be.ac.ulb.infof307.g01.server;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
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
    public Response getAllMarker() {
        ArrayList<MarkerSendableModel> arrayListMarker = DatabaseModel.getInstance().getAllMarkers();
        GenericEntity<ArrayList<MarkerSendableModel>> entity = 
                new GenericEntity<ArrayList<MarkerSendableModel>>(arrayListMarker) {};
        Response response = Response.ok(entity).build();
        
        return response;
    }
    
    @Path("marker/update")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateMarker(MarkerSendableModel marker) {
        DatabaseModel.getInstance().updateMarker(marker);
        return Response.status(Status.OK).entity(marker).build();
    }
    
}
