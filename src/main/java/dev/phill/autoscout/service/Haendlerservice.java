package dev.phill.autoscout.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Haendler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

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

    /*
    *
    *
     */
    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHaendler(
            @PathParam("uuid") String haendlerUUID
    ) {
        int httpStatus = 200;

        if(!DataHandler.getInstance().deleteHaendler(haendlerUUID)){
            httpStatus = 404;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @POST
    @Path("insert")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHaendler(
            @FormParam("vorname") String vorname,
            @FormParam("strasse") String strasse,
            @FormParam("nachname") String nachname
            ) {
        int httpStatus = 200;
        System.out.println(vorname);
        System.out.println(nachname);
        System.out.println(strasse);
        try {
            Haendler haendler = new Haendler(UUID.randomUUID().toString(), vorname, strasse, nachname);
            DataHandler.getInstance().insertHaendler(haendler);
        } catch (Exception e) {
            httpStatus = 500;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHaendler(

            @FormParam("vorname") String vorname,
            @FormParam("strasse") String strasse,
            @FormParam("nachname") String nachname,
            @FormParam("haendlerUUID") String haendlerUUID
    ) {
        int httpStatus = 200;

        try {
            Haendler haendler = DataHandler.getInstance().readHaendlerByUUID(haendlerUUID);

            haendler.setNachname(nachname);
            haendler.setVorname(vorname);
            haendler.setStrasse(strasse);

            DataHandler.getInstance().updateHaendler();
        } catch (Exception e) {
            httpStatus = 500;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
