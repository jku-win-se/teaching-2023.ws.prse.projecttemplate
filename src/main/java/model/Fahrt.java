package model;

import java.io.Serializable;

public class Fahrt implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int fahrtID;
    private String KFZ_kennzeichen;
    private String Datuem;
    private String activeFahrzeit;
    private String abfahrtszeit;
    private String gefahreneKM;
    private String Ankunftszeit;
    private String kategorie;

    public Fahrt(int fahrtID, String kFZ_kennzeichen, String datuem, String activeFahrzeit, String abfahrtszeit,
                 String gefahreneKM, String ankunftszeit, String kategorie) {
        super();
        this.fahrtID = fahrtID;
        KFZ_kennzeichen = kFZ_kennzeichen;
        Datuem = datuem;
        this.activeFahrzeit = activeFahrzeit;
        this.abfahrtszeit = abfahrtszeit;
        this.gefahreneKM = gefahreneKM;
        Ankunftszeit = ankunftszeit;
        this.kategorie = kategorie;
    }

    public int getFahrtID() {
        return fahrtID;
    }
    public void setFahrtID(int fahrtID) {
        this.fahrtID = fahrtID;
    }
    public String getKFZ_kennzeichen() {
        return KFZ_kennzeichen;
    }
    public void setKFZ_kennzeichen(String kFZ_kennzeichen) {
        KFZ_kennzeichen = kFZ_kennzeichen;
    }
    public String getDatuem() {
        return Datuem;
    }
    public void setDatuem(String datuem) {
        Datuem = datuem;
    }
    public String getActiveFahrzeit() {
        return activeFahrzeit;
    }
    public void setActiveFahrzeit(String activeFahrzeit) {
        this.activeFahrzeit = activeFahrzeit;
    }
    public String getAbfahrtszeit() {
        return abfahrtszeit;
    }
    public void setAbfahrtszeit(String abfahrtszeit) {
        this.abfahrtszeit = abfahrtszeit;
    }
    public String getGefahreneKM() {
        return gefahreneKM;
    }
    public void setGefahreneKM(String gefahreneKM) {
        this.gefahreneKM = gefahreneKM;
    }
    public String getAnkunftszeit() {
        return Ankunftszeit;
    }
    public void setAnkunftszeit(String ankunftszeit) {
        Ankunftszeit = ankunftszeit;
    }
    public String getKategorie() {
        return kategorie;
    }
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

}
