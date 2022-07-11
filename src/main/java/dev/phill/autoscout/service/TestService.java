package dev.phill.autoscout.service;


import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * test service
 */
@Path("test")
public class TestService {
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"user","admin"})
    public Response test() {

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }
}