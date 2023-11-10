package at.jku.se.prse.team3;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

public class Fahrt {

    private String kfzKennzeichen;
    private LocalDate datum;
    private LocalTime abfahrtszeit;
    private LocalTime ankunftszeit;
    private Double gefahreneKilometer;
    private LocalTime aktiveFahrzeit;

    private List<String> kategorien;
    private FahrtStatus fahrtstatus;

    public Fahrt(String kfzKennzeichen, LocalDate datum, LocalTime abfahrtszeit, LocalTime ankunftszeit, Double gefahreneKilometer, LocalTime aktiveFahrzeit, List<String> fahrtKategorie,FahrtStatus fahrtstatus) {
        this.kfzKennzeichen = kfzKennzeichen;
        this.datum = datum;
        this.abfahrtszeit = abfahrtszeit;
        this.ankunftszeit = ankunftszeit;
        this.gefahreneKilometer = gefahreneKilometer;
        this.aktiveFahrzeit = aktiveFahrzeit;
        this.fahrtstatus = fahrtstatus;
        this.kategorien = fahrtKategorie;
    }

    public Double getGefahreneKilometer() {
        return gefahreneKilometer;
    }

    public void setGefahreneKilometer(Double gefahreneKilometer) {
        this.gefahreneKilometer = gefahreneKilometer;
    }

    public LocalTime getAktiveFahrzeit() {
        return aktiveFahrzeit;
    }

    public void setAktiveFahrzeit(LocalTime aktiveFahrzeit) {
        this.aktiveFahrzeit = aktiveFahrzeit;
    }

    public List<String> getKategorien() {
        return kategorien;
    }

    public void setKategorien(List<String> kategorien) {
        this.kategorien = kategorien;
    }

    public LocalTime getAnkunftszeit() {
        return ankunftszeit;
    }

    public void setAnkunftszeit(LocalTime ankunftszeit) {
        this.ankunftszeit = ankunftszeit;
    }

    public FahrtStatus getFahrtstatus() {
        return fahrtstatus;
    }

    public void setFahrtstatus(FahrtStatus fahrtstatus) {
        this.fahrtstatus = fahrtstatus;
    }




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

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalDate getDatum() {
        return datum;
    }
    //ID7
    public void addKategorie(String kategorie){
        kategorien.add(kategorie);
    }


}
