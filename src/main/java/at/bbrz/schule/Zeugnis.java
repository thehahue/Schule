package at.bbrz.schule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Zeugnis {
    private final Schueler schueler;
    private final String schuljahr;
    private final LocalDate ausstellungsDatum;
    private final List<ZeugnisEintrag> eintraege;

    public Zeugnis(Schueler schueler, String schuljahr, LocalDate ausstellungsDatum) {
        this.schueler = Objects.requireNonNull(schueler, "Schüler darf nicht null sein");
        this.schuljahr = Objects.requireNonNull(schuljahr, "Schuljahr darf nicht null sein");
        this.ausstellungsDatum = Objects.requireNonNull(ausstellungsDatum, "Ausstellungsdatum darf nicht null sein");
        this.eintraege = new ArrayList<>();
    }

    public Schueler getSchueler() {
        return schueler;
    }

    public String getSchuljahr() {
        return schuljahr;
    }

    public LocalDate getAusstellungsDatum() {
        return ausstellungsDatum;
    }

    public List<ZeugnisEintrag> getEintraege() {
        // Hilfsmethode die eine Liste liefert, wo die Einträge nicht änderbar sind
        return Collections.unmodifiableList(eintraege);
    }

    public void addEintrag(ZeugnisEintrag eintrag) {
        Objects.requireNonNull(eintrag, "Zeugniseintrag darf nicht null sein");

        for (ZeugnisEintrag zeugnisEintrag : eintraege) {
            if (zeugnisEintrag.getFach() == eintrag.getFach()) {
                throw new IllegalArgumentException("Fach " + eintrag.getFach()
                        + " wurde bereits eingetragen");
            }
        }

        this.eintraege.add(eintrag);
    }

    public void addEintrag(Fach fach, Note note, String kommentar) {
        addEintrag(new ZeugnisEintrag(fach, note, kommentar));
    }

    public void removeEintrag(Fach fach) {
        eintraege.removeIf(e -> e.getFach() == fach);
    }

    public Optional<ZeugnisEintrag> findeEintrag(Fach fach) {
        return eintraege.stream()
                .filter(e -> e.getFach() == fach)
                .findFirst();
    }

    public double berechneNotenschnitt() {
        if (eintraege.isEmpty()) {
            return 0.0;
        }
        int summe = eintraege.stream()
                .mapToInt(e -> e.getNote().getWert())
                .sum();

        return Math.floor(((double) summe / eintraege.size() * 100)) / 100;
    }

    @Override
    public String toString() {
        return "Zeugnis{" +
                "schueler=" + schueler +
                ", schuljahr='" + schuljahr + '\'' +
                ", ausstellungsDatum=" + ausstellungsDatum +
                ", eintraege=" + eintraege +
                '}';
    }
}
