package at.bbrz;

import at.bbrz.schule.*;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Adresse adresseKlaus = new Adresse("Klausstr.", "7a", "2700",
                "Wiener Neustadt", "Österreich");
        Mitarbeiter klaus = new Mitarbeiter("Klaus", "Maier",
                LocalDate.of(1980, 8, 12), adresseKlaus, "4711",
                2400, false);

        System.out.println(klaus);

        Adresse adresseLehrer1 = new Adresse("Wissensweg", "1", "1010",
                "Wien", "Österreich");
        Lehrer lehrer1= new Lehrer("Willi", "Willswissen",
                LocalDate.of(1960, 1,1), adresseLehrer1, "L0001",
                4000, false);
        lehrer1.addFach("Mathe");
        lehrer1.addFach("Biologie");
        lehrer1.addFach("Physik");


        Schueler fritzi = new Schueler("Fritzi", "Huber", LocalDate.of(2008, 5, 12),
                new Adresse("Fritzstr.", "7", "1200", "Wien", "Österreich"), false);

        lehrer1.addSchueler(fritzi);

        System.out.println(lehrer1);

        Chef chef = new Chef("Chef", "Supa", LocalDate.of(1950, 1, 13)
        ,adresseLehrer1, "C0001", 6000);

        chef.addMitarbeiter(klaus);
        chef.addMitarbeiter(lehrer1);
        //chef.addMitarbeiter(chef); -> Wäre ein Zirkelbezug -> Also Exception werfen
    }
}