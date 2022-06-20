package dev.phill.autoscout.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.Dealer;
import dev.phill.autoscout.model.Vehicle;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

/**
 * test service
 */
@Path("vehicle")
public class Vehicleservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listVehicle() {
        List<Vehicle> vehicleList = DataHandler.getInstance().readallVehicle();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(vehicleList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Error by Vehicle list")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readVehicle(
            @NotEmpty
            @NotNull
            @PathParam("uuid") String vehicleUUID

    ) {
        Vehicle vehicle = DataHandler.getInstance().readVehicleByUUID(vehicleUUID);
        if (vehicle == null) {
            return Response
                    .status(404)
                    .entity("No Vehicle found")
                    .build();
        }

        return Response
                .status(200)
                .entity(vehicle)
                .build();
    }

    /*
     *
     *
     */
    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteVehicle(
            @NotEmpty
            @NotNull
            @PathParam("uuid") String vehicleUUID
    ) {
        int httpStatus = 200;

        if(!DataHandler.getInstance().deleteVehicle(vehicleUUID)){
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
    public Response insertVehicle(

            @NotEmpty
            @NotNull
            @Valid
            @FormParam("marke") String marke,

            @NotEmpty
            @NotNull
            @FormParam("modell") String modell,

            @NotEmpty
            @NotNull
            @Size(min = 0)
            @FormParam("preis") Float preis,

            @NotEmpty
            @NotNull
            @FormParam("dealerUUID") String dealerUUID,

            @NotEmpty
            @NotNull
            @Size(min = 1900)
            @FormParam("baujahr") Integer baujahr,

            @NotEmpty
            @NotNull
            @Size(min = 0)
            @FormParam("hubraum") Float hubraum,

            @NotEmpty
            @NotNull
            @Size(min = 0)
            @FormParam("leistung") Float leistung,

            @NotEmpty
            @NotNull
            @FormParam("tuning") Boolean tuning,

            @NotEmpty
            @NotNull
            @FormParam("mfk") Boolean mfk,

            @NotEmpty
            @NotNull
            @Size(min = 0)
            @FormParam("leergewicht") Float leergewicht,

            @NotEmpty
            @NotNull
            @Pattern(regexp="([0-9]{0,4} {0,1}[0-9]{1,4})")
            @FormParam("kennzeichen") String kennzeichen
    ) {
        int httpStatus = 200;

        try {
            Vehicle vehicle = new Vehicle(UUID.randomUUID().toString(), marke, modell, preis, baujahr, hubraum, DataHandler.getInstance().readDealerByUUID(dealerUUID), leistung, tuning, mfk, leergewicht,kennzeichen);
            DataHandler.getInstance().insertVehicle(vehicle);
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
    public Response updateVehicle(

            @NotEmpty
            @NotEmpty
            @FormParam("vehicleUUID") String vehicleUUID,

            @NotEmpty
            @NotNull
            @FormParam("marke") String marke,

            @NotEmpty
            @NotNull
            @FormParam("modell") String modell,

            @NotEmpty
            @NotNull
            @Size(min = 0)
            @FormParam("preis") Float preis,

            @NotEmpty
            @NotNull
            @FormParam("dealerUUID") String dealerUUID,

            @NotEmpty
            @NotNull
            @Size(min = 1900)
            @FormParam("baujahr") Integer baujahr,

            @NotEmpty
            @NotNull
            @Size(min = 0)
            @FormParam("hubraum") Float hubraum,

            @NotEmpty
            @NotNull
            @Size(min = 0)
            @FormParam("leistung") Float leistung,

            @NotEmpty
            @NotNull
            @FormParam("tuning") Boolean tuning,

            @NotEmpty
            @NotNull
            @FormParam("mfk") Boolean mfk,

            @NotEmpty
            @NotNull
            @Size(min = 0)
            @FormParam("leergewicht") Float leergewicht,

            @NotEmpty
            @NotNull
            @Pattern(regexp="([0-9]{0,4} {0,1}[0-9]{1,4})")
            @FormParam("kennzeichen") String kennzeichen
    ) {
        int httpStatus = 200;

        try {
            Vehicle vehicle = DataHandler.getInstance().readVehicleByUUID(vehicleUUID);

            vehicle.setDealer(DataHandler.getInstance().readDealerByUUID(dealerUUID));
            vehicle.setMarke(marke);
            vehicle.setModell(modell);
            vehicle.setPreis(preis);
            vehicle.setBaujahr(baujahr);
            vehicle.setHubraum(hubraum);
            vehicle.setLeistung(leistung);
            vehicle.setTuning(tuning);
            vehicle.setMfk(mfk);
            vehicle.setLeergewicht(leergewicht);

            DataHandler.getInstance().updateVehicle();
        } catch (Exception e) {
            httpStatus = 500;

        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


}
