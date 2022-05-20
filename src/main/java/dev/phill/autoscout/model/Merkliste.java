package dev.phill.autoscout.model;

import java.util.Date;

public class Merkliste {
    private String MerklisteUUID;
    private Fahrzeug[] fahrzeugliste;
    private String beschreibung;
    private Date erstellungsdatum;

    public Fahrzeug[] getFahrzeugliste() {
        return fahrzeugliste;
    }

    public void setFahrzeugliste(Fahrzeug[] fahrzeugliste) {
        this.fahrzeugliste = fahrzeugliste;
    }

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
}
