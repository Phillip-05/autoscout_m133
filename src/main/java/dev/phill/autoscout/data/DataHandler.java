package dev.phill.autoscout.data;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.phill.autoscout.model.*;
import dev.phill.autoscout.service.Config;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This DataHandler contains all functions that are needed for writing and reading jsons
 */

@Getter
@Setter
public class DataHandler {
    private static List<Vehicle> vehicleList;
    private static List<Dealer> dealerList;
    private static List<Buyer> buyerList;
    private static List<Watchlist> watchlistList;
    private static List<User> userList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {

    }

    static {
        setUserList(new ArrayList<>());
        setBuyerList(new ArrayList<>());
        setVehicleList(new ArrayList<>());
        setDealerList(new ArrayList<>());
        setWatchlistList(new ArrayList<>());
        readUserJSON();
        readDealerJSON();
        readVehicleJSON();
        readWatchlistJSON();
        readBuyerJSON();

    }

    public static void resetAllLists() {
        setUserList(new ArrayList<>());
        setBuyerList(new ArrayList<>());
        setVehicleList(new ArrayList<>());
        setDealerList(new ArrayList<>());
        setWatchlistList(new ArrayList<>());
        readUserJSON();
        readDealerJSON();
        readVehicleJSON();
        readWatchlistJSON();
        readBuyerJSON();

    }

    /**
     * reads all Vehicle
     * @return whole vehicle list
     */
    public static List<Vehicle> readallVehicle() {
        return getVehicleList();
    }

