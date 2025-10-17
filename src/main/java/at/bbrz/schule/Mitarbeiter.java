package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("MITARBEITER")
public class Mitarbeiter extends Person {
    private String nummer;
    private double gehalt;
    private boolean betriebsrat;

    @JsonCreator
    public Mitarbeiter(@JsonProperty("vorname") String vorname,
                       @JsonProperty("nachname") String nachname,
                       @JsonProperty("geburtsDatum") LocalDate geburtsDatum,
                       @JsonProperty("adresse") Adresse adresse,
                       @JsonProperty("nummer") String nummer,
                       @JsonProperty("gehalt") double gehalt,
                       @JsonProperty("betriebsrat") boolean betriebsrat) {
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
