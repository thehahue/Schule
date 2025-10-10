package at.bbrz.schule;

public class Adresse {
    private String strasze;
    private String hausNummer;
    private String plz;
    private String stadt;
    private String land;

    public Adresse(String strasze, String hausNummer, String plz, String stadt, String land) {
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
