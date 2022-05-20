package dev.phill.autoscout.model;

import java.util.Date;
import java.util.Vector;

public class Merkliste {
    private String MerklisteUUID;
    private Vector<Fahrzeug> fahrzeugliste = new Vector<Fahrzeug>();
    private String beschreibung;
    private Date erstellungsdatum;





    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Date getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(Date erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
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
