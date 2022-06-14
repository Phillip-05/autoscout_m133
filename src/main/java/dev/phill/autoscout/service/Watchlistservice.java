package dev.phill.autoscout.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Vehicle;
import dev.phill.autoscout.model.Watchlist;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * test service
 */
@Path("watchlist")
public class Watchlistservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listWatchlist() {
        List<Watchlist> watchlistList = DataHandler.getInstance().readallWatchlist();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(watchlistList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Error by Watchlist")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readWatchlist(
            @PathParam("uuid") String watchlistUUID

    ){
        Watchlist watchlist = DataHandler.getInstance().readWatchlistByUUID(watchlistUUID);
        if (watchlist == null) {
            return Response
                    .status(404)
                    .entity("No Watchlist found")
                    .build();
        }
        return Response
                .status(200)
                .entity(watchlist)
                .build();
    }

    /*
     *
     *
     */
    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteWatchlist(
            @PathParam("uuid") String watchlistUUID
    ) {
        int httpStatus = 200;

        if(!DataHandler.getInstance().deleteWatchlist(watchlistUUID)){
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
    public Response insertWatchlist(
            @FormParam("vehicleUUIDs") String vehicleUUIDs,
            @FormParam("beschreibung") String beschreibung

    ) {
        int httpStatus = 200;

        try {
            String[] allvehicleUUIDs = vehicleUUIDs.split(";");

            Vector<Vehicle> vehicleVector = new Vector<>();

            for (int i = 0; i < allvehicleUUIDs.length; i++) {
                vehicleVector.add(DataHandler.getInstance().readVehicleByUUID(allvehicleUUIDs[i]));
            }

            Watchlist watchlist = new Watchlist(UUID.randomUUID().toString(),vehicleVector,beschreibung);
            DataHandler.getInstance().insertWatchlist(watchlist);
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
    public Response updateWatchlist(

            @FormParam("vehicleUUIDs") String vehicleUUIDs,
            @FormParam("beschreibung") String beschreibung,
            @FormParam("watchlistUUID") String watchlistUUID

    ) {
        int httpStatus = 200;

        try {
            String[] allvehicleUUIDs = vehicleUUIDs.split(";");

            Vector<Vehicle> vehicleVector = new Vector<>();

            for (int i = 0; i < allvehicleUUIDs.length; i++) {
                vehicleVector.add(DataHandler.getInstance().readVehicleByUUID(allvehicleUUIDs[i]));
            }

            Watchlist watchlist = DataHandler.getInstance().readWatchlistByUUID(watchlistUUID);

            watchlist.setVehiclelist(vehicleVector);
            watchlist.setBeschreibung(beschreibung);

            DataHandler.getInstance().updateWatchlist();
        } catch (Exception e) {
            httpStatus = 500;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