    /**
     * reads a Vehicle by its uuid
     * @param vehicleUUID uuid of the vehicle
     * @return the found vehicle
     */
    public static Vehicle readVehicleByUUID(String vehicleUUID) {
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
    public static void insertVehicle(Vehicle vehicle) {
        getVehicleList().add(vehicle);
        writeVehicleJSON();
    }

    /**
     * updates the bookList
     */
    public static void updateVehicle() {
        writeVehicleJSON();
    }

    private static void writeVehicleJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String vehiclePath = Config.getProperty("vehicleJSON");
        try {
            fileOutputStream = new FileOutputStream(vehiclePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getVehicleList());
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
    public static boolean deleteVehicle(String vehicleUUID) {
        Vehicle vehicle = readVehicleByUUID(vehicleUUID);
        if (vehicle != null) {
            getVehicleList().remove(vehicle);
            writeVehicleJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all Dealer
     * @return whole buyer list
     */
    public static List<Buyer> readallBuyer() {
        return getBuyerList();
    }


    /**
     * reads a Buyer by its uuid
     * @param buyerUUID buyer key
     * @return the found buyer
     */
    public static Buyer readBuyerByUUID(String buyerUUID) {
        Buyer buyer = null;
        for (Buyer entry : getBuyerList()) {
            if (entry.getBuyerUUID().equals(buyerUUID)) {
                buyer = entry;
            }
        }
        return buyer;
    }

    /**
     * inserts a new buyer into the buyerlist
     *
     * @param buyer the buyer to be saved
     */
    public static void insertBuyer(Buyer buyer) {
        getBuyerList().add(buyer);
        writeBuyerJSON();
    }

    /**
     * updates the bookList
     */
    public static void updateBuyer() {
        writeBuyerJSON();
    }

    private static void writeBuyerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String buyerPath = Config.getProperty("buyerJSON");
        try {
            fileOutputStream = new FileOutputStream(buyerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getBuyerList());
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
    public static boolean deleteBuyer(String buyerUUID) {
        Buyer buyer = readBuyerByUUID(buyerUUID);
        if (buyer != null) {
            getBuyerList().remove(buyer);
            writeBuyerJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all dealer
     * @return gets whole watchlistlist
     */
    public static List<Watchlist> readallWatchlist() {
        return getWatchlistList();
    }

    /**
     * reads a Watchlist by its uuid
     * @param watchlistUUID watchlist key
     * @return the found watchlist
     */
    public static Watchlist readWatchlistByUUID(String watchlistUUID) {
        Watchlist watchlist = null;
        for (Watchlist entry : getWatchlistList()) {
            if (entry.getWatchlistUUID().equals(watchlistUUID)) {
                watchlist = entry;
            }
        }
        return watchlist;
    }

    /**
     * inserts a new watchlist into the watchlistlist
     *
     * @param watchlist the watchlist to be saved
     */
    public static void insertWatchlist(Watchlist watchlist) {
        getWatchlistList().add(watchlist);
        writeWatchlistJSON();
    }

    /**
     * updates the watchlistlist
     */
    public static void updateWatchlist() {
        writeWatchlistJSON();
    }

    /**
     * writes into watchlist json
     */
    private static void writeWatchlistJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String watchlistPath = Config.getProperty("watchlistJSON");
        try {
            fileOutputStream = new FileOutputStream(watchlistPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getWatchlistList());
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
    public static boolean deleteWatchlist(String watchlistUUID) {
        Watchlist watchlist = readWatchlistByUUID(watchlistUUID);
        if (watchlist != null) {
            getWatchlistList().remove(watchlist);
            writeWatchlistJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all dealer
     * @return whole dealerlist
     */
    public static List<Dealer> readAllDealers() {

        return getDealerList();
    }

    /**
     * reads a Dealer by its uuid
     * @param dealerUUID dealer key
     * @return the found dealer
     */
    public static Dealer readDealerByUUID(String dealerUUID) {
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
    public static void insertDealer(Dealer dealer) {
        getDealerList().add(dealer);
        writeDealerJSON();
    }

    /**
     * updates the bookList
     */
    public static void updateDealer() {
        writeDealerJSON();
    }

    /**
     * writes into dealer json
     */
    private static void writeDealerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String dealerPath = Config.getProperty("dealerJSON");
        try {
            fileOutputStream = new FileOutputStream(dealerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getDealerList());
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
    public static boolean deleteDealer(String dealerUUID) {
        Dealer dealer = readDealerByUUID(dealerUUID);
        if (dealer != null) {
            getDealerList().remove(dealer);
            writeDealerJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * gets the Vehicle von JSON
     */
    private static void readVehicleJSON() {
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
    private static void readBuyerJSON() {
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
    private static void readWatchlistJSON() {
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
    private static void readDealerJSON() {
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
    private static List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * sets all Vehicle
     */
    private static void setVehicleList(List<Vehicle> vehicleList) {
        DataHandler.vehicleList = vehicleList;
    }

    /**
     * gets Buyer list
     */
    private static List<Buyer> getBuyerList() {
        return buyerList;
    }

    /**
     * sets buyer list
     */
    private static void setBuyerList(List<Buyer> buyerList) {
        DataHandler.buyerList = buyerList;
    }

    /**
     * gets Watchlist list
     */
    private static List<Watchlist> getWatchlistList() {
        return watchlistList;
    }

    /**
     * sets Watchlist list
     */
    private static void setWatchlistList(List<Watchlist> watchlistList) {
        DataHandler.watchlistList = watchlistList;
    }

    /**
     * gets Dealer list
     */
    private static List<Dealer> getDealerList() {
        return dealerList;
    }

    /**
     * sets Dealer list
     */
    private static void setDealerList(List<Dealer> dealerList) {
        DataHandler.dealerList = dealerList;
    }

    public static String readUserRole(String username, String password) {
        for (User user : getUserList()) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return "guest";
    }

    /**
     * reads the users from the JSON-file
     */
    private static void readUserJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("userJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets userList
     *
     * @return value of userList
     */

    public static List<User> getUserList() {
        if (DataHandler.userList == null) {
            DataHandler.setUserList(new ArrayList<>());
            readUserJSON();
        }
        return userList;
    }

    /**
     * reads a user by the username/password provided
     *
     * @param username
     * @param password
     * @return user-object
     */
    public static User readUser(String username, String password) {
        User user = new User();
        for (User entry : getUserList()) {
            if (entry.getUsername().equals(username) &&
                    entry.getPassword().equals(password)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * sets userList
     *
     * @param userList the value to set
     */

    public static void setUserList(List<User> userList) {
        DataHandler.userList = userList;
    }


}