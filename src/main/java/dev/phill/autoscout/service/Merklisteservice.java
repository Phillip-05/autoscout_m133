package dev.phill.autoscout.service;


import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Merkliste;
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
@Path("merkliste")
public class Merklisteservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMerliste() {
        List<Merkliste> merklisteList = DataHandler.getInstance().readallMerkliste();
        return Response
                .status(200)
                .entity(merklisteList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readMerkliste(
            @QueryParam("uuid") String merklisteUUID

    ){
        Merkliste merkliste = DataHandler.getInstance().readMerklisteByUUID(merklisteUUID);
        return Response
                .status(200)
                .entity(merkliste)
                .build();
    }

}
