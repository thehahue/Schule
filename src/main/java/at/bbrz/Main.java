package at.bbrz;

import at.bbrz.schule.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        System.out.println("--------------------------------------------------------------------------------");

//        List<Schueler> schuelerListe = schule.getSchueler();
//
//        for (Schueler schueler : schuelerListe) {
//
//        }

        List<Zeugnis> exampleZeugnisse = ZeugnisFactory.createExampleZeugnisse(schule, "2025/2026");
        schule.addZeugnisse(exampleZeugnisse);



        System.out.println("--------------------------------------------------------------------------------");
        Klassenzimmer k_1A = new Klassenzimmer("1A");
        schule.addKlassenzimmer(k_1A);

        k_1A.addSchueler(schuelerVertreter.get(0));
        k_1A.setKlassenvorstand(new Lehrer("Stefan", "KeineAhnung", LocalDate.of(1980, 11,1), new Adresse("asfd","1","2222","Dort", Land.DEUTSCHLAND), "1", 2222, false));

        addExampleKlassen(schule);

        SchuleAsciiPrinter.print(schule);
        System.out.println("ENDE");


    }

    private static void addExampleKlassen(Schule schule) {
        Objects.requireNonNull(schule, "Schule darf nicht null sein");

        Lehrer willi = findLehrer(schule, "Willswissen");
        Lehrer sabine = findLehrer(schule, "Stark");

        if (willi != null) {
            List<Schueler> klasse1AList = new ArrayList<>();
            addIfNotNull(klasse1AList, findSchueler(schule, "Anna", "Meier"));
            addIfNotNull(klasse1AList, findSchueler(schule, "Paul", "König"));
            Klassenzimmer klasse1A = new Klassenzimmer("1A", willi, klasse1AList);
            schule.addKlassenzimmer(klasse1A);
        }

        if (sabine != null) {
            List<Schueler> klasse1BList = new ArrayList<>();
            addIfNotNull(klasse1BList, findSchueler(schule, "Fritzi", "Huber"));
            addIfNotNull(klasse1BList, findSchueler(schule, "Lena", "Bauer"));
            addIfNotNull(klasse1BList, findSchueler(schule, "Miriam", "Schmidt"));
            Klassenzimmer klasse1B = new Klassenzimmer("1B", sabine, klasse1BList);
            schule.addKlassenzimmer(klasse1B);
        }
    }

    private static Lehrer findLehrer(Schule schule, String nachname) {
        return schule.getMitarbeiter().stream()
                .filter(Lehrer.class::isInstance)
                .map(Lehrer.class::cast)
                .filter(l -> l.getNachname().equalsIgnoreCase(nachname))
                .findFirst()
                .orElse(null);
    }

    private static Schueler findSchueler(Schule schule, String vorname, String nachname) {
        return schule.getSchueler().stream()
                .filter(s -> s.getVorname().equalsIgnoreCase(vorname))
                .filter(s -> s.getNachname().equalsIgnoreCase(nachname))
                .findFirst()
                .orElse(null);
    }

    private static void addIfNotNull(List<Schueler> target, Schueler schueler) {
        if (schueler != null) {
            target.add(schueler);
        }
    }
}
