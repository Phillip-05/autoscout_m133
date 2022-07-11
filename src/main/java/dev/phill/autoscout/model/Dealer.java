package dev.phill.autoscout.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * A Car dealer
 */
@Getter
@Setter
@NoArgsConstructor
public class Dealer {

    private String dealerUUID;

    private String vorname;

    private String nachname;

    private String strasse;

    /**
     * heandler constructor
     * @param: dealerUUID
     * @param: vorname
     * @param: nachname
     * @param: strasse
     *
     */

    public Dealer(String dealerUUID, String vorname, String nachname, String strasse) {
        this.dealerUUID = dealerUUID;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
    }


}
