package dev.phill.autoscout.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Kaeufer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * test service
 */
@Path("kaeufer")
public class Kaeuferservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKaeufer() {
        List<Kaeufer> kaeuferList = DataHandler.getInstance().readallKaeufer();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(kaeuferList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren des Kaeufers")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKaeufer(
            @PathParam("uuid") String kaeuferUUID

    ){
        Kaeufer kaeufer = DataHandler.getInstance().readKaeuferByUUID(kaeuferUUID);
        if (kaeufer == null) {
            return Response
                    .status(404)
                    .entity("Kaeufer nicht gefunden")
                    .build();
        }
        return Response
                .status(200)
                .entity(kaeufer)
                .build();
    }

}
