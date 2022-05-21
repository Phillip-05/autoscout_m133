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

    public Vector<String> getFahrzeugUUID() {
        Vector<String> uuids = new Vector<>();
        if(fahrzeugliste != null){
            for (Fahrzeug fahrzeug : fahrzeugliste) {
                uuids.add(fahrzeug.getFahrzeugUUID());
            }
        }
        return uuids;
    }

    public void setFahrzeugUUID(Vector<String> fahrzeugUUIDs) {
        fahrzeugliste = new Vector<>();
        if(fahrzeugUUIDs != null) {
            for (String fahrzeugUUID : fahrzeugUUIDs) {
                Fahrzeug fahrzeug = DataHandler.getInstance().readFahrzeugByUUID(fahrzeugUUID);
                fahrzeugliste.add(fahrzeug);
            }
        }


    }


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
