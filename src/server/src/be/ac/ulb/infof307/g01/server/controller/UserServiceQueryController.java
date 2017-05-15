package be.ac.ulb.infof307.g01.server.controller;

import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import java.security.InvalidParameterException;
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

/**
 *
 * @author Groupe 1
 */
@Path("user")
public class UserServiceQueryController {
    
    @Path("signin")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response userSignin(final UserSendableModel user) {
        try {
            DatabaseModel.getInstance().signin(user);
            return Response.status(Response.Status.OK).entity(user).build();
        } catch (InvalidParameterException exception) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    @Path("confirm")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_HTML)
    public String confirmAccount(@QueryParam("username") final String username,
            @QueryParam("token") final String token) {
        String htmlPage = "<html> " + "<title>" + "User Account Conrfirmation" + "</title><body>"
                + "<h1>" + "Validate Account" + "</h1>";
        try {
            DatabaseModel.getInstance().confirmAccount(username, token);
            htmlPage += "Your account has been validated";
        } catch(InvalidParameterException exception) {
            htmlPage += "An error has occured";
        }
        return htmlPage + "</body>" + "</html> ";
    }
    
    @Path("signup")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response userSignup(final UserSendableModel user) {
        try {
            DatabaseModel.getInstance().signup(user);
        } catch (InvalidParameterException exception) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        
        final String token = generateToken();
        try {
            final EmailSender sender = new EmailSender();
            sender.sendConfirmationEmail(user.getEmail(), user.getUsername(), token);
        } catch (MessagingException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        try {
            DatabaseModel.getInstance().addTokenToUser(user, token);
        } catch(InvalidParameterException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        
        return Response.status(Response.Status.OK).build();
    }
    
    private String generateToken() {
        return UUID.randomUUID().toString();
    }
    
}
