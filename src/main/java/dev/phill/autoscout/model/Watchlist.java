package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ws.rs.FormParam;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Vector;

@Getter
@Setter
@NoArgsConstructor
public class Watchlist {

    @FormParam("WatchlistUUID")
    @NotEmpty
    @NotNull
    private String WatchlistUUID;

    @JsonIgnore
    private Vector<Vehicle> vehiclelist;

    @FormParam("beschreibung")
    @NotEmpty
    @NotNull
    private String beschreibung;

    /**
     * gets Vehicle by its uuid
     * @return VehicleUUids
     */
    public Vector<String> getVehicleUUID() {
        Vector<String> uuids = new Vector<>();
        if(vehiclelist != null){
            for (Vehicle vehicle : vehiclelist) {
                uuids.add(vehicle.getVehicleUUID());
            }
        }
        return uuids;
    }

    /**
     * set Vehicle by its uuid
     * @param: VehicleUUIDs
     */
    public void setVehicleUUID(Vector<String> vehicleUUIDs) {
        vehiclelist = new Vector<>();
        if(vehicleUUIDs != null) {
            for (String vehicleUUID : vehicleUUIDs) {
                Vehicle vehicle = DataHandler.getInstance().readVehicleByUUID(vehicleUUID);
                vehiclelist.add(vehicle);
            }
        }


    }

    /**
     * Merkliste constructor
     * @param: MerklisteUUID
     * @param: fahrzeugliste
     * @param: beschreibung
     */
    public Watchlist(String merklisteUUID, Vector<Vehicle> vehiclelist, String beschreibung) {
        this.WatchlistUUID = merklisteUUID;
        this.vehiclelist = vehiclelist;
        this.beschreibung = beschreibung;

    }

    /**
     * add Fahrzeug to Merkliste
     */
    public void addVehicle(Vehicle vehicle) {
        vehiclelist.add(vehicle);
    }

    /**
     * get Fahrzeug to Merkliste
     */
    public Vehicle getVehicle(int index) {
        return vehiclelist.get(index);
    }

}