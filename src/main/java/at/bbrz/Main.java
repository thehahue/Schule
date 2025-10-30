package at.bbrz;

import at.bbrz.schule.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Schule schule = Schule.load(Path.of("src/main/resources/schule.json"));

        System.out.println("Schülervertreter:\n---------------------");

        List<Schueler> schuelerVertreter = schule.findSchuelerVertreter();
        for (Schueler schueler : schuelerVertreter) {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schueler));
        }

        schule.save(Path.of("src/main/resources/schule2.json"));

        Schule schule2 = Schule.load(Path.of("src/main/resources/schule2.json"));

        System.out.println("Schule und Schule2 gleich? "+schule.equals(schule2));

        System.out.println("ENDE");
    }
}
