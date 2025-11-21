package at.bbrz.schule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class SchuleAsciiPrinter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private SchuleAsciiPrinter() {
    }

    public static void print(Schule schule) {
        System.out.println(render(schule));
    }

    public static String render(Schule schule) {
        Objects.requireNonNull(schule, "Schule darf nicht null sein");

        StringBuilder sb = new StringBuilder();
        sb.append("Schule: ").append(schule.getBezeichnung()).append(System.lineSeparator());

        Adresse adresse = schule.getAdresse();
        if (adresse != null) {
            sb.append("Adresse: ")
                    .append(adresse.getStrasze()).append(" ").append(adresse.getHausNummer()).append(", ")
                    .append(adresse.getPlz()).append(" ").append(adresse.getStadt());
            if (adresse.getLand() != null) {
                sb.append(" (").append(adresse.getLand().toString()).append(")");
            }
            sb.append(System.lineSeparator());
        }

        sb.append(buildStudentsTable(schule.getSchueler()));
        sb.append(buildZeugnisseSection(schule.getZeugnisse()));

        return sb.toString();
    }

    private static String buildStudentsTable(List<Schueler> schuelerListe) {
        String[] headers = {"Nr", "Vorname", "Nachname", "Geburtsdatum", "Vertreter"};
        List<String[]> rows = new ArrayList<>();

        int index = 1;
        for (Schueler schueler : schuelerListe) {
            rows.add(new String[]{
                    Integer.toString(index++),
                    schueler.getVorname(),
                    schueler.getNachname(),
                    schueler.getGeburtsDatum() != null ? DATE_FORMATTER.format(schueler.getGeburtsDatum()) : "-",
                    schueler.isSchuelerVertreter() ? "ja" : "nein"
            });
        }

        return buildTable(headers, rows);
    }

    private static String buildZeugnisseSection(List<Zeugnis> zeugnisse) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator()).append("Zeugnisse:").append(System.lineSeparator());
        if (zeugnisse.isEmpty()) {
            sb.append("(keine)").append(System.lineSeparator());
            return sb.toString();
        }

        sb.append(buildZeugnisOverviewTable(zeugnisse));
        sb.append(buildZeugnisDetailTables(zeugnisse));

        return sb.toString();
    }

    private static String buildZeugnisOverviewTable(List<Zeugnis> zeugnisse) {
        String[] headers = {"Schüler", "Schuljahr", "Ausstellung", "Schnitt", "Fächer"};
        List<String[]> rows = new ArrayList<>();
        for (Zeugnis zeugnis : zeugnisse) {
            rows.add(new String[]{
                    formatSchuelerName(zeugnis.getSchueler()),
                    zeugnis.getSchuljahr(),
                    formatDate(zeugnis.getAusstellungsDatum()),
                    formatAverage(zeugnis.berechneNotenschnitt()),
                    Integer.toString(zeugnis.getEintraege().size())
            });
        }
        return buildTable(headers, rows);
    }

    private static String buildZeugnisDetailTables(List<Zeugnis> zeugnisse) {
        StringBuilder sb = new StringBuilder();
        for (Zeugnis zeugnis : zeugnisse) {
            sb.append(System.lineSeparator())
                    .append("Zeugnis für ")
                    .append(formatSchuelerName(zeugnis.getSchueler()))
                    .append(" (").append(zeugnis.getSchuljahr()).append(")")
                    .append(System.lineSeparator());

            String[] headers = {"Fach", "Note", "Kommentar"};
            List<String[]> rows = new ArrayList<>();
            for (ZeugnisEintrag eintrag : zeugnis.getEintraege()) {
                rows.add(new String[]{
                        eintrag.getFach().name(),
                        eintrag.getNote().name(),
                        eintrag.getKommentar() == null ? "" : eintrag.getKommentar()
                });
            }
            sb.append(buildTable(headers, rows));
        }
        return sb.toString();
    }

    private static String buildTable(String[] headers, List<String[]> rows) {
        int[] widths = calculateColumnWidths(headers, rows);
        String horizontal = buildSeparator(widths);

        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append(horizontal).append(System.lineSeparator());
        appendRow(sb, headers, widths);
        sb.append(horizontal).append(System.lineSeparator());

        if (rows.isEmpty()) {
            appendRow(sb, emptyRow(headers.length), widths);
        } else {
            for (String[] row : rows) {
                appendRow(sb, row, widths);
            }
        }
        sb.append(horizontal).append(System.lineSeparator());

        return sb.toString();
    }

    private static int[] calculateColumnWidths(String[] headers, List<String[]> rows) {
        int[] widths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
        }
        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                widths[i] = Math.max(widths[i], row[i] != null ? row[i].length() : 1);
            }
        }
        return widths;
    }

    private static String buildSeparator(int[] widths) {
        StringBuilder sb = new StringBuilder();
        sb.append('+');
        for (int width : widths) {
            sb.append("-".repeat(width + 2)).append('+');
        }
        return sb.toString();
    }

    private static void appendRow(StringBuilder sb, String[] data, int[] widths) {
        sb.append('|');
        for (int i = 0; i < data.length; i++) {
            String cell = data[i] == null ? "" : data[i];
            sb.append(' ').append(padRight(cell, widths[i])).append(' ').append('|');
        }
        sb.append(System.lineSeparator());
    }

    private static String padRight(String value, int width) {
        if (value.length() >= width) {
            return value;
        }
        return value + " ".repeat(width - value.length());
    }

    private static String[] emptyRow(int columns) {
        String[] row = new String[columns];
        java.util.Arrays.fill(row, "-");
        return row;
    }

    private static String formatDate(LocalDate date) {
        return date == null ? "-" : DATE_FORMATTER.format(date);
    }

    private static String formatAverage(double value) {
        if (value <= 0) {
            return "-";
        }
        return String.format(java.util.Locale.GERMAN, "%.2f", value);
    }

    private static String formatSchuelerName(Schueler schueler) {
        if (schueler == null) {
            return "-";
        }
        return schueler.getVorname() + " " + schueler.getNachname();
    }
}
