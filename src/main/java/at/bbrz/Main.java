package at.bbrz;

import at.bbrz.schule.*;

import java.nio.file.Path;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Schule.load(Path.of("src/main/resources/schule.json"));
        System.out.println("ENDE");
    }
}
