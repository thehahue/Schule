package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Schueler.class, name = "SCHUELER"),
        @JsonSubTypes.Type(value = Mitarbeiter.class, name = "MITARBEITER"),
        @JsonSubTypes.Type(value = Lehrer.class, name = "LEHRER"),
        @JsonSubTypes.Type(value = Chef.class, name = "CHEF")
})
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
