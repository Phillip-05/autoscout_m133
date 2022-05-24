package dev.phill.autoscout.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Haendler;
import jakarta.ws.rs.*;
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
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(haendlerList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren des Haendlers")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readhHaendler(
            @PathParam("uuid") String haendlerUUID

    ){
        Haendler haendler = DataHandler.getInstance().readHaendlerByUUID(haendlerUUID);
        if (haendler == null) {
            return Response
                    .status(404)
                    .entity("Haendler nicht gefunden")
                    .build();
        }
        return Response
                .status(200)
                .entity(haendler)
                .build();
    }

}
