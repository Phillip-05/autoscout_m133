package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Vehicle {

    private String vehicleUUID;

//    @FormParam("marke")
//    @NotEmpty
    private String marke;

//    @FormParam("modell")
//    @NotEmpty
    private String modell;

//    @FormParam("preis")
//    @Size(min = 0)
//    @NotEmpty
    private Float preis;

    @JsonIgnore
    private Dealer dealer;

//    @FormParam("baujahr")
//    @Size(min = 1900)
//    @NotEmpty
    private Integer baujahr;

//    @FormParam("hubraum")
//    @Size(min = 0)
//    @NotEmpty
    private Float hubraum;

//    @FormParam("leistung")
//    @Size(min = 0)
//    @NotEmpty
    private Float leistung;

//    @FormParam("tuning")
//    @NotEmpty
    private Boolean tuning;

//    @FormParam("mfk")
//    @NotEmpty
    private Boolean mfk;

//    @FormParam("leergewicht")
//    @Size(min = 0)
//    @NotEmpty
    private Float leergewicht;

    /**
     * constructor for a new Fahrzeug
     * @param: fahrzeugUUID
     * @param: marke
     * @param: modell
     * @param: preis
     * @param: haendler
     * @param: baujahr
     * @param: hubraum
     * @param: leistung
     * @param: tuning
     * @param: mfk
     * @param: leergewicht
     *
     */
    public Vehicle(String vehicleUUID, String marke, String modell, Float preis, Integer baujahr, Float hubraum, Dealer heandler, Float leistung, Boolean tuning, Boolean mfk, Float leergewicht) {
        this.vehicleUUID = vehicleUUID;
        this.marke = marke;
        this.modell = modell;
        this.preis = preis;
        this.dealer = heandler;
        this.hubraum = hubraum;
        this.baujahr = baujahr;
        this.leistung = leistung;
        this.tuning = tuning;
        this.mfk = mfk;
        this.leergewicht = leergewicht;
    }

    /**
     * gets Haendler by its uuid
     * @param:
     */
    public String getDealerUUID() {
        return getDealer().getDealerUUID();
    }

    /**
     * sets Haendler by its uuid
     * @param: haendlerUUID
     */
    public void setDealerUUID(String dealerUUID) {
        setDealer( new Dealer());
        Dealer dealer = DataHandler.getInstance().readDealerByUUID(dealerUUID);
        getDealer().setDealerUUID(dealerUUID);
        getDealer().setDealerUUID(dealer.getDealerUUID());

    }
}
