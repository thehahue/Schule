package at.bbrz;

import at.bbrz.schule.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Schule schule = Schule.load(Path.of("src/main/resources/schule.json"));

        //Falsche Stelle -> die Schule ist zuständig für die Schüler
//        List<Schueler> schueler1 = schule.getSchueler();
//        for (Schueler schueler2 : schueler1) {
//            if (schueler2.isSchuelerVertreter()) {
//                System.out.println(schueler2);
//            }
//        }

        System.out.println("Schülervertreter:\n---------------------");

        List<Schueler> schuelerVertreter = schule.findSchuelerVertreter();
        for (Schueler schueler : schuelerVertreter) {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schueler));
        }

        List<Fach> availableSubjects = schule.findAvailableSubjects();
        List<Fach> availableSubjectsFor = schule.findAvailableSubjectsFor();

        System.out.println("Available subjects:");
        for (Fach subject : availableSubjects) {
            System.out.println(subject);
        }

        System.out.println("Available subjects FOR:");
        for (Fach subject : availableSubjectsFor) {
            System.out.println(subject);
        }

        schule.deleteSchueler("Fritzi", "Huber", LocalDate.of(2008,5,12));
        //schule.deleteSchuelerNew("Fritzi", "Huber", LocalDate.of(2008,5,12));

        schule.save(Path.of("src/main/resources/schule2.json"));

        Schule schule2 = Schule.load(Path.of("src/main/resources/schule2.json"));

        System.out.println("Schule und Schule2 gleich? "+schule.equals(schule2));

        Zeugnis erstesZeugnis = new Zeugnis(schule.findSchuelerVertreter().getFirst(),
                "2025/2026", LocalDate.of(2025, 11, 20));
        erstesZeugnis.addEintrag(Fach.DEUTSCH, Note.SEHR_GUT, "War besonders fleißig");
        erstesZeugnis.addEintrag(Fach.MATHE, Note.GUT, null);

        System.out.println(erstesZeugnis);

        schule.addZeugnis(erstesZeugnis);


        System.out.println("ENDE");


    }
}
