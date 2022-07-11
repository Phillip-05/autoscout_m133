package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    private String vehicleUUID;

    private String marke;

    private String modell;

    private Float preis;

    @JsonIgnore
    private Dealer dealer;

    private Integer baujahr;

    private Float hubraum;

    private Float leistung;

    private Boolean tuning;

    private Boolean mfk;

    private Float leergewicht;

    private String kennzeichen;



    /**
     * constructor for a new Fahrzeug
     * @param: fahrzeugUUID
     * @param: marke
     * @param: modell
     * @param: preis
     * @param: haendler
     * @param: baujahr
     * @param: hubraum
     * @param: leistung
     * @param: tuning
     * @param: mfk
     * @param: leergewicht
     *
     */
    public Vehicle(String vehicleUUID, String marke, String modell, Float preis, Integer baujahr, Float hubraum, Dealer heandler, Float leistung, Boolean tuning, Boolean mfk, Float leergewicht,String kennzeichen) {
        this.vehicleUUID = vehicleUUID;
        this.marke = marke;
        this.modell = modell;
        this.preis = preis;
        this.dealer = heandler;
        this.hubraum = hubraum;
        this.baujahr = baujahr;
        this.leistung = leistung;
        this.tuning = tuning;
        this.mfk = mfk;
        this.leergewicht = leergewicht;
        this.kennzeichen = kennzeichen;
    }

    /**
     * gets Haendler by its uuid
     * @param:
     */
    public String getDealerUUID() {
        return getDealer().getDealerUUID();
    }

    /**
     * sets Haendler by its uuid
     * @param: dealerUUID
     */
    public void setDealerUUID(String dealerUUID) {
        setDealer( new Dealer());
        Dealer dealer = DataHandler.readDealerByUUID(dealerUUID);
        getDealer().setDealerUUID(dealerUUID);
        getDealer().setDealerUUID(dealer.getDealerUUID());

    }
}
