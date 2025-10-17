package at.bbrz.schule;

import java.util.ArrayList;
import java.util.List;

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
}
