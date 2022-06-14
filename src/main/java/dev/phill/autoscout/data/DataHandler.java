package dev.phill.autoscout.data;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.phill.autoscout.model.Buyer;
import dev.phill.autoscout.model.Dealer;
import dev.phill.autoscout.model.Vehicle;
import dev.phill.autoscout.model.Watchlist;
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
    private List<Vehicle> vehicleList;
    private List<Dealer> dealerList;
    private List<Buyer> buyerList;
    private List<Watchlist> watchlistList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setDealerList(new ArrayList<>());
        readDealerJSON();
        setVehicleList(new ArrayList<>());
        readVehicleJSON();
        setBuyerList(new ArrayList<>());
        readBuyerJSON();
        setWatchlistList(new ArrayList<>());
        readWatchlistJSON();
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
     * reads all Vehicle
     */
    public List<Vehicle> readallVehicle() {
        return getVehicleList();
    }

    /**
     * reads a Vehicle by its uuid
     */
    public Vehicle readVehicleByUUID(String vehicleUUID) {
        Vehicle vehicle = null;
        for (Vehicle entry : getVehicleList()) {
            if (entry.getVehicleUUID().equals(vehicleUUID)) {
                vehicle = entry;
            }
        }
        return vehicle;
    }

    /**
     * inserts a new Vehicle into the Vehiclelist
     *
     * @param vehicle the vehicle to be saved
     */
    public void insertVehicle(Vehicle vehicle) {
        getVehicleList().add(vehicle);
        writeVehicleJSON();
    }

    /**
     * updates the bookList
     */
    public void updateVehicle() {
        writeVehicleJSON();
    }

    private void writeVehicleJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String vehiclePath = Config.getProperty("vehicleJSON");
        try {
            fileOutputStream = new FileOutputStream(vehiclePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getVehicleList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * deletes a Vehicle identified by the VehicleUUID
     *
     * @param vehicleUUID the key
     * @return success=true/false
     */
    public boolean deleteVehicle(String vehicleUUID) {
        Vehicle vehicle = getInstance().readVehicleByUUID(vehicleUUID);
        if (vehicle != null) {
            getInstance().getVehicleList().remove(vehicle);
            writeVehicleJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all Dealer
     */
    public List<Buyer> readallBuyer() {
        return getBuyerList();
    }


    /**
     * reads a Buyer by its uuid
     */
    public Buyer readBuyerByUUID(String buyerUUID) {
        Buyer buyer = null;
        for (Buyer entry : getBuyerList()) {
            if (entry.getBuyerUUID().equals(buyerUUID)) {
                buyer = entry;
            }
        }
        return buyer;
    }

    /**
     * inserts a new dealer into the heandlerlist
     *
     * @param buyer the dealer to be saved
     */
    public void insertBuyer(Buyer buyer) {
        getBuyerList().add(buyer);
        writeBuyerJSON();
    }

    /**
     * updates the bookList
     */
    public void updateBuyer() {
        writeBuyerJSON();
    }

    private void writeBuyerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String buyerPath = Config.getProperty("buyerJSON");
        try {
            fileOutputStream = new FileOutputStream(buyerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getBuyerList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * deletes a Buyer identified by the buyerUUID
     *
     * @param buyerUUID the key
     * @return success=true/false
     */
    public boolean deleteBuyer(String buyerUUID) {
        Buyer buyer = getInstance().readBuyerByUUID(buyerUUID);
        if (buyer != null) {
            getInstance().getBuyerList().remove(buyer);
            writeBuyerJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all dealer
     */
    public List<Watchlist> readallWatchlist() {
        return getWatchlistList();
    }

    /**
     * reads a Watchlist by its uuid
     */
    public Watchlist readWatchlistByUUID(String watchlistUUID) {
        Watchlist watchlist = null;
        for (Watchlist entry : getWatchlistList()) {
            if (entry.getWatchlistUUID().equals(watchlistUUID)) {
                watchlist = entry;
            }
        }
        return watchlist;
    }

    /**
     * inserts a new dealer into the heandlerlist
     *
     * @param watchlist the heandler to be saved
     */
    public void insertWatchlist(Watchlist watchlist) {
        getWatchlistList().add(watchlist);
        writeWatchlistJSON();
    }

    /**
     * updates the bookList
     */
    public void updateWatchlist() {
        writeWatchlistJSON();
    }

    private void writeWatchlistJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String watchlistPath = Config.getProperty("watchlistJSON");
        try {
            fileOutputStream = new FileOutputStream(watchlistPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getWatchlistList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * deletes a Watchlist identified by the WatchlistUUID
     *
     * @param watchlistUUID the key
     * @return success=true/false
     */
    public boolean deleteWatchlist(String watchlistUUID) {
        Watchlist watchlist = getInstance().readWatchlistByUUID(watchlistUUID);
        if (watchlist != null) {
            getInstance().getWatchlistList().remove(watchlist);
            writeWatchlistJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all dealer
     */
    public List<Dealer> readAllDealers() {

        return getDealerList();
    }

    /**
     * reads a Dealer by its uuid
     */
    public Dealer readDealerByUUID(String dealerUUID) {
        Dealer dealer = null;
        for (Dealer entry : getDealerList()) {
            if (entry.getDealerUUID().equals(dealerUUID)) {
                dealer = entry;
            }
        }
        return dealer;
    }

    /**
     * inserts a new dealer into the heandlerlist
     *
     * @param dealer the heandler to be saved
     */
    public void insertDealer(Dealer dealer) {
        getDealerList().add(dealer);
        writeDealerJSON();
    }

    /**
     * updates the bookList
     */
    public void updateDealer() {
        writeDealerJSON();
    }

    private void writeDealerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String dealerPath = Config.getProperty("dealerJSON");
        try {
            fileOutputStream = new FileOutputStream(dealerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getDealerList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * deletes a book identified by the bookUUID
     *
     * @param dealerUUID the key
     * @return success=true/false
     */
    public boolean deleteDealer(String dealerUUID) {
        Dealer dealer = getInstance().readDealerByUUID(dealerUUID);
        if (dealer != null) {
            getInstance().getDealerList().remove(dealer);
            writeDealerJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * gets the Vehicle von JSON
     */
    private void readVehicleJSON() {
        try {
            String path = Config.getProperty("vehicleJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Vehicle[] vehicles = objectMapper.readValue(jsonData, Vehicle[].class);
            for (Vehicle vehicle : vehicles) {
                getVehicleList().add(vehicle);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets the healer von JSON
     */
    private void readBuyerJSON() {
        try {
            String path = Config.getProperty("buyerJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Buyer[] buyers = objectMapper.readValue(jsonData, Buyer[].class);
            for (Buyer buyer : buyers) {
                getBuyerList().add(buyer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets the dealer von JSON
     */
    private void readWatchlistJSON() {
        try {
            String path = Config.getProperty("watchlistJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Watchlist[] watchlists = objectMapper.readValue(jsonData, Watchlist[].class);
            for (Watchlist watchlist : watchlists) {
                getWatchlistList().add(watchlist);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets the dealer von JSON
     */
    private void readDealerJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("dealerJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Dealer[] dealers = objectMapper.readValue(jsonData, Dealer[].class);
            for (Dealer dealer : dealers) {
                getDealerList().add(dealer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets all Vehicle
     */
    private List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * sets all Vehicle
     */
    private void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    /**
     * gets Buyer list
     */
    private List<Buyer> getBuyerList() {
        return buyerList;
    }

    /**
     * sets buyer list
     */
    private void setBuyerList(List<Buyer> buyerList) {
        this.buyerList = buyerList;
    }

    /**
     * gets Watchlist list
     */
    private List<Watchlist> getWatchlistList() {
        return watchlistList;
    }

    /**
     * sets Watchlist list
     */
    private void setWatchlistList(List<Watchlist> watchlistList) {
        this.watchlistList = watchlistList;
    }

    /**
     * gets Dealer list
     */
    private List<Dealer> getDealerList() {
        return dealerList;
    }

    /**
     * sets Dealer list
     */
    private void setDealerList(List<Dealer> dealerList) {
        this.dealerList = dealerList;
    }


}