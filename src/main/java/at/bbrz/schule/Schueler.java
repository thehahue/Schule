package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("SCHUELER")
public class Schueler extends Person {
    private boolean schuelerVertreter;

    @JsonCreator
    public Schueler(@JsonProperty("vorname") String vorname,
                    @JsonProperty("nachname") String nachname,
                    @JsonProperty("geburtsDatum") LocalDate geburtsDatum,
                    @JsonProperty("adresse") Adresse adresse,
                    @JsonProperty("schuelerVertreter") boolean schuelerVertreter) {
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
