package dev.phill.autoscout.service;


import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Kaeufer;
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
@Path("kaeufer")
public class Kaeuferservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKaeufer() {
        List<Kaeufer> kaeuferList = DataHandler.getInstance().readallKaeufer();
        return Response
                .status(200)
                .entity(kaeuferList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKaeufer(
            @QueryParam("uuid") String kaeuferUUID

    ){
        Kaeufer kaeufer = DataHandler.getInstance().readKaeuferByUUID(kaeuferUUID);
        return Response
                .status(200)
                .entity(kaeufer)
                .build();
    }

}
