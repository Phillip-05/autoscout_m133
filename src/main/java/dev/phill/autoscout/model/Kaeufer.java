package dev.phill.autoscout.model;

import java.util.Vector;

public class Kaeufer {
    private String KaeuferUUID;

    private Vector<Merkliste> merklisten = new Vector<Merkliste>();



    public String getKaeuferUUID() {
        return KaeuferUUID;
    }

    public void setKaeuferUUID(String kaeuferUUID) {
        KaeuferUUID = kaeuferUUID;
    }

    public Vector<Merkliste> getMerklisten() {
        return merklisten;
    }

    public void setMerklisten(Vector<Merkliste> merklisten) {
        this.merklisten = merklisten;
    }
}
