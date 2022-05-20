package dev.phill.autoscout.model;

public class Haendler {

    private String haendlerUUID;
    private String vorname;
    private String nachname;
    private String strasse;

    public String getHaendlerUUID() {
        return haendlerUUID;
    }

    public void setHaendlerUUID(String haendlerUUID) {
        this.haendlerUUID = haendlerUUID;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }
}
