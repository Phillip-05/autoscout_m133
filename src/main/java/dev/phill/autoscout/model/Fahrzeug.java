package dev.phill.autoscout.model;

public class Fahrzeug {

    private String fahrzeugUUID;
    private String marke;
    private String modell;
    private Float preis;
    private Integer baujahr;
    private Float hubraum;
    private Float leistung;
    private Boolean tuning;
    private Boolean mfk;
    private Float leergewicht;

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
}
