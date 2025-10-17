package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Land {
    // EU-Mitgliedstaaten
    BELGIEN("Belgien"),
    BULGARIEN("Bulgarien"),
    DAENEMARK("Dänemark"),
    DEUTSCHLAND("Deutschland"),
    ESTLAND("Estland"),
    FINNLAND("Finnland"),
    FRANKREICH("Frankreich"),
    GRIECHENLAND("Griechenland"),
    IRLAND("Irland"),
    ITALIEN("Italien"),
    KROATIEN("Kroatien"),
    LETTLAND("Lettland"),
    LITAUEN("Litauen"),
    LUXEMBURG("Luxemburg"),
    MALTA("Malta"),
    NIEDERLANDE("Niederlande"),
    OESTERREICH("Österreich"),
    POLEN("Polen"),
    PORTUGAL("Portugal"),
    RUMAENIEN("Rumänien"),
    SCHWEDEN("Schweden"),
    SLOWAKEI("Slowakei"),
    SLOWENIEN("Slowenien"),
    SPANIEN("Spanien"),
    TSCHECHIEN("Tschechien"),
    UNGARN("Ungarn"),
    ZYPERN("Zypern"),

    // Nicht EU
    SCHWEIZ("Schweiz");

    private final String displayName;

    Land(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String toJson() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    @JsonCreator
    public static Land fromJson(String value) {
        if (value == null) return null;
        for (Land l : values()) {
            if (l.displayName.equalsIgnoreCase(value)) {
                return l;
            }
        }
        // Generische Normalisierung (Umlaute → ae/oe/ue/ss)
        String n = normalize(value);
        for (Land l : values()) {
            if (normalize(l.displayName).equals(n)) {
                return l;
            }
        }
        throw new IllegalArgumentException("Unbekanntes Land: " + value);
    }

    private static String normalize(String s) {
        return s == null ? null : s.trim()
                .toLowerCase(java.util.Locale.ROOT)
                .replace("ä", "ae")
                .replace("ö", "oe")
                .replace("ü", "ue")
                .replace("ß", "ss");
    }
}
