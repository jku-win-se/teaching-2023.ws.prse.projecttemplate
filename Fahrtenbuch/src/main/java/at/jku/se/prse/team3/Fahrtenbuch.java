package at.jku.se.prse.team3;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;

public class Fahrtenbuch {

    private List<String> kategorien;
    private List<Fahrt> fahrten;

    public Fahrtenbuch(List<String> kategorien, List<Fahrt> fahrten) {
        this.kategorien = new ArrayList<>(kategorien);
        this.fahrten = new ArrayList<>(fahrten);
    }

    public Fahrtenbuch() {
    kategorien=new ArrayList<>();
    fahrten=new ArrayList<>();
    }

    //ID1
    public void neueFahrt(String kfzKennzeichen, LocalDate datum, LocalTime abfahrtszeit,
                          LocalTime neueAnkunftszeit, Double neueGefahreneKilometer,
                          LocalTime neueAktiveFahrzeit, FahrtStatus fahrtStatus, List<String> category) throws IOException {
        fahrten.add(new Fahrt(kfzKennzeichen,datum,abfahrtszeit,neueAnkunftszeit,neueGefahreneKilometer,neueAktiveFahrzeit,category,fahrtStatus));
        exportFahrt();
    }
    //ID3
    public void bearbeiteFahrt(String kfzKennzeichen, LocalDate datum, LocalTime abfahrtszeit,
                               LocalTime neueAnkunftszeit, Double neueGefahreneKilometer,
                               LocalTime neueAktiveFahrzeit) {
        for (Fahrt fahrt : fahrten) {
            // Finde die spezifische Fahrt basierend auf dem Schlüssel
            if (fahrt.getKfzKennzeichen().equals(kfzKennzeichen) &&
                    fahrt.getDatum().equals(datum) &&
                    fahrt.getAbfahrtszeit().equals(abfahrtszeit)) {

                // Update der Eigenschaften der gefundenen Fahrt
                fahrt.setAnkunftszeit(neueAnkunftszeit);
                fahrt.setGefahreneKilometer(neueGefahreneKilometer);
                fahrt.setAktiveFahrzeit(neueAktiveFahrzeit);

                // Breche die Schleife ab, da die Fahrt gefunden und bearbeitet wurde
                break;
            }
        }
    }
    //ID4

    public void loescheFahrten(String kfzKennzeichen, LocalDate datum, LocalTime abfahrtszeit) {
        fahrten.removeIf(fahrt ->
                fahrt.getKfzKennzeichen().equals(kfzKennzeichen) &&
                        fahrt.getDatum().equals(datum) &&
                        fahrt.getAbfahrtszeit().equals(abfahrtszeit));

    }

    //ID5
    public void planeZukuenftigeFahrten(List<LocalDate> reoccurances,
                                        String      kfzKennzeichen,
                                        LocalTime   abfahrtszeit,
                                        List<String> fahrtKategorie) throws IOException {

        for (LocalDate d:reoccurances
             ) {
            LocalTime neueAnkunftszeit= LocalTime.of(0,0,0);
            double neueGefahreneKilometer=0;
            LocalTime neueAktiveFahrzeit= LocalTime.of(0,0,0);

            fahrten.add(new Fahrt(
                    kfzKennzeichen,
                    d,
                    abfahrtszeit,
                    neueAnkunftszeit,
                    neueGefahreneKilometer,
                    neueAktiveFahrzeit,
                    fahrtKategorie,
                    FahrtStatus.ZUKUENFTIG));
        }

        exportFahrt();


    }


    //ID2
    public List<Fahrt> listeFahrten(){


        return fahrten;
    }

    public List<Fahrt> filtereFahrten(){

        //List<Fahrt> fahrten = new ArrayList<Fahrt>();

        return fahrten;
    }

    public void sortiereFahrten() {
        fahrten.sort(Comparator.comparing(Fahrt::getDatum)
                .thenComparing(Fahrt::getAbfahrtszeit));
    }

    //ID6
    public FahrtStatus getFahrtstatus(Fahrt fahrt){
        return fahrt.getFahrtstatus();
    }

    //ID8
    public void exportFahrt() throws IOException {
        //export Fahrten&Kategorien as CSV.
        Path path= Paths.get(System.getProperty("user.home")+ File.separator + "Documents"+File.separator+"Fahrtenbuch 0.0.3");
        String realExport=path.toString();

        String exportFahrten= realExport+ File.separator+ "fahrten.csv";
        String exportKategorien=realExport+ File.separator+"Kategorien.csv";
        CSVWriter csvWriter=new CSVWriter(new FileWriter(exportFahrten));


        for (Fahrt f:fahrten
             ) {
            String[] data = {f.getKfzKennzeichen(),
                    String.valueOf(f.getDatum()),
                    String.valueOf(f.getAbfahrtszeit()),
                    String.valueOf(f.getAnkunftszeit()),
                    String.valueOf(f.getGefahreneKilometer()),
                    String.valueOf(f.getAktiveFahrzeit()),
                    String.valueOf(f.getFahrtstatus()),
                    f.getKategorien().toString().replace("[","").replace("]","")};

            csvWriter.writeNext(data);
                }
        CSVWriter csvWriter2=new CSVWriter(new FileWriter(exportKategorien));
        for (String k:kategorien
        ) {
            String[] data = {k};
            csvWriter2.writeNext(data);
        }
        csvWriter.close();
        csvWriter2.close();

    }


