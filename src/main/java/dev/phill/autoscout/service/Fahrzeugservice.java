package dev.phill.autoscout.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Fahrzeug;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * test service
 */
@Path("fahrzeug")
public class Fahrzeugservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFahrzeuge() {
        List<Fahrzeug> fahrzeugList = DataHandler.getInstance().readallFahrzeuge();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(fahrzeugList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren der Fahrzeuge")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFahrzeug(
            @PathParam("uuid") String fahrzeugUUID

    ) {
        Fahrzeug fahrzeug = DataHandler.getInstance().readFahrzeugByUUID(fahrzeugUUID);
        if (fahrzeug == null) {
            return Response
                    .status(404)
                    .entity("Fahrzeug nicht gefunden")
                    .build();
        }

        return Response
                .status(200)
                .entity(fahrzeug)
                .build();
    }

}
