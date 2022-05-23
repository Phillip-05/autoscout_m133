package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Kaeufer {
    private String KaeuferUUID;
    private Integer jahrgang;
    @JsonIgnore
    private Merkliste merkliste;

    /**
     * gets Merkliste by its uuid
     * @return Merkliste
     */
    public String getMerklisteUUID() {
        return getMerkliste().getMerklisteUUID();
    }

    /**
     * sets Merkliste by its uuid
     * @param: MerklisteUUID
     */
    public void setMerklisteUUID(String merklisteUUID) {
        setMerkliste(new Merkliste());
        Merkliste merkliste = DataHandler.getInstance().readMerklisteByUUID(merklisteUUID);
        getMerkliste().setMerklisteUUID(merklisteUUID);
        getMerkliste().setMerklisteUUID(merkliste.getMerklisteUUID());

    }

    /**
     * Kaeufer constructor
     * @param: KaeuferUUID
     * @param: jahrgang
     * @param: merkliste
     */
    public Kaeufer(String kaeuferUUID, Integer jahrgang, Merkliste merkliste) {
        KaeuferUUID = kaeuferUUID;
        this.jahrgang = jahrgang;

        this.merkliste = merkliste;
    }
}
