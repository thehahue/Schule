package at.bbrz.schule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Land {
    OESTERREICH("Österreich"),
    DEUTSCHLAND("Deutschland"),
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
        // Fallback for common ASCII variant
        String normalized = value
                .replace("Ä", "Ae").replace("ä", "ae")
                .replace("Ö", "Oe").replace("ö", "oe")
                .replace("Ü", "Ue").replace("ü", "ue")
                .replace("ß", "ss");
        if ("Oesterreich".equalsIgnoreCase(normalized)) return OESTERREICH;
        throw new IllegalArgumentException("Unbekanntes Land: " + value);
    }
}

