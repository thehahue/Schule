package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Klassenzimmer {
    private String bezeichnung;
    private Lehrer klassenvorstand;
    private List<Schueler> schueler;

    public Klassenzimmer(String bezeichnung) {
        this(bezeichnung, null, null);
    }

    @JsonCreator
    public Klassenzimmer(@JsonProperty("bezeichnung") String bezeichnung,
                         @JsonProperty("klassenvorstand") Lehrer klassenvorstand,
                         @JsonProperty("schueler") List<Schueler> schueler) {
        this.bezeichnung = Objects.requireNonNull(bezeichnung, "Bezeichnung darf nicht null sein");
        this.klassenvorstand = klassenvorstand;
        this.schueler = new ArrayList<>();
        if (schueler != null) {
            this.schueler.addAll(schueler);
        }
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Lehrer getKlassenvorstand() {
        return klassenvorstand;
    }

    public void setKlassenvorstand(Lehrer klassenvorstand) {
        this.klassenvorstand = klassenvorstand;
    }

    public List<Schueler> getSchueler() {
        return Collections.unmodifiableList(schueler);
    }

    public void addSchueler(Schueler schueler) {
        Objects.requireNonNull(schueler, "Schüler darf nicht null sein");
        this.schueler.add(schueler);
    }

    public void removeSchueler(Schueler target) {
        this.schueler.removeIf(target::equals);
    }

    public void replaceSchueler(Schueler original, Schueler updated) {
        Objects.requireNonNull(original, "Original darf nicht null sein");
        Objects.requireNonNull(updated, "Updated darf nicht null sein");
        int index = this.schueler.indexOf(original);
        if (index >= 0) {
            this.schueler.set(index, updated);
        }
    }
}
