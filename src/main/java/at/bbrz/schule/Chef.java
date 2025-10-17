package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("CHEF")
public class Chef extends Lehrer {
    private List<Mitarbeiter> mitarbeiter;

    @JsonCreator
    public Chef(@JsonProperty("vorname") String vorname,
                @JsonProperty("nachname") String nachname,
                @JsonProperty("geburtsDatum") LocalDate geburtsDatum,
                @JsonProperty("adresse") Adresse adresse,
                @JsonProperty("nummer") String nummer,
                @JsonProperty("gehalt") double gehalt) {
        super(vorname, nachname, geburtsDatum, adresse, nummer, gehalt, false);
        this.mitarbeiter = new ArrayList<>();
    }

    public void addMitarbeiter(Mitarbeiter mitarbeiter) {
        //Zirkelbezug verhindern
        if (mitarbeiter == this) {
            throw new IllegalArgumentException("Chef connot be added");
        }
        if (mitarbeiter == null) {
            throw new IllegalArgumentException("Employee cannot be null");
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
