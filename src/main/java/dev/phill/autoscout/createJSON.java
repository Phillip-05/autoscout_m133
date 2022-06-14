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
                new Haendler("eb37b55e-a2a0-4f0e-9de7-81ce506aab6f", "franz", "meier", "bachweg"),
                new Haendler("496483fb-c64a-43ee-9b09-c7429777fef6", "hans", "mueller", "strasse")
        };

        Fahrzeug[] fahrzeuge = {
                new Fahrzeug("a1d0a3b1-c0fa-4a3c-a3d5-5cf0c0fa6080", "Porsche", "911 Sport Classic", 338900f, 2022, 3700f, haendlers[0], 550f, false, true, 1645f),
                new Fahrzeug("143c3cbc-7c1f-41b8-b52c-11f00f9627ab", "Porsche", "911 Sport Classic", 300000f, 2012, 3700f, haendlers[1], 550f, false, true, 1645f)
        };

        Vector<Fahrzeug> fahrzeugVector = new Vector<>();
        fahrzeugVector.add(fahrzeuge[0]);
        fahrzeugVector.add(fahrzeuge[1]);
        Merkliste[] merklistes = {
                new Merkliste("6f5ed8a4-33f2-424c-afca-8a76cf7a9fac",fahrzeugVector, "Merkliste 1"),
                new Merkliste("9944014b-073f-431e-a3c3-f7979af8fa52",fahrzeugVector, "Merkliste 2")
        };

        Kaeufer[] kaeufers = {
                new Kaeufer("4cd341d9-f457-4c99-a42a-634f81efaa6e", 2022, merklistes[0]),
                new Kaeufer("9330cc53-358c-4eba-ad47-92ef29f59d08", 2022, merklistes[1])
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






