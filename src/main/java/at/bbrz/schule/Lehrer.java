package at.bbrz.schule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lehrer extends Mitarbeiter {
    private List<String> feacher;
    private List<Schueler> schueler;

    public Lehrer(String vorname, String nachname, LocalDate geburtsDatum, Adresse adresse, String nummer,
                  double gehalt, boolean betriebsrat) {
        super(vorname, nachname, geburtsDatum, adresse, nummer, gehalt, betriebsrat);
        this.feacher = new ArrayList<>();
        this.schueler = new ArrayList<>();
    }

    public void addSchueler(Schueler schueler) {
        this.schueler.add(schueler);
    }

    public void addFach(String fach) {
        this.feacher.add(fach);
    }

    public List<String> getFeacher() {
        return feacher;
    }

    @Override
    public String toString() {
        return "Lehrer{" +
                "feacher=" + feacher +
                ", schueler=" + schueler +
                "} " + super.toString();
    }
}
