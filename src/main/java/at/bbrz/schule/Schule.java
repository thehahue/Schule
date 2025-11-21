package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Schule {
    private Adresse adresse;
    private String bezeichnung;
    private List<Mitarbeiter> mitarbeiter;
    @JsonIgnore
    private List<Schueler> schueler;
    private List<Zeugnis> zeugnisse;

    public Schule(Adresse adresse, String bezeichnung) {
        this.adresse = adresse;
        this.bezeichnung = bezeichnung;
        this.mitarbeiter = new ArrayList<>();
        this.schueler = new ArrayList<>();
        this.zeugnisse = new ArrayList<>();
    }

    public Schule(Adresse adresse, String bezeichnung, Chef chef) {
        this(adresse, bezeichnung);
        if (chef != null) {
            this.mitarbeiter.add(chef);
        }
    }

    public List<Schueler> getSchueler() {
        return Collections.unmodifiableList(schueler);
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    @JsonCreator
    public Schule(@JsonProperty("adresse") Adresse adresse,
                  @JsonProperty("bezeichnung") String bezeichnung,
                  @JsonProperty("chef") Chef chef,
                  @JsonProperty("mitarbeiter") List<Mitarbeiter> mitarbeiter) {
        this.adresse = adresse;
        this.bezeichnung = bezeichnung;
        this.mitarbeiter = new ArrayList<>();
        this.schueler = new ArrayList<>();
        this.zeugnisse = new ArrayList<>();

        if (chef != null) {
            this.mitarbeiter.add(chef);
        }
        if (mitarbeiter != null) {
            this.mitarbeiter.addAll(mitarbeiter);
        }
    }

    public void addZeugnis(Zeugnis zeugnis) {
        this.zeugnisse.add(zeugnis);
    }

    public void addZeugnisse(List<Zeugnis> zeugnisse) {
        this.zeugnisse.addAll(zeugnisse);
    }

    public List<Zeugnis> getZeugnisse() {
        return Collections.unmodifiableList(zeugnisse);
    }

    public static Schule load(Path path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            Schule schule = mapper.readValue(new File(path.toString()), Schule.class);

            // abgeleitete Sammlung der Schüler aus allen Lehrern (ohne Duplikate)
            java.util.Set<Schueler> unique = new java.util.LinkedHashSet<>();
            for (Mitarbeiter m : schule.mitarbeiter) {
                if (m instanceof Lehrer l) {
                    if (l.getSchueler() != null) {
                        unique.addAll(l.getSchueler());
                    }
                }
            }
            schule.schueler.addAll(unique);

            return schule;
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Laden der Schule aus " + path + ": " + e.getMessage(), e);
        }
    }

    public void save(Path path) {
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            // Felder der Klasse serialisieren, auch wenn keine Getter vorhanden
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            mapper.writeValue(new File(path.toString()), this);
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Speichern der Schule nach " + path + ": " + e.getMessage(), e);
        }
    }

    public List<Schueler> findSchuelerVertreter() {
        return schueler.stream().filter(s -> s.isSchuelerVertreter())
                .toList();
    }

    public List<Fach> findAvailableSubjects() {
        List<Lehrer> lehrer = this.mitarbeiter.stream()
                .filter(ma -> ma instanceof Lehrer)
                .map(ma -> (Lehrer) ma)
                .toList();

        Set<Fach> found = new LinkedHashSet<>();

        for (Lehrer l : lehrer) {
            found.addAll(l.getFeacher());
        }

        return found.stream().toList();

        /*
            return mitarbeiter.stream()
            .filter(Lehrer.class::isInstance)
            .map(Lehrer.class::cast)
            .flatMap(l -> l.getFeacher().stream())
            .distinct()
            .toList();
         */
    }

    // wie findAvailableSubjects, aber ohne Streams – nur mit Schleifen
    public List<Fach> findAvailableSubjectsFor() {
        List<Lehrer> lehrerList = new ArrayList<>();
        for (Mitarbeiter m : this.mitarbeiter) {
            if (m instanceof Lehrer) {
                lehrerList.add((Lehrer) m);
            }
        }

        Set<Fach> unique = new LinkedHashSet<>();
        for (Lehrer l : lehrerList) {
            List<Fach> faecher = l.getFeacher();
            if (faecher != null) {
                unique.addAll(faecher);
            }
        }

        return new ArrayList<>(unique);
    }

    public void deleteSchueler(String vorname, String nachname, LocalDate geburtsDatum) {
        Schueler vergl = new Schueler(vorname, nachname, geburtsDatum, null, false);
        // Funktioniert nicht, weil eine Liste nicht während der Schleife verändert werden darf -> ConcurrentModificationException
        /*        for (Schueler s : schueler) {
            if (vergl.equals(s)) {
                schueler.remove(s);
            }
        }*/

        // Abhilfe mit einem Iterator
        Iterator<Schueler> iter = schueler.iterator();
        while (iter.hasNext()) {
            Schueler schueler = iter.next();

            if (vergl.equals(schueler)) {
                iter.remove();
            }
        }

        Iterator<Mitarbeiter> iterMitarbeiter = mitarbeiter.iterator();
        while (iterMitarbeiter.hasNext()) {
            Mitarbeiter mitarbeiter = iterMitarbeiter.next();
            if (mitarbeiter instanceof Lehrer) {
                Lehrer lehrer = (Lehrer) mitarbeiter;

                iter = lehrer.getSchueler().iterator();
                while (iter.hasNext()) {
                    Schueler schueler = iter.next();
                    if (vergl.equals(schueler)) {
                        iter.remove();
                    }
                }
            }
        }
    }

    // Moderne Variante mit removeIf und Streams
    public void deleteSchuelerNew(String vorname, String nachname, LocalDate geburtsDatum) {
        Schueler target = new Schueler(vorname, nachname, geburtsDatum, null, false);

        schueler.removeIf(target::equals);
        //schueler.removeIf(schueler -> target.equals(schueler));

        // Entferne aus allen Lehrer-Schüler-Listen
        mitarbeiter.stream()
                .filter(Lehrer.class::isInstance)
                .map(Lehrer.class::cast)
                .forEach(lehrer -> {
                    List<Schueler> liste = lehrer.getSchueler();
                    if (liste != null) {
                        liste.removeIf(target::equals);
                    }
                });
    }
}
