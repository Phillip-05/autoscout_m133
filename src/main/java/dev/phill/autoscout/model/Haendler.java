package dev.phill.autoscout.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Haendler {

    private String haendlerUUID;
    private String vorname;
    private String nachname;
    private String strasse;

    public Haendler(String haendlerUUID, String vorname, String nachname, String strasse) {
        this.haendlerUUID = haendlerUUID;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
    }


}
