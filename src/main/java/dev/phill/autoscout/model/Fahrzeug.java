package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;

public class Fahrzeug {

    private String fahrzeugUUID;
    private String marke;
    private String modell;
    private Float preis;

    @JsonIgnore
    private Haendler heandler;

    private Integer baujahr;
    private Float hubraum;
    private Float leistung;
    private Boolean tuning;
    private Boolean mfk;
    private Float leergewicht;

    public String getHeandlerUUID() {
        return getHeandler().getHaendlerUUID();
    }

    public Fahrzeug(){

    }

    public Fahrzeug(String fahrzeugUUID, String marke, String modell, Float preis, Integer baujahr, Float hubraum,Haendler heandler, Float leistung, Boolean tuning, Boolean mfk, Float leergewicht) {
        this.fahrzeugUUID = fahrzeugUUID;
        this.marke = marke;
        this.modell = modell;
        this.preis = preis;
        this.heandler = heandler;
        this.hubraum = hubraum;
        this.baujahr = baujahr;
        this.leistung = leistung;
        this.tuning = tuning;
        this.mfk = mfk;
        this.leergewicht = leergewicht;
    }

    public void setHeandlerUUID(String heandlerUUID) {
        setHeandler( new Haendler());
        Haendler haendler = DataHandler.getInstance().readHaendlerByUUID(heandlerUUID);
        getHeandler().setHaendlerUUID(heandlerUUID);
        getHeandler().setHaendlerUUID(haendler.getHaendlerUUID());

    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public Float getPreis() {
        return preis;
    }

    public void setPreis(Float preis) {
        this.preis = preis;
    }

    public Integer getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(Integer baujahr) {
        this.baujahr = baujahr;
    }

    public Float getHubraum() {
        return hubraum;
    }

    public void setHubraum(Float hubraum) {
        this.hubraum = hubraum;
    }

    public Float getLeistung() {
        return leistung;
    }

    public void setLeistung(Float leistung) {
        this.leistung = leistung;
    }

    public Boolean getTuning() {
        return tuning;
    }

    public void setTuning(Boolean tuning) {
        this.tuning = tuning;
    }

    public Boolean getMfk() {
        return mfk;
    }

    public void setMfk(Boolean mfk) {
        this.mfk = mfk;
    }

    public Float getLeergewicht() {
        return leergewicht;
    }

    public void setLeergewicht(Float leergewicht) {
        this.leergewicht = leergewicht;
    }

    public String getFahrzeugUUID() {
        return fahrzeugUUID;
    }

    public void setFahrzeugUUID(String fahrzeugUUID) {
        this.fahrzeugUUID = fahrzeugUUID;
    }

    public Haendler getHeandler() {
        return heandler;
    }

    public void setHeandler(Haendler heandler) {
        this.heandler = heandler;
    }
}
