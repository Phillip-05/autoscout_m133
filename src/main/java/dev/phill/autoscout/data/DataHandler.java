package dev.phill.autoscout.data;



import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.model.Fahrzeug;
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
//    private List<Publisher> publisherList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
//        setPublisherList(new ArrayList<>());
//        readPublisherJSON();
        setFahrzeugList(new ArrayList<>());
        readFahrzeugJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all books
     * @return list of books
     */
    public List<Fahrzeug> readallFahrzeuge() {
        return getFahrzeugList();
    }

    /**
     * reads a book by its uuid
     * @param fahrzeugUUID
     * @return the Book (null=not found)
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

//    /**
//     * reads all Publishers
//     * @return list of publishers
//     */
//    public List<Publisher> readAllPublishers() {
//
//        return getPublisherList();
//    }
//
//    /**
//     * reads a publisher by its uuid
//     * @param publisherUUID
//     * @return the Publisher (null=not found)
//     */
//    public Publisher readPublisherByUUID(String publisherUUID) {
//        Publisher publisher = null;
//        for (Publisher entry : getPublisherList()) {
//            if (entry.getPublisherUUID().equals(publisherUUID)) {
//                publisher = entry;
//            }
//        }
//        return publisher;
//    }

    /**
     * reads the books from the JSON-file
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

//    /**
//     * reads the publishers from the JSON-file
//     */
//    private void readPublisherJSON() {
//        try {
//            byte[] jsonData = Files.readAllBytes(
//                    Paths.get(
//                            Config.getProperty("publisherJSON")
//                    )
//            );
//            ObjectMapper objectMapper = new ObjectMapper();
//            Publisher[] publishers = objectMapper.readValue(jsonData, Publisher[].class);
//            for (Publisher publisher : publishers) {
//                getPublisherList().add(publisher);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    /**
     * gets bookList
     *
     * @return value of bookList
     */
    private List<Fahrzeug> getFahrzeugList() {
        return fahrzeugList;
    }

    /**
     * sets bookList
     *
     * @param fahrzeugList the value to set
     */
    private void setFahrzeugList(List<Fahrzeug> fahrzeugList) {
        this.fahrzeugList = fahrzeugList;
    }

//    /**
//     * gets publisherList
//     *
//     * @return value of publisherList
//     */
//    private List<Publisher> getPublisherList() {
//        return publisherList;
//    }
//
//    /**
//     * sets publisherList
//     *
//     * @param publisherList the value to set
//     */
//    private void setPublisherList(List<Publisher> publisherList) {
//        this.publisherList = publisherList;
//    }


}