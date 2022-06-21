package dev.phill.autoscout.service;


import dev.phill.autoscout.data.DataHandler;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.codec.digest.DigestUtils;

@Path("user")
public class Userservice {

    /**
     *
     * @param username the username of the user who wants to login
     * @param password the password of the user who want to login
     * @return if the login was successful
     */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);
        String user = DataHandler.getInstance().readUserRole(username,hashedPassword);
        return Response
                .status(200)
                .entity(user)
                .build();
    }
}
