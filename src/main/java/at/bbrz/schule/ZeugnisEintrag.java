package at.bbrz.schule;

import java.util.Objects;

public class ZeugnisEintrag {
    private final Fach fach;
    private final Note note;
    private final String kommentar;

    public ZeugnisEintrag(Fach fach, Note note, String kommentar) {
        this.fach = Objects.requireNonNull(fach, "Fach darf nicht null sein");
        this.note = Objects.requireNonNull(note, "Note darf nicht null sein");
        this.kommentar = kommentar;
    }

    public Fach getFach() {
        return fach;
    }

    public Note getNote() {
        return note;
    }

    public String getKommentar() {
        return kommentar;
    }

    @Override
    public String toString() {
        return "ZeugnisEintrag{" +
                "fach=" + fach +
                ", note=" + note +
                ", kommentar='" + kommentar + '\'' +
                '}';
    }
}
