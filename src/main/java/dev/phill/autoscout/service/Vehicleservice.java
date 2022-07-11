package dev.phill.autoscout.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Dealer;
import dev.phill.autoscout.model.Vehicle;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

/**
 * vehicle services
 */
@Path("vehicle")
public class Vehicleservice {

    /**
     * Gets whole json and returns it
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user","admin"})
    public Response listVehicle() {
        List<Vehicle> vehicleList = DataHandler.readallVehicle();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(vehicleList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(400)
                    .entity("Error by Vehicle list")
                    .build();
        }
    }

    /**
     * Reads a Vehicle buy its UUID
     */
    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user","admin"})
    public Response readVehicle(
            @NotEmpty
            @NotNull
            @PathParam("uuid") String vehicleUUID

    ) {
        Vehicle vehicle = DataHandler.readVehicleByUUID(vehicleUUID);
        if (vehicle == null) {
            return Response
                    .status(400)
                    .entity("No Vehicle found")
                    .build();
        }

        return Response
                .status(200)
                .entity(vehicle)
                .build();
    }

    /**
     * Deletes a Vehicle buy its UUID
     */
    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response deleteVehicle(
            @NotEmpty
            @NotNull
            @PathParam("uuid") String vehicleUUID
    ) {
        int httpStatus = 200;

        if(!DataHandler.deleteVehicle(vehicleUUID)){
            httpStatus = 400;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Inserts a Vehicle
     */
    @POST
    @Path("insert")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response insertVehicle(

            @NotEmpty
            @NotNull
            @FormParam("marke") String marke,

            @NotEmpty
            @NotNull
            @FormParam("modell") String modell,

            @NotNull
            @Min(0)
            @FormParam("preis") Float preis,

            @NotEmpty
            @NotNull
            @FormParam("dealerUUID") String dealerUUID,

            @NotNull
            @Min(1900)
            @FormParam("baujahr") Integer baujahr,

            @NotNull
            @Min(0)
            @FormParam("hubraum") Float hubraum,

            @NotNull
            @Min(0)
            @FormParam("leistung") Float leistung,

            @NotNull
            @FormParam("tuning") Boolean tuning,

            @NotNull
            @FormParam("mfk") Boolean mfk,

            @NotNull
            @Min(0)
            @FormParam("leergewicht") Float leergewicht,

            @NotEmpty
            @NotNull
            @Pattern(regexp="([0-9]{0,4} {0,1}[0-9]{1,4})")
            @FormParam("kennzeichen") String kennzeichen
    ) {
        int httpStatus = 200;

        try {
            Vehicle vehicle = new Vehicle(UUID.randomUUID().toString(), marke, modell, preis, baujahr, hubraum,
                    DataHandler.readDealerByUUID(dealerUUID), leistung, tuning, mfk, leergewicht,kennzeichen);
            DataHandler.insertVehicle(vehicle);
        } catch (Exception e) {
            httpStatus = 400;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Updates a Vehicle buy its UUID
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response updateVehicle(

            @NotNull
            @NotEmpty
            @FormParam("vehicleUUID") String vehicleUUID,

            @NotEmpty
            @NotNull
            @FormParam("marke") String marke,

            @NotEmpty
            @NotNull
            @FormParam("modell") String modell,

            @NotNull
            @Min(0)
            @FormParam("preis") Float preis,

            @NotEmpty
            @NotNull
            @FormParam("dealerUUID") String dealerUUID,

            @NotNull
            @Min(1900)
            @FormParam("baujahr") Integer baujahr,

            @NotNull
            @Min(0)
            @FormParam("hubraum") Float hubraum,

            @NotNull
            @Min(0)
            @FormParam("leistung") Float leistung,

            @NotNull
            @FormParam("tuning") Boolean tuning,

            @NotNull
            @FormParam("mfk") Boolean mfk,

            @NotNull
            @Min(0)
            @FormParam("leergewicht") Float leergewicht,

            @NotEmpty
            @NotNull
            @Pattern(regexp="([0-9]{0,4} {0,1}[0-9]{1,4})")
            @FormParam("kennzeichen") String kennzeichen
    ) {
        int httpStatus = 200;

        try {
            Vehicle vehicle = DataHandler.readVehicleByUUID(vehicleUUID);

            vehicle.setDealer(DataHandler.readDealerByUUID(dealerUUID));
            vehicle.setMarke(marke);
            vehicle.setModell(modell);
            vehicle.setPreis(preis);
            vehicle.setBaujahr(baujahr);
            vehicle.setHubraum(hubraum);
            vehicle.setLeistung(leistung);
            vehicle.setTuning(tuning);
            vehicle.setMfk(mfk);
            vehicle.setLeergewicht(leergewicht);

            DataHandler.updateVehicle();
        } catch (Exception e) {
            httpStatus = 400;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


}
