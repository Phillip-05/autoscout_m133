package dev.phill.autoscout.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Buyer;
import dev.phill.autoscout.model.Vehicle;
import dev.phill.autoscout.model.Watchlist;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * Buyers services
 */
@Path("buyer")
public class Buyerservice {

    /**
     * Gets whole json and returns it
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user","admin"})
    public Response listBuyer() {
        List<Buyer> buyerList = DataHandler.readallBuyer();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(buyerList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(400)
                    .entity("Error by Buyer")
                    .build();
        }
    }

    /**
     * Gets one Buyer by his UUID
     */
    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user","admin"})
    public Response readBuyer(
            @NotNull
            @NotEmpty
            @PathParam("uuid") String buyerUUID

    ){
        Buyer buyer = DataHandler.readBuyerByUUID(buyerUUID);
        if (buyer == null) {
            return Response
                    .status(400)
                    .entity("No Buyer found")
                    .build();
        }
        return Response
                .status(200)
                .entity(buyer)
                .build();
    }

    /**
     * Delete a Buyer buy its UUID
     */
    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response deleteBuyer(
            @NotNull
            @NotEmpty
            @PathParam("uuid") String buyerUUID
    ) {
        int httpStatus = 200;

        if(!DataHandler.deleteBuyer(buyerUUID)){
            httpStatus = 400;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Inserts a Buyer
     */
    @POST
    @Path("insert")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response insertBuyer(

            @NotNull
            @NotEmpty
            @FormParam("watchlistUUID") String watchlistUUID,

            @NotNull
            @Min(1900)
            @FormParam("jahrgang") Integer jahrgang

    ) {
        int httpStatus = 200;

        try {


            Buyer buyer = new Buyer(UUID.randomUUID().toString(),jahrgang,DataHandler.readWatchlistByUUID(watchlistUUID));
            DataHandler.insertBuyer(buyer);
        } catch (Exception e) {
            httpStatus = 400;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Updates a Buyer buy its UUID
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response updateBuyer(

            @NotNull
            @NotEmpty
            @FormParam("buyerUUID") String buyerUUID,

            @NotNull
            @NotEmpty
            @FormParam("watchlistUUID") String watchlistUUID,

            @NotNull
            @Min(1900)
            @FormParam("jahrgang") Integer jahrgang

    ) {
        int httpStatus = 200;

        try {
            Buyer buyer = DataHandler.readBuyerByUUID(buyerUUID);

            buyer.setJahrgang(jahrgang);
            buyer.setWatchlist(DataHandler.readWatchlistByUUID(watchlistUUID));


            DataHandler.updateBuyer();
        } catch (Exception e) {
            httpStatus = 400;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


}
