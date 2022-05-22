package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Fahrzeug {

    private String fahrzeugUUID;
    private String marke;
    private String modell;
    private Float preis;

    @JsonIgnore
    private Haendler haendler;

    private Integer baujahr;
    private Float hubraum;
    private Float leistung;
    private Boolean tuning;
    private Boolean mfk;
    private Float leergewicht;

    /**
     * constructor for a new Fahrzeug
     */
    public Fahrzeug(String fahrzeugUUID, String marke, String modell, Float preis, Integer baujahr, Float hubraum,Haendler heandler, Float leistung, Boolean tuning, Boolean mfk, Float leergewicht) {
        this.fahrzeugUUID = fahrzeugUUID;
        this.marke = marke;
        this.modell = modell;
        this.preis = preis;
        this.haendler = heandler;
        this.hubraum = hubraum;
        this.baujahr = baujahr;
        this.leistung = leistung;
        this.tuning = tuning;
        this.mfk = mfk;
        this.leergewicht = leergewicht;
    }

    /**
     * gets Haendler by its uuid
     */
    public String getHaendlerUUID() {
        return getHaendler().getHaendlerUUID();
    }

    /**
     * sets Haendler by its uuid
     */
    public void setHaendlerUUID(String heandlerUUID) {
        setHaendler( new Haendler());
        Haendler haendler = DataHandler.getInstance().readHaendlerByUUID(heandlerUUID);
        getHaendler().setHaendlerUUID(heandlerUUID);
        getHaendler().setHaendlerUUID(haendler.getHaendlerUUID());

    }
}