    public void importFahrt() throws IOException, CsvValidationException {
        //export Fahrten&Kategorien as CSV.

        Path path= Paths.get(System.getProperty("user.home")+ File.separator + "Documents"+File.separator+"Fahrtenbuch 0.0.3");
        Path realImport = path;
        Path importFahrten = Paths.get(realImport + File.separator + "fahrten.csv");

        Path importKategorien = Paths.get(realImport + File.separator + "Kategorien.csv");
        try {

            Files.createDirectory(realImport);
            System.out.println("Neues System...");
            try (CSVWriter writer = new CSVWriter(new FileWriter(importFahrten.toFile()))) {
            }
            try (CSVWriter writer2 = new CSVWriter(new FileWriter(importKategorien.toFile()))) {
            }
            System.out.println("Initialisiere Datensätze...");
        }
        catch (FileAlreadyExistsException f){
            System.out.println("Willkommen zurück!");

            String[] data;
            String kFZKennzeichen;
            LocalDate datum;
            LocalTime abfahrtszeit;
            LocalTime ankunftszeit;
            Double gefahreneKilometer;
            LocalTime aktiveFahrzeit;
            List<String> kategorien;
            FahrtStatus fahrtstatus;

            try (CSVReader reader = new CSVReader(new FileReader(importFahrten.toFile()))) {

                while ((data = reader.readNext()) != null) {


                    kFZKennzeichen = data[0];


                    datum = LocalDate.parse(data[1]);


                    abfahrtszeit = LocalTime.parse(data[2]);


                    ankunftszeit = LocalTime.parse(data[3]);
                    gefahreneKilometer = Double.valueOf(data[4]);
                    aktiveFahrzeit = LocalTime.parse(data[5]);
                    if (FahrtStatus.ZUKUENFTIG.toString().equals(data[6])) fahrtstatus = FahrtStatus.ZUKUENFTIG;
                    else if (FahrtStatus.ABSOLVIERT.toString().equals(data[6])) fahrtstatus = FahrtStatus.ABSOLVIERT;
                    else fahrtstatus = FahrtStatus.AUF_FAHRT;
                    kategorien = Arrays.asList(data[7]);
                    neueFahrt(kFZKennzeichen, datum, abfahrtszeit, ankunftszeit, gefahreneKilometer, aktiveFahrzeit, fahrtstatus, kategorien);

                }

            } catch (NullPointerException nullPointerException) {


            }


        }

    }
    // Methode um gefahrene Kilometer pro Monat zu ermitteln
    public Map<YearMonth, Double> berechneKilometerProMonat() {
        Map<YearMonth, Double> kilometerProMonat = new TreeMap<>();
        for (Fahrt fahrt : fahrten) {
            YearMonth yearMonth = YearMonth.from(fahrt.getDatum());
            kilometerProMonat.merge(yearMonth, fahrt.getGefahreneKilometer(), Double::sum);
        }
        return kilometerProMonat;
    }
    public Map<YearMonth, Map<String, Double>> berechneKilometerProMonatUndKategorie() {
        Map<YearMonth, Map<String, Double>> kilometerProMonatUndKategorie = new TreeMap<>();

        for (Fahrt fahrt : fahrten) {
            YearMonth yearMonth = YearMonth.from(fahrt.getDatum());
            List<String> fahrtKategorien = fahrt.getKategorien();

            fahrtKategorien.forEach(kategorie -> {
                kilometerProMonatUndKategorie
                        .computeIfAbsent(yearMonth, k -> new HashMap<>())
                        .merge(kategorie, fahrt.getGefahreneKilometer(), Double::sum);
            });
        }
        return kilometerProMonatUndKategorie;
    }

    public Map<Integer, Map<String, Double>> berechneKilometerProJahrUndKategorie() {
        Map<Integer, Map<String, Double>> kilometerProJahrUndKategorie = new TreeMap<>();

        for (Fahrt fahrt : fahrten) {
            int jahr = fahrt.getDatum().getYear();
            List<String> fahrtKategorien = fahrt.getKategorien();

            fahrtKategorien.forEach(kategorie -> {
                kilometerProJahrUndKategorie
                        .computeIfAbsent(jahr, k -> new HashMap<>())
                        .merge(kategorie, fahrt.getGefahreneKilometer(), Double::sum);
            });
        }
        return kilometerProJahrUndKategorie;
    }


    public Set<String> getKategorien() {
        Set<String> uniqueKategorien = new HashSet<>();
        for (Fahrt fahrt : fahrten) {
            uniqueKategorien.addAll(fahrt.getKategorien());
        }
        return uniqueKategorien;
    }

}
