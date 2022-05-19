package dev.phill.autoscout.model;

import java.util.Date;

public class Merkliste {
    private dev.phillip.m133_autoscout.model.Fahrzeug[] fahrzeugliste;
    private String beschreibung;
    private Date erstellungsdatum;

    public dev.phillip.m133_autoscout.model.Fahrzeug[] getFahrzeugliste() {
        return fahrzeugliste;
    }

    public void setFahrzeugliste(dev.phillip.m133_autoscout.model.Fahrzeug[] fahrzeugliste) {
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
}
