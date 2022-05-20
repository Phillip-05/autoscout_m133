package dev.phill.autoscout.model;

public class Kaeufer {
    private String KaeuferUUID;
    private Merkliste[] merkliste;

    public Merkliste[] getMerkliste() {
        return merkliste;
    }

    public void setMerkliste(Merkliste[] merkliste) {
        this.merkliste = merkliste;
    }

    public String getKaeuferUUID() {
        return KaeuferUUID;
    }

    public void setKaeuferUUID(String kaeuferUUID) {
        KaeuferUUID = kaeuferUUID;
    }
}
