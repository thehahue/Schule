package at.bbrz.schule;

import java.time.LocalDate;

public class Mitarbeiter extends Person {
    private String nummer;
    private double gehalt;
    private boolean betriebsrat;

    public Mitarbeiter(String vorname, String nachname, LocalDate geburtsDatum, Adresse adresse, String nummer, double gehalt, boolean betriebsrat) {
        super(vorname, nachname, geburtsDatum, adresse);
        this.nummer = nummer;
        this.gehalt = gehalt;
        this.betriebsrat = betriebsrat;
    }

    public String getNummer() {
        return nummer;
    }

    public double getGehalt() {
        return gehalt;
    }

    public boolean isBetriebsrat() {
        return betriebsrat;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "nummer='" + nummer + '\'' +
                ", gehalt=" + gehalt +
                ", betriebsrat=" + betriebsrat +
                "} " + super.toString();
    }
}
