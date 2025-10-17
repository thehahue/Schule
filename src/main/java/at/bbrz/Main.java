package at.bbrz;

import at.bbrz.schule.*;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Mitarbeiter klaus = createMitarbeiter();
        System.out.println(klaus);

        Lehrer lehrer1 = createLehrer(); // mit STRG+ALT+m -> extract method aufgerufen
        Schueler fritzi = createSchueler();

        lehrer1.addSchueler(fritzi);

        System.out.println(lehrer1);

        Chef chef = createChef(lehrer1);

        try {
            chef.addMitarbeiter(klaus);
            chef.addMitarbeiter(lehrer1);
            //chef.addMitarbeiter(null);
            //chef.addMitarbeiter(chef); //-> Wäre ein Zirkelbezug -> Also Exception werfen

        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("ENDE");
    }

    private static Chef createChef(Lehrer lehrer1) {
        return new Chef("Chef", "Supa", LocalDate.of(1950, 1, 13)
        , lehrer1.getAdresse(), "C0001", 6000);
    }

    private static Schueler createSchueler() {
        return new Schueler("Fritzi", "Huber", LocalDate.of(2008, 5, 12),
                new Adresse("Fritzstr.", "7", "1200", "Wien", "Österreich"), false);
    }

    private static Mitarbeiter createMitarbeiter() {
        Adresse adresseKlaus = new Adresse("Klausstr.", "7a", "2700",
                "Wiener Neustadt", "Österreich");
        return new Mitarbeiter("Klaus", "Maier",
                LocalDate.of(1980, 8, 12), adresseKlaus, "4711",
                2400, false);
    }

    private static Lehrer createLehrer() {
        Adresse adresseLehrer1 = new Adresse("Wissensweg", "1", "1010",
                "Wien", "Österreich");
        Lehrer lehrer1= new Lehrer("Willi", "Willswissen",
                LocalDate.of(1960, 1,1), adresseLehrer1, "L0001",
                4000, false);
        lehrer1.addFach("Mathe");
        lehrer1.addFach("Biologie");
        lehrer1.addFach("Physik");
        return lehrer1;
    }
}