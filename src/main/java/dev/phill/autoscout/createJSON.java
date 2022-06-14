package dev.phill.autoscout;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.phill.autoscout.model.Fahrzeug;
import dev.phill.autoscout.model.Haendler;
import dev.phill.autoscout.model.Kaeufer;
import dev.phill.autoscout.model.Merkliste;

import java.io.IOException;
import java.nio.file.Paths;

import java.util.UUID;
import java.util.Vector;

public class createJSON {


    public static void main(String[] args) {


        Haendler[] haendlers = {
                new Haendler("d3cbe97e-9c39-4df3-8097-4f4fb45a5e9d", "franz", "meier", "bachweg"),
                new Haendler("08b6dfc2-004e-4e0b-9c41-a1ba8665aa48", "hans", "mueller", "strasse")
        };

        Fahrzeug[] fahrzeuge = {
                new Fahrzeug("e2cfaa52-83e5-4c5c-b32a-abbf03c3c151", "Porsche", "911 Sport Classic", 338900f, 2022, 3700f, haendlers[0], 550f, false, true, 1645f),
                new Fahrzeug("bc8907ed-298d-4065-befb-84af4886e718", "Porsche", "911 Sport Classic", 300000f, 2012, 3700f, haendlers[1], 550f, false, true, 1645f)
        };

        Vector<Fahrzeug> fahrzeugVector = new Vector<>();
        fahrzeugVector.add(fahrzeuge[0]);
        fahrzeugVector.add(fahrzeuge[1]);
        Merkliste[] merklistes = {
                new Merkliste("4a0139a1-1773-41a9-9de4-3a8fa3d28e54",fahrzeugVector, "Merkliste 1"),
                new Merkliste("ac1db514-4f8a-45a7-902a-d09be120ed3f",fahrzeugVector, "Merkliste 2")
        };

        Kaeufer[] kaeufers = {
                new Kaeufer("62c74fbd-d324-4d03-8f1e-577836250da2", 2022, merklistes[0]),
                new Kaeufer("7b1af934-fc46-4da4-b500-bc73b411a51a", 2022, merklistes[1])
        };

        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(Paths.get("C:/Github/autoscout_m133/testing/fahrzeug.json").toFile(), fahrzeuge);
            om.writeValue(Paths.get("C:/Github/autoscout_m133/testing/haendler.json").toFile(), haendlers);
            om.writeValue(Paths.get("C:/Github/autoscout_m133/testing/merkliste.json").toFile(), merklistes);
            om.writeValue(Paths.get("C:/Github/autoscout_m133/testing/kaeufer.json").toFile(), kaeufers);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






