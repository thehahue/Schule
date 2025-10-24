package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Adresse {
    private String strasze;
    private String hausNummer;
    private String plz;
    private String stadt;
    private Land land;

    @JsonCreator
    public Adresse(@JsonProperty("strasze") String strasze,
                   @JsonProperty("hausNummer") String hausNummer,
                   @JsonProperty("plz") String plz,
                   @JsonProperty("stadt") String stadt,
                   @JsonProperty("land") Land land) {
        this.strasze = strasze;
        this.hausNummer = hausNummer;
        this.plz = plz;
        this.stadt = stadt;
        this.land = land;
    }

    @JsonProperty("strasze")
    public String getStrasze() {
        return strasze;
    }

    @JsonProperty("hausNummer")
    public String getHausNummer() {
        return hausNummer;
    }

    @JsonProperty("plz")
    public String getPlz() {
        return plz;
    }

    @JsonProperty("stadt")
    public String getStadt() {
        return stadt;
    }

    @JsonProperty("land")
    public Land getLand() {
        return land;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "strasze='" + strasze + '\'' +
                ", hausNummer='" + hausNummer + '\'' +
                ", plz='" + plz + '\'' +
                ", stadt='" + stadt + '\'' +
                ", land='" + land + '\'' +
                '}';
    }
}
