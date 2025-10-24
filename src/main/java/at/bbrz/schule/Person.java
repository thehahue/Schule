package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("adresse")
    public Adresse getAdresse() {
        return adresse;
    }

    @JsonProperty("vorname")
    public String getVorname() {
        return vorname;
    }

    @JsonProperty("nachname")
    public String getNachname() {
        return nachname;
    }

    @JsonProperty("geburtsDatum")
    public LocalDate getGeburtsDatum() {
        return geburtsDatum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return java.util.Objects.equals(vorname, person.vorname)
                && java.util.Objects.equals(nachname, person.nachname)
                && java.util.Objects.equals(geburtsDatum, person.geburtsDatum);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(vorname, nachname, geburtsDatum);
    }
}
