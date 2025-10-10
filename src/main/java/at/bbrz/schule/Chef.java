package at.bbrz.schule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Chef extends Lehrer {
    private List<Mitarbeiter> mitarbeiter;

    public Chef(String vorname, String nachname, LocalDate geburtsDatum, Adresse adresse, String nummer, double gehalt) {
        super(vorname, nachname, geburtsDatum, adresse, nummer, gehalt, false);
        this.mitarbeiter = new ArrayList<>();
    }

    public void addMitarbeiter(Mitarbeiter mitarbeiter) {
        //Zirkelbezug verhindern
        if (mitarbeiter == this) {
            throw new IllegalArgumentException("Cannot be by employee");
        }
        this.mitarbeiter.add(mitarbeiter);
    }

    public List<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    @Override
    public String toString() {
        return "Chef{" +
                "mitarbeiter=" + mitarbeiter +
                "} " + super.toString();
    }
}
