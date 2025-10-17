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
    private String land;

    @JsonCreator
    public Adresse(@JsonProperty("strasze") String strasze,
                   @JsonProperty("hausNummer") String hausNummer,
                   @JsonProperty("plz") String plz,
                   @JsonProperty("stadt") String stadt,
                   @JsonProperty("land") String land) {
        this.strasze = strasze;
        this.hausNummer = hausNummer;
        this.plz = plz;
        this.stadt = stadt;
        this.land = land;
    }

    public String getStrasze() {
        return strasze;
    }

    public String getHausNummer() {
        return hausNummer;
    }

    public String getPlz() {
        return plz;
    }

    public String getStadt() {
        return stadt;
    }

    public String getLand() {
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
