package dev.phill.autoscout.service;

import dev.phill.autoscout.CreateJSON;
import dev.phill.autoscout.data.DataHandler;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

@Path("reset")
public class Resetservice {
    @Path("resetall")
    @PermitAll
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response resetAll() {


        CreateJSON.main(null);
        DataHandler.resetAllLists();

        return Response
                .status(200)
                .entity("reset everything")
                .build();

    }

}
