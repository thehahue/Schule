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
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (Schueler schueler : schuelerListe) {
            Zeugnis zeugnis = new Zeugnis(schueler, schuljahr, ausstellungsDatum);
            for (Fach fach : subjects) {
                zeugnis.addEintrag(fach, randomNote(random), randomKommentar(random));
            }
            result.add(zeugnis);
        }

        return result;
    }

    private static Note randomNote(ThreadLocalRandom random) {
        Note[] values = Note.values();
        return values[random.nextInt(values.length)];
    }

    private static String randomKommentar(ThreadLocalRandom random) {
        return KOMMENTARE.get(random.nextInt(KOMMENTARE.size()));
    }
}
