package at.bbrz.schule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ZeugnisTest {
    Zeugnis zeugnis;

    @BeforeEach
    void setUp() {
        Schueler schueler = new Schueler("Fritz", "Müller",
                LocalDate.of(2000,1,1),
                new Adresse("Dort", "5", "2000", "Dorf", Land.OESTERREICH),
                false);

        zeugnis = new Zeugnis(schueler, "2025/2026", LocalDate.of(2025, 11, 20));
    }

    @Test
    @DisplayName("Notenschnitt soll bei mehreren Nachkommastellen runden.")
    void calculateCorrectNotenschnitt() {
        ZeugnisEintrag zeugnisEintrag = new ZeugnisEintrag(Fach.DEUTSCH, Note.fromWert(1), "");
        ZeugnisEintrag zeugnisEintrag2 = new ZeugnisEintrag(Fach.MATHE, Note.fromWert(2), "");
        ZeugnisEintrag zeugnisEintrag3 = new ZeugnisEintrag(Fach.BIOLOGIE, Note.fromWert(1), "");

        zeugnis.addEintrag(zeugnisEintrag);
        zeugnis.addEintrag(zeugnisEintrag2);
        zeugnis.addEintrag(zeugnisEintrag3);

        double notenschnitt = zeugnis.berechneNotenschnitt();

        assertEquals(1.33, notenschnitt);
    }

    @Test
    void returnZeroWhenNoEintrag() {
        double notenschnitt = zeugnis.berechneNotenschnitt();

        assertEquals(0.0, notenschnitt);
    }

    @Test
    void calculateCorrectNotenschnitt_ifCalculationProducesPeriodicResult() {
        ZeugnisEintrag zeugnisEintrag = new ZeugnisEintrag(Fach.DEUTSCH, Note.fromWert(1), "");
        ZeugnisEintrag zeugnisEintrag2 = new ZeugnisEintrag(Fach.MATHE, Note.fromWert(2), "");
        zeugnis.addEintrag(zeugnisEintrag);
        zeugnis.addEintrag(zeugnisEintrag2);

        double notenschnitt = zeugnis.berechneNotenschnitt();

        assertEquals(1.5, notenschnitt);
    }

    @Test
    void shouldThrowException_whenFachExistsTwice() {
        ZeugnisEintrag zeugnisEintrag = new ZeugnisEintrag(Fach.DEUTSCH, Note.fromWert(1), "");
        ZeugnisEintrag zeugnisEintrag2 = new ZeugnisEintrag(Fach.DEUTSCH, Note.fromWert(2), "");
        zeugnis.addEintrag(zeugnisEintrag);

        assertThrows(IllegalArgumentException.class, () -> zeugnis.addEintrag(zeugnisEintrag2));
    }

    @Test
    void shouldThrowException_whenModifyListOfGetEintraege() {
        ZeugnisEintrag zeugnisEintrag = new ZeugnisEintrag(Fach.DEUTSCH, Note.fromWert(1), "");
        zeugnis.addEintrag(zeugnisEintrag);

        assertThrows(UnsupportedOperationException.class, () ->
                zeugnis.getEintraege().add(zeugnisEintrag));

        assertThrows(UnsupportedOperationException.class,
                () -> zeugnis.getEintraege().remove(zeugnisEintrag));

        assertThrows(UnsupportedOperationException.class,
                () -> zeugnis.getEintraege().clear());
    }

}