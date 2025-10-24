package at.bbrz;

import at.bbrz.schule.*;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Schule.load(Path.of("src/main/resources/schule.json"));
        System.out.println("ENDE");
    }
}