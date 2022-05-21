package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;

import java.util.Date;
import java.util.Vector;

public class Merkliste {
    private String MerklisteUUID;
    @JsonIgnore
    private Vector<Fahrzeug> fahrzeugliste;
    private String beschreibung;

//    public String getFahrzeugUUID() {
//        return getFahrzeugliste().firstElement().getFahrzeugUUID();
//    }
//
//    public void setFahrzeugUUID(String merklisteUUID) {
//        setFahrzeugliste(new Vector<Merkliste>(new Fahrzeug()));
//        Haendler haendler = DataHandler.getInstance().readHaendlerByUUID(merklisteUUID);
//        getFahrzeugliste().firstElement.(merklisteUUID);
//        getFahrzeugliste().setHaendlerUUID(haendler.getHaendlerUUID());
//
//    }


    public Merkliste() {

    }

    public Merkliste(String merklisteUUID, Vector<Fahrzeug> fahrzeugliste, String beschreibung) {
        this.MerklisteUUID = merklisteUUID;
        this.fahrzeugliste = fahrzeugliste;
        this.beschreibung = beschreibung;

    }

    public void addFahrzeug(Fahrzeug fahrzeug) {
        fahrzeugliste.add(fahrzeug);
    }

    public Fahrzeug getFahrzeug(int index) {
        return fahrzeugliste.get(index);
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getMerklisteUUID() {
        return MerklisteUUID;
    }

    public void setMerklisteUUID(String merklisteUUID) {
        MerklisteUUID = merklisteUUID;
    }

    public Vector<Fahrzeug> getFahrzeugliste() {
        return fahrzeugliste;
    }

    public void setFahrzeugliste(Vector<Fahrzeug> fahrzeugliste) {
        this.fahrzeugliste = fahrzeugliste;
    }
}
