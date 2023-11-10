package at.jku.se.prse.team3;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fahrtenbuch {

    private List<Kategorie> kategorien;
    private List<Fahrt> fahrten;

    public Fahrtenbuch(List<Kategorie> kategorien, List<Fahrt> fahrten) {
        kategorien = kategorien;
        fahrten = fahrten;
    }
    //ID1
    public void neueFahrt(){

    }
    //ID3
    public void bearbeiteFahrt(){

    }
    //ID4
    public void loescheFahrten(){

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
