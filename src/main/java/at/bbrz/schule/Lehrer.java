package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("LEHRER")
public class Lehrer extends Mitarbeiter {
    @JsonProperty("faecher")
    private List<Fach> feacher;
    private List<Schueler> schueler;

    @JsonCreator
    public Lehrer(@JsonProperty("vorname") String vorname,
                  @JsonProperty("nachname") String nachname,
                  @JsonProperty("geburtsDatum") LocalDate geburtsDatum,
                  @JsonProperty("adresse") Adresse adresse,
                  @JsonProperty("nummer") String nummer,
                  @JsonProperty("gehalt") double gehalt,
                  @JsonProperty("betriebsrat") boolean betriebsrat) {
        super(vorname, nachname, geburtsDatum, adresse, nummer, gehalt, betriebsrat);
        this.feacher = new ArrayList<>();
        this.schueler = new ArrayList<>();
    }

    public void addSchueler(Schueler schueler) {
        this.schueler.add(schueler);
    }

    public void addFach(Fach fach) {
        this.feacher.add(fach);
    }

    public List<Schueler> getSchueler() {
        return schueler;
    }

    @Override
    public String toString() {
        return "Lehrer{" +
                "feacher=" + feacher +
                ", schueler=" + schueler +
                "} " + super.toString();
    }

    public List<Fach> getFeacher() {
        return feacher;
    }
}
