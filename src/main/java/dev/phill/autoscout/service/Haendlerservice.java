package dev.phill.autoscout.service;


import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Haendler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * test service
 */
@Path("haendler")
public class Haendlerservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHaendlers() {
        List<Haendler> haendlerList = DataHandler.getInstance().readAllHaendlers();
        return Response
                .status(200)
                .entity(haendlerList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readhHaendler(
            @QueryParam("uuid") String haendlerUUID

    ){
        Haendler haendler = DataHandler.getInstance().readHaendlerByUUID(haendlerUUID);
        return Response
                .status(200)
                .entity(haendler)
                .build();
    }

}
