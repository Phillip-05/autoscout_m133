package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;

public class Kaeufer {
    private String KaeuferUUID;
    private Integer jahrgang;
    @JsonIgnore
    private Merkliste merkliste;

    public Kaeufer(){

    }

    public String getMerklisteUUID() {
        return getMerkliste().getMerklisteUUID();
    }

    public void setMerklisteUUID(String merklisteUUID) {
        setMerkliste(new Merkliste());
        Merkliste merkliste = DataHandler.getInstance().readMerklisteByUUID(merklisteUUID);
        getMerkliste().setMerklisteUUID(merklisteUUID);
        getMerkliste().setMerklisteUUID(merkliste.getMerklisteUUID());

    }

    public Kaeufer(String kaeuferUUID, Integer jahrgang, Merkliste merkliste) {
        KaeuferUUID = kaeuferUUID;
        this.jahrgang = jahrgang;

        this.merkliste = merkliste;
    }

    public String getKaeuferUUID() {
        return KaeuferUUID;
    }

    public void setKaeuferUUID(String kaeuferUUID) {
        KaeuferUUID = kaeuferUUID;
    }

    public Integer getJahrgang() {
        return jahrgang;
    }

    public void setJahrgang(Integer jahrgang) {
        this.jahrgang = jahrgang;
    }

    public Merkliste getMerkliste() {
        return merkliste;
    }

    public void setMerkliste(Merkliste merkliste) {
        this.merkliste = merkliste;
    }
}
