package dev.phill.autoscout.data;



import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Haendler;
import dev.phill.autoscout.model.Kaeufer;
import dev.phill.autoscout.model.Merkliste;
import dev.phill.autoscout.service.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Fahrzeug> fahrzeugList;
    private List<Haendler> haendlerList;
    private List<Kaeufer> kaeuferList;
    private List<Merkliste> merklisteList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setHaendlerList(new ArrayList<>());
        readHaendlerJSON();
        setFahrzeugList(new ArrayList<>());
        readFahrzeugJSON();
        setKaeuferList(new ArrayList<>());
        readKaeuferJSON();
        setMerklisteList(new ArrayList<>());
        readMerklisteJSON();
    }


    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    public List<Fahrzeug> readallFahrzeuge() {
        return getFahrzeugList();
    }


    public Fahrzeug readFahrzeugByUUID(String fahrzeugUUID) {
        Fahrzeug fahrzeug = null;
        for (Fahrzeug entry : getFahrzeugList()) {
            if (entry.getFahrzeugUUID().equals(fahrzeugUUID)) {
                fahrzeug = entry;
            }
        }
        return fahrzeug;
    }

    public List<Kaeufer> readallKaeufer() {
        return getKaeuferList();
    }


    public Kaeufer readKaeuferByUUID(String kaeuferUUID) {
        Kaeufer kaeufer = null;
        for (Kaeufer entry : getKaeuferList()) {
            if (entry.getKaeuferUUID().equals(kaeuferUUID)) {
                kaeufer = entry;
            }
        }
        return kaeufer;
    }

    public List<Merkliste> readallMerkliste() {
        return getMerklisteList();
    }


    public Merkliste readMerklisteByUUID(String merklisteUUID) {
        Merkliste merkliste = null;
        for (Merkliste entry : getMerklisteList()) {
            if (entry.getMerklisteUUID().equals(merklisteUUID)) {
                merkliste = entry;
            }
        }
        return merkliste;
    }


    public List<Haendler> readAllHaendlers() {

        return getHaendlerList();
    }


    public Haendler readHaendlerByUUID(String haendlerUUID) {
        Haendler haendler = null;
        for (Haendler entry : getHaendlerList()) {
            if (entry.getHaendlerUUID().equals(haendlerUUID)) {
                haendler = entry;
            }
        }
        return haendler;
    }


    private void readFahrzeugJSON() {
        try {
            String path = Config.getProperty("fahrzeugJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Fahrzeug[] fahrzeuge = objectMapper.readValue(jsonData, Fahrzeug[].class);
            for (Fahrzeug fahrzeug : fahrzeuge) {
                getFahrzeugList().add(fahrzeug);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readKaeuferJSON() {
        try {
            String path = Config.getProperty("kaeuferJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Kaeufer[] kaeufers = objectMapper.readValue(jsonData, Kaeufer[].class);
            for (Kaeufer kaeufer : kaeufers) {
                getKaeuferList().add(kaeufer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readMerklisteJSON() {
        try {
            String path = Config.getProperty("merklistJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Merkliste[] merklistes = objectMapper.readValue(jsonData, Merkliste[].class);
            for (Merkliste merkliste : merklistes) {
                getMerklisteList().add(merkliste);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void readHaendlerJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("haendlerJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Haendler[] haendlers = objectMapper.readValue(jsonData, Haendler[].class);
            for (Haendler haendler : haendlers) {
                getHaendlerList().add(haendler);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private List<Fahrzeug> getFahrzeugList() {
        return fahrzeugList;
    }


    private void setFahrzeugList(List<Fahrzeug> fahrzeugList) {
        this.fahrzeugList = fahrzeugList;
    }

    private List<Kaeufer> getKaeuferList() {
        return kaeuferList;
    }


    private void setKaeuferList(List<Kaeufer> kaeuferList) {
        this.kaeuferList = kaeuferList;
    }

    private List<Merkliste> getMerklisteList() {
        return merklisteList;
    }


    private void setMerklisteList(List<Merkliste> merklisteList) {
        this.merklisteList = merklisteList;
    }

    private List<Haendler> getHaendlerList() {
        return haendlerList;
    }


    private void setHaendlerList(List<Haendler> haendlerList) {
        this.haendlerList = haendlerList;
    }


}