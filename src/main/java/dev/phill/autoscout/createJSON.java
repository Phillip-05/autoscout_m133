package dev.phill.autoscout;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.model.Dealer;
import dev.phill.autoscout.model.Vehicle;
import dev.phill.autoscout.model.Buyer;
import dev.phill.autoscout.model.Watchlist;

import java.io.IOException;
import java.nio.file.Paths;

import java.util.Vector;

public class createJSON {


    public static void main(String[] args) {


        Dealer[] dealers = {
                new Dealer("d3cbe97e-9c39-4df3-8097-4f4fb45a5e9d", "franz", "meier", "bachweg"),
                new Dealer("08b6dfc2-004e-4e0b-9c41-a1ba8665aa48", "hans", "mueller", "strasse")
        };

        Vehicle[] fahrzeuge = {
                new Vehicle("e2cfaa52-83e5-4c5c-b32a-abbf03c3c151", "Porsche", "911 Sport Classic", 338900f, 2022, 3700f, dealers[0], 550f, false, true, 1645f,"333"),
                new Vehicle("bc8907ed-298d-4065-befb-84af4886e718", "Porsche", "911 Sport Classic", 300000f, 2012, 3700f, dealers[1], 550f, false, true, 1645f,"444")
        };

        Vector<Vehicle> vehicleVector = new Vector<>();
        vehicleVector.add(fahrzeuge[0]);
        vehicleVector.add(fahrzeuge[1]);
        Watchlist[] watchlists = {
                new Watchlist("4a0139a1-1773-41a9-9de4-3a8fa3d28e54", vehicleVector, "Merkliste 1"),
                new Watchlist("ac1db514-4f8a-45a7-902a-d09be120ed3f", vehicleVector, "Merkliste 2")
        };

        Buyer[] buyers = {
                new Buyer("62c74fbd-d324-4d03-8f1e-577836250da2", 2022, watchlists[0]),
                new Buyer("7b1af934-fc46-4da4-b500-bc73b411a51a", 2022, watchlists[1])
        };

        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(Paths.get("C:/Github/autoscout_m133/testing/vehicle.json").toFile(), fahrzeuge);
            om.writeValue(Paths.get("C:/Github/autoscout_m133/testing/dealer.json").toFile(), dealers);
            om.writeValue(Paths.get("C:/Github/autoscout_m133/testing/watchlist.json").toFile(), watchlists);
            om.writeValue(Paths.get("C:/Github/autoscout_m133/testing/buyer.json").toFile(), buyers);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






