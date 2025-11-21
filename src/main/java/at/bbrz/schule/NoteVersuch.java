package at.bbrz.schule;

public class NoteVersuch {
    public static void main(String[] args) {
        Note noteEins = Note.fromWert(1);

        System.out.printf("Beschreibung von noteEins="+noteEins.getBeschreibung());
    }
}
