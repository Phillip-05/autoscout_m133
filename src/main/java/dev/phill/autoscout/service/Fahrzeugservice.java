package dev.phill.autoscout.service;

import dev.phillip.m133_autoscout.data.DataHandler;
import dev.phillip.m133_autoscout.model.Fahrzeug;
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
@Path("fahrzeug")
public class Fahrzeugservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFahrzeuge() {
        List<Fahrzeug> fahrzeugList = DataHandler.getInstance();
        return Response
                .status(200)
                .entity(booklist)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readbook(
            @QueryParam("uuid") String bookUUID

    ){
        Book book = DataHandler.getInstance().readBookByUUID(bookUUID);
        return Response
                .status(200)
                .entity(book)
                .build();
    }

}
