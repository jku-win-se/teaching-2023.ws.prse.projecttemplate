package at.jku.se.prse.team3;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Fahrt {

    private String kfzKennzeichen;
    private Date datum;
    private LocalTime abfahrtszeit;
    private LocalTime ankunftszeit;
    private Double gefahreneKilometer;
    private LocalTime aktiveFahrzeit;

    public FahrtStatus getFahrtstatus() {
        return Fahrtstatus;
    }

    public void setFahrtstatus(FahrtStatus fahrtstatus) {
        Fahrtstatus = fahrtstatus;
    }

    private FahrtStatus Fahrtstatus;
    private List<Kategorie> kategorien;

    public LocalTime getAbfahrtszeit() {
        return abfahrtszeit;
    }

    public void setAbfahrtszeit(LocalTime abfahrtszeit) {
        this.abfahrtszeit = abfahrtszeit;
    }

    // Konstruktor
    public Fahrt(String kfzKennzeichen) {
        this.kfzKennzeichen = kfzKennzeichen;
    }

    // Getters und Setters
    public String getKfzKennzeichen() {
        return kfzKennzeichen;
    }

    public void setKfzKennzeichen(String kfzKennzeichen) {
        this.kfzKennzeichen = kfzKennzeichen;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getDatum() {
        return datum;
    }
    //ID7
    public void addKategorie(Kategorie kategorie){
        kategorien.add(kategorie);
    }


}
