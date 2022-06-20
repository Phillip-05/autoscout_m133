package dev.phill.autoscout.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Dealer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * test service
 */
@Path("dealer")
public class Dealerservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDealer() {
        List<Dealer> dealerList = DataHandler.getInstance().readAllDealers();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(dealerList))
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
            @NotNull
            @NotEmpty
            @PathParam("uuid") String dealerUUID

    ){
        Dealer dealer = DataHandler.getInstance().readDealerByUUID(dealerUUID);
        if (dealer == null) {
            return Response
                    .status(404)
                    .entity("Haendler nicht gefunden")
                    .build();
        }
        return Response
                .status(200)
                .entity(dealer)
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
            @NotNull
            @NotEmpty
            @PathParam("uuid") String dealerUUID
    ) {
        int httpStatus = 200;

        if(!DataHandler.getInstance().deleteDealer(dealerUUID)){
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

            @NotNull
            @NotEmpty
            @FormParam("vorname") String vorname,

            @NotNull
            @NotEmpty
            @FormParam("strasse") String strasse,

            @NotNull
            @NotEmpty
            @FormParam("nachname") String nachname
            ) {
        int httpStatus = 200;

        try {
            Dealer dealer = new Dealer(UUID.randomUUID().toString(), vorname, strasse, nachname);

            dealer.setVorname(vorname);
            dealer.setNachname(nachname);
            dealer.setStrasse(strasse);
            DataHandler.getInstance().insertDealer(dealer);
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
            @NotNull
            @NotEmpty
            @FormParam("vorname") String vorname,

            @NotNull
            @NotEmpty
            @FormParam("strasse") String strasse,

            @NotNull
            @NotEmpty
            @FormParam("nachname") String nachname,

            @NotNull
            @NotEmpty
            @FormParam("dealerUUID") String dealerUUID
    ) {
        int httpStatus = 200;

        try {
            Dealer dealer = DataHandler.getInstance().readDealerByUUID(dealerUUID);

            dealer.setNachname(nachname);
            dealer.setVorname(vorname);
            dealer.setStrasse(strasse);

            DataHandler.getInstance().updateDealer();
        } catch (Exception e) {
            httpStatus = 500;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
