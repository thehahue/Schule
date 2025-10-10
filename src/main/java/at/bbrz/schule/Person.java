package at.bbrz.schule;

import java.time.LocalDate;

public abstract class Person {
    private String vorname;
    private String nachname;
    private LocalDate geburtsDatum;
    private Adresse adresse;

    public Person(String vorname, String nachname, LocalDate geburtsDatum, Adresse adresse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsDatum = geburtsDatum;
        this.adresse = adresse;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public LocalDate getGeburtsDatum() {
        return geburtsDatum;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return "Person{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtsDatum=" + geburtsDatum +
                ", adresse=" + adresse +
                '}';
    }
}
