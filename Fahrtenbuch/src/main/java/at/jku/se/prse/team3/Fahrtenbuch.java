package at.jku.se.prse.team3;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Fahrtenbuch {

    private List<Kategorie> kategorien;
    private List<Fahrt> fahrten;

    public Fahrtenbuch(List<Kategorie> kategorien, List<Fahrt> fahrten) {
        this.kategorien = new ArrayList<>(kategorien);
        this.fahrten = new ArrayList<>(fahrten);
    }
    //ID1
    public void neueFahrt(Fahrt fahrt){
        fahrten.add(fahrt);
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
    public void planeZukuenftigeFahrten(){

    }
    //ID2
    public List<Fahrt> listeFahrten(){
        List<Fahrt> fahrten = new ArrayList<Fahrt>();

        return fahrten;
    }

    public List<Fahrt> filtereFahrten(){

        List<Fahrt> fahrten = new ArrayList<Fahrt>();

        return fahrten;
    }

    public List<Fahrt> sortiereFahrten(){

        List<Fahrt> fahrten = new ArrayList<Fahrt>();

        return fahrten;

    }
    //ID6
    public FahrtStatus getFahrtstatus(Fahrt fahrt){
        return fahrt.getFahrtstatus();
    }

    //ID8
    public void exportFahrt() throws IOException {
        //export Fahrten&Kategorien as CSV.
        String exportPath="fahrten.csv";
        String exportPath2="Kategorien.csv";
        CSVWriter csvWriter=new CSVWriter(new FileWriter(exportPath));
        for (Fahrt f:fahrten
             ) {
            String[] data = {f.getKfzKennzeichen()};
                }
        CSVWriter csvWriter2=new CSVWriter(new FileWriter(exportPath2));
        for (Kategorie k:kategorien
        ) {
            String[] data = {k.toString()};
        }

    }
}
