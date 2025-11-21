package at.bbrz.schule;

public enum Note {
    SEHR_GUT(1, "Sehr gut"),
    GUT(2, "Gut"),
    BEFRIEDIGEND(3, "Befriedigend"),
    GENUEGEND(4, "Genügend"),
    NICHT_GENUEGEND(5, "Nicht genügend");

    private final int wert;
    private final String beschreibung;

    // Konstruktor für die Enum Einträge oben
    // z.B. SEHR_GUT(1, "Sehr gut")
    Note(int wert, String beschreibung) {
        this.wert = wert;
        this.beschreibung = beschreibung;
    }

    public int getWert() {
        return wert;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    // Statische Methode zum Erzeugen eines Eintrags
    // z.B. fromWert(1) liefert SEHR_GUT
    // Wird direkt über den Klassennamen aufgerufen z.B. Note.fromWert(1)
    public static Note fromWert(int wert) {
        for (Note note : values()) {
            if (note.wert == wert) {
                return note;
            }
        }
        throw new IllegalArgumentException("Unbekannter Notenwert: " + wert);
    }
}
