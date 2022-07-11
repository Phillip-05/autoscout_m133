package dev.phill.autoscout.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Vehicle;
import dev.phill.autoscout.model.Watchlist;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * watchlist services
 */
@Path("watchlist")
public class Watchlistservice {

    /**
     * Gets whole json and returns it
     */

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user","admin"})
    public Response listWatchlist() {
        List<Watchlist> watchlistList = DataHandler.readallWatchlist();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(watchlistList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(400)
                    .entity("Error by Watchlist")
                    .build();
        }
    }

    /**
     * Reads a Watchlist buy its UUID
     */
    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user","admin"})
    public Response readWatchlist(
            @NotEmpty
            @NotNull
            @PathParam("uuid") String watchlistUUID

    ){
        Watchlist watchlist = DataHandler.readWatchlistByUUID(watchlistUUID);
        if (watchlist == null) {
            return Response
                    .status(400)
                    .entity("No Watchlist found")
                    .build();
        }
        return Response
                .status(200)
                .entity(watchlist)
                .build();
    }

    /**
     * Deletes a Watchlist buy its UUID
     */
    @DELETE
    @Path("delete/{uuid}")
    @RolesAllowed({"admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteWatchlist(
            @NotEmpty
            @NotNull
            @PathParam("uuid") String watchlistUUID
    ) {
        int httpStatus = 200;

        if(!DataHandler.deleteWatchlist(watchlistUUID)){
            httpStatus = 400;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Inserts a Watchlist
     */
    @POST
    @Path("insert")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response insertWatchlist(

            @NotEmpty
            @NotNull
            @FormParam("vehicleUUIDs") String vehicleUUIDs,

            @NotEmpty
            @NotNull
            @FormParam("beschreibung") String beschreibung

    ) {
        int httpStatus = 200;

        try {
            String[] allvehicleUUIDs = vehicleUUIDs.split(";");

            Vector<Vehicle> vehicleVector = new Vector<>();

            for (int i = 0; i < allvehicleUUIDs.length; i++) {
                vehicleVector.add(DataHandler.readVehicleByUUID(allvehicleUUIDs[i]));
            }

            Watchlist watchlist = new Watchlist(UUID.randomUUID().toString(),vehicleVector,beschreibung);
            DataHandler.insertWatchlist(watchlist);
        } catch (Exception e) {
            httpStatus = 400;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Updates a Watchlist buy its UUID
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response updateWatchlist(

            @NotEmpty
            @NotNull
            @FormParam("vehicleUUIDs") String vehicleUUIDs,

            @NotEmpty
            @NotNull
            @FormParam("beschreibung") String beschreibung,

            @NotEmpty
            @NotNull
            @FormParam("watchlistUUID") String watchlistUUID

    ) {
        int httpStatus = 200;

        try {
            String[] allvehicleUUIDs = vehicleUUIDs.split(";");

            Vector<Vehicle> vehicleVector = new Vector<>();

            for (int i = 0; i < allvehicleUUIDs.length; i++) {
                vehicleVector.add(DataHandler.readVehicleByUUID(allvehicleUUIDs[i]));
            }

            Watchlist watchlist = DataHandler.readWatchlistByUUID(watchlistUUID);

            watchlist.setVehiclelist(vehicleVector);
            watchlist.setBeschreibung(beschreibung);

            DataHandler.updateWatchlist();
        } catch (Exception e) {
            httpStatus = 400;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
