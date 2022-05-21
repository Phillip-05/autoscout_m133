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
        Fahrzeug[] fahrzeuge = {
                new Fahrzeug(UUID.randomUUID().toString(), "Porsche", "911 Sport Classic", 338900f, 2022, 3700f, new Haendler(UUID.randomUUID().toString(), "franz", "meier", "bachweg"), 550f, false, true, 1645f),
                new Fahrzeug(UUID.randomUUID().toString(), "Porsche", "911 Sport Classic", 300000f, 2012, 3700f, new Haendler(UUID.randomUUID().toString(), "hans", "mueller", "strasse"), 550f, false, true, 1645f)
        };

        Haendler[] haendlers = {
                new Haendler(UUID.randomUUID().toString(), "franz", "meier", "bachweg"),
                new Haendler(UUID.randomUUID().toString(), "hans", "mueller", "strasse")
        };

        Vector<Fahrzeug> fahrzeugVector = new Vector<>();
        fahrzeugVector.add(fahrzeuge[0]);
        fahrzeugVector.add(fahrzeuge[1]);
        Merkliste[] merklistes = {
                new Merkliste(UUID.randomUUID().toString(),fahrzeugVector, "Merkliste 1"),
                new Merkliste(UUID.randomUUID().toString(),fahrzeugVector, "Merkliste 2")
        };

        Kaeufer[] kaeufers = {
                new Kaeufer(UUID.randomUUID().toString(), 2022, merklistes[0]),
                new Kaeufer(UUID.randomUUID().toString(), 2022, merklistes[1])
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






