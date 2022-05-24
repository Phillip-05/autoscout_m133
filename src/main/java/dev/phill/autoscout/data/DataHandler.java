package dev.phill.autoscout.data;



import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Haendler;
import dev.phill.autoscout.model.Kaeufer;
import dev.phill.autoscout.model.Merkliste;
import dev.phill.autoscout.service.Config;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    /**
     * gets the Instance of this class
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /**
     * reads all Fahrzeuge
     */
    public List<Fahrzeug> readallFahrzeuge() {
        return getFahrzeugList();
    }

    /**
     * reads a Fahrzeug by its uuid
     */
    public Fahrzeug readFahrzeugByUUID(String fahrzeugUUID) {
        Fahrzeug fahrzeug = null;
        for (Fahrzeug entry : getFahrzeugList()) {
            if (entry.getFahrzeugUUID().equals(fahrzeugUUID)) {
                fahrzeug = entry;
            }
        }
        return fahrzeug;
    }

    /**
     * reads all Haendler
     */
    public List<Kaeufer> readallKaeufer() {
        return getKaeuferList();
    }


    /**
     * reads a Kaeufer by its uuid
     */
    public Kaeufer readKaeuferByUUID(String kaeuferUUID) {
        Kaeufer kaeufer = null;
        for (Kaeufer entry : getKaeuferList()) {
            if (entry.getKaeuferUUID().equals(kaeuferUUID)) {
                kaeufer = entry;
            }
        }
        return kaeufer;
    }

    /**
     * reads all Haendler
     */
    public List<Merkliste> readallMerkliste() {
        return getMerklisteList();
    }

    /**
     * reads a Merkliste by its uuid
     */
    public Merkliste readMerklisteByUUID(String merklisteUUID) {
        Merkliste merkliste = null;
        for (Merkliste entry : getMerklisteList()) {
            if (entry.getMerklisteUUID().equals(merklisteUUID)) {
                merkliste = entry;
            }
        }
        return merkliste;
    }

    /**
     * reads all Haendler
     */
    public List<Haendler> readAllHaendlers() {

        return getHaendlerList();
    }

    /**
     * reads a Haendler by its uuid
     */
    public Haendler readHaendlerByUUID(String haendlerUUID) {
        Haendler haendler = null;
        for (Haendler entry : getHaendlerList()) {
            if (entry.getHaendlerUUID().equals(haendlerUUID)) {
                haendler = entry;
            }
        }
        return haendler;
    }

    /**
     * inserts a new haendler into the heandlerlist
     *
     * @param heandler the heandler to be saved
     */
    public void insertHaendler(Haendler heandler) {
        getHaendlerList().add(heandler);
        writeHaendlerJSON();
    }

    /**
     * updates the bookList
     */
    public void updateBook() {
        writeHaendlerJSON();
    }

    private void writeHaendlerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String haendlerPath = Config.getProperty("haendlerJSON");
        try {
            fileOutputStream = new FileOutputStream(haendlerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getHaendlerList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * deletes a book identified by the bookUUID
     * @param haendlerUUID  the key
     * @return  success=true/false
     */
    public boolean deleteHaendler(String haendlerUUID) {
        Haendler haendler = getInstance().readHaendlerByUUID(haendlerUUID);
        if (haendler != null) {
            getInstance().getHaendlerList().remove(haendler);
            writeHaendlerJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * gets the fahrzeuge von JSON
     */
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

    /**
     * gets the haendler von JSON
     */
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

    /**
     * gets the haendler von JSON
     */
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

    /**
     * gets the haendler von JSON
     */
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

    /**
     * gets all fahrzeuge
     */
    private List<Fahrzeug> getFahrzeugList() {
        return fahrzeugList;
    }

    /**
     * sets all fahrzeuge
     */
    private void setFahrzeugList(List<Fahrzeug> fahrzeugList) {
        this.fahrzeugList = fahrzeugList;
    }

    /**
     * gets kaeufer list
     */
    private List<Kaeufer> getKaeuferList() {
        return kaeuferList;
    }

    /**
     * sets kaeufer list
     */
    private void setKaeuferList(List<Kaeufer> kaeuferList) {
        this.kaeuferList = kaeuferList;
    }

    /**
     * gets merkliste list
     */
    private List<Merkliste> getMerklisteList() {
        return merklisteList;
    }

    /**
     * sets merkliste list
     */
    private void setMerklisteList(List<Merkliste> merklisteList) {
        this.merklisteList = merklisteList;
    }

    /**
     * gets haendler list
     */
    private List<Haendler> getHaendlerList() {
        return haendlerList;
    }

    /**
     * sets haendler list
     */
    private void setHaendlerList(List<Haendler> haendlerList) {
        this.haendlerList = haendlerList;
    }


}