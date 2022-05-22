package dev.phill.autoscout.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Merkliste;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * test service
 */
@Path("merkliste")
public class Merklisteservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMerliste() {
        List<Merkliste> merklisteList = DataHandler.getInstance().readallMerkliste();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(merklisteList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren der Merkliste")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readMerkliste(
            @PathParam("uuid") String merklisteUUID

    ){
        Merkliste merkliste = DataHandler.getInstance().readMerklisteByUUID(merklisteUUID);
        if (merkliste == null) {
            return Response
                    .status(404)
                    .entity("Merkliste nicht gefunden")
                    .build();
        }
        return Response
                .status(200)
                .entity(merkliste)
                .build();
    }

}
