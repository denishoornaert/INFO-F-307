package be.ac.ulb.infof307.g01.server.controller;

import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import java.security.InvalidParameterException;
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
@Path("filter")
public class FilterServiceQueryController {
    
    
    @Path("insert")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response insertFilter(FilterSendableModel filter) {
        Response response;
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Insert filter: {0}", 
                filter.getName());
        try {
            DatabaseModel.getInstance().insertFilter(filter);
            response = Response.status(Response.Status.OK).entity(filter).build();
        } catch(InvalidParameterException ex) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, 
                    "Exception while inserting filter: {0}", ex.getMessage());
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }
    
    @Path("getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<FilterSendableModel> getAllMarker() {
        List<FilterSendableModel> arrayListFilter = DatabaseModel.getInstance().getAllFilter();
        return arrayListFilter;
    }
    
}
