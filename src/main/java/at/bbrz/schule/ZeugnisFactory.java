package at.bbrz.schule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public final class ZeugnisFactory {

    private static final List<String> KOMMENTARE = List.of(
            "Sehr engagiert, arbeitet konzentriert mit.",
            "Gute Leistungen, gelegentlich etwas unkonzentriert.",
            "Zeigt stetige Fortschritte.",
            "Sollte sich stärker beteiligen.",
            "Braucht noch Unterstützung bei den Hausaufgaben."
    );

    private ZeugnisFactory() {
    }

    public static List<Zeugnis> createExampleZeugnisse(Schule schule, String schuljahr) {
        return createExampleZeugnisse(schule, schuljahr, LocalDate.now());
    }

    private static List<Zeugnis> createExampleZeugnisse(Schule schule, String schuljahr, LocalDate ausstellungsDatum) {
        Objects.requireNonNull(schule, "Schule darf nicht null sein");
        Objects.requireNonNull(schuljahr, "Schuljahr darf nicht null sein");
        Objects.requireNonNull(ausstellungsDatum, "Ausstellungsdatum darf nicht null sein");

        List<Schueler> schuelerListe = schule.getSchueler();
        if (schuelerListe.isEmpty()) {
            return List.of();
        }

        List<Fach> subjects = schule.findAvailableSubjects();
        if (subjects.isEmpty()) {
            subjects = List.of(Fach.values());
        }

        List<Zeugnis> result = new ArrayList<>();
        for (Schueler schueler : schuelerListe) {
            result.add(createExampleZeugnis(schueler, schuljahr, ausstellungsDatum, subjects));
        }

        return result;
    }

    public static Zeugnis createExampleZeugnis(Schule schule, Schueler schueler, String schuljahr) {
        return createExampleZeugnis(schule, schueler, schuljahr, LocalDate.now());
    }

    public static Zeugnis createExampleZeugnis(Schule schule, Schueler schueler, String schuljahr, LocalDate ausstellungsDatum) {
        Objects.requireNonNull(schule, "Schule darf nicht null sein");
        Objects.requireNonNull(schueler, "Schüler darf nicht null sein");
        Objects.requireNonNull(schuljahr, "Schuljahr darf nicht null sein");
        Objects.requireNonNull(ausstellungsDatum, "Ausstellungsdatum darf nicht null sein");

        List<Fach> subjects = schule.findAvailableSubjects();
        if (subjects.isEmpty()) {
            subjects = List.of(Fach.values());
        }
        return createExampleZeugnis(schueler, schuljahr, ausstellungsDatum, subjects);
    }

    private static Zeugnis createExampleZeugnis(Schueler schueler, String schuljahr,
                                                LocalDate ausstellungsDatum, List<Fach> subjects) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Zeugnis zeugnis = new Zeugnis(schueler, schuljahr, ausstellungsDatum);
        for (Fach fach : subjects) {
            zeugnis.addEintrag(fach, randomNote(random), randomKommentar(random));
        }
        return zeugnis;
    }

    private static Note randomNote(ThreadLocalRandom random) {
        Note[] values = Note.values();
        return values[random.nextInt(values.length)];
    }

    private static String randomKommentar(ThreadLocalRandom random) {
        return KOMMENTARE.get(random.nextInt(KOMMENTARE.size()));
    }
}
