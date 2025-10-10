package at.bbrz.schule;

import java.time.LocalDate;

public class Schueler extends Person {
    private boolean schuelerVertreter;

    public Schueler(String vorname, String nachname, LocalDate geburtsDatum, Adresse adresse, boolean schuelerVertreter) {
        super(vorname, nachname, geburtsDatum, adresse);
        this.schuelerVertreter = schuelerVertreter;
    }

    public boolean isSchuelerVertreter() {
        return schuelerVertreter;
    }

    @Override
    public String toString() {
        return "Schueler{" +
                "schuelerVertreter=" + schuelerVertreter +
                "} " + super.toString();
    }
}
