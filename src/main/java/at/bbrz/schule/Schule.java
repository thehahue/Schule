package at.bbrz.schule;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.nio.file.Path;

public class Schule {
    private Adresse adresse;
    private String bezeichnung;
    private List<Mitarbeiter> mitarbeiter;
    private List<Schueler> schueler;

    public Schule(Adresse adresse, String bezeichnung) {
        this.adresse = adresse;
        this.bezeichnung = bezeichnung;
        this.mitarbeiter = new ArrayList<>();
        this.schueler = new ArrayList<>();
    }

    public Schule(Adresse adresse,  String bezeichnung, Chef chef) {
        this.adresse = adresse;
        this.bezeichnung = bezeichnung;
        this.mitarbeiter = new ArrayList<>();
        this.schueler = new ArrayList<>();

        this.mitarbeiter.add(chef);
    }

    public static Schule load() {
        return load(Path.of("schule.json"));
    }

    public static Schule load(Path path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File(path.toString()));

            String bezeichnung = text(root, "bezeichnung");
            Adresse adresse = parseAdresse(root.get("adresse"));

            // Chef
            JsonNode chefNode = root.get("chef");
            Chef chef = parseChef(chefNode);

            Schule schule = new Schule(adresse, bezeichnung, chef);

            // Mitarbeiter-Liste
            JsonNode mitarbeiterArray = root.get("mitarbeiter");
            if (mitarbeiterArray != null && mitarbeiterArray.isArray()) {
                for (JsonNode n : mitarbeiterArray) {
                    String type = text(n, "type");
                    if ("LEHRER".equalsIgnoreCase(type)) {
                        Lehrer l = parseLehrer(n);
                        schule.mitarbeiter.add(l);
                        // Schüler auch in die Schule aufnehmen
                        JsonNode schuelerArray = n.get("schueler");
                        if (schuelerArray != null && schuelerArray.isArray()) {
                            for (JsonNode s : schuelerArray) {
                                Schueler sch = parseSchueler(s);
                                schule.schueler.add(sch);
                            }
                        }
                    } else { // Standard: Mitarbeiter
                        Mitarbeiter m = parseMitarbeiter(n);
                        schule.mitarbeiter.add(m);
                    }
                }
            }

            return schule;
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Laden der Schule aus " + path + ": " + e.getMessage(), e);
        }
    }

    private static Adresse parseAdresse(JsonNode n) {
        if (n == null || n.isNull()) return null;
        return new Adresse(
                text(n, "strasze"),
                text(n, "hausNummer"),
                text(n, "plz"),
                text(n, "stadt"),
                text(n, "land")
        );
    }

    private static Mitarbeiter parseMitarbeiter(JsonNode n) {
        return new Mitarbeiter(
                text(n, "vorname"),
                text(n, "nachname"),
                LocalDate.parse(text(n, "geburtsDatum")),
                parseAdresse(n.get("adresse")),
                text(n, "nummer"),
                n.get("gehalt").asDouble(),
                n.get("betriebsrat").asBoolean(false)
        );
    }

    private static Lehrer parseLehrer(JsonNode n) {
        Lehrer l = new Lehrer(
                text(n, "vorname"),
                text(n, "nachname"),
                LocalDate.parse(text(n, "geburtsDatum")),
                parseAdresse(n.get("adresse")),
                text(n, "nummer"),
                n.get("gehalt").asDouble(),
                n.get("betriebsrat").asBoolean(false)
        );

        JsonNode faecher = n.get("faecher");
        if (faecher != null && faecher.isArray()) {
            for (JsonNode f : faecher) {
                l.addFach(Fach.valueOf(f.asText()));
            }
        }

        JsonNode schuelerArray = n.get("schueler");
        if (schuelerArray != null && schuelerArray.isArray()) {
            for (JsonNode s : schuelerArray) {
                l.addSchueler(parseSchueler(s));
            }
        }
        return l;
    }

    private static Chef parseChef(JsonNode n) {
        Chef c = new Chef(
                text(n, "vorname"),
                text(n, "nachname"),
                LocalDate.parse(text(n, "geburtsDatum")),
                parseAdresse(n.get("adresse")),
                text(n, "nummer"),
                n.get("gehalt").asDouble()
        );
        JsonNode faecher = n.get("faecher");
        if (faecher != null && faecher.isArray()) {
            for (JsonNode f : faecher) {
                c.addFach(Fach.valueOf(f.asText()));
            }
        }
        return c;
    }

    private static Schueler parseSchueler(JsonNode n) {
        return new Schueler(
                text(n, "vorname"),
                text(n, "nachname"),
                LocalDate.parse(text(n, "geburtsDatum")),
                parseAdresse(n.get("adresse")),
                n.get("schuelerVertreter").asBoolean(false)
        );
    }

    private static String text(JsonNode n, String field) {
        JsonNode v = (n == null) ? null : n.get(field);
        return v == null || v.isNull() ? null : v.asText();
    }
}
