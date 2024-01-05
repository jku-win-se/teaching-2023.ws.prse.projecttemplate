//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

public class Fahrt {
    private String KFZ_kennzeichen;
    private String activeFahrzeit;
    private String abfahrtszeit;
    private String gefahreneKM;
    private String Ankunftszeit;
    private String kategorie;

    public Fahrt(String kFZ_kennzeichen, String activeFahrzeit, String abfahrtszeit, String ankunftszeit, String gefahreneKM, String kategorieField) {
        this.KFZ_kennzeichen = kFZ_kennzeichen;
        this.activeFahrzeit = activeFahrzeit;
        this.abfahrtszeit = abfahrtszeit;
        this.gefahreneKM = gefahreneKM;
        this.Ankunftszeit = ankunftszeit;
        this.kategorie = kategorieField;
    }

    public String getKFZ_kennzeichen() {
        return this.KFZ_kennzeichen;
    }

    public void setKFZ_kennzeichen(String kFZ_kennzeichen) {
        this.KFZ_kennzeichen = kFZ_kennzeichen;
    }

    public String getActiveFahrzeit() {
        return this.activeFahrzeit;
    }

    public void setActiveFahrzeit(String activeFahrzeit) {
        this.activeFahrzeit = activeFahrzeit;
    }

    public String getAbfahrtszeit() {
        return this.abfahrtszeit;
    }

    public void setAbfahrtszeit(String abfahrtszeit) {
        this.abfahrtszeit = abfahrtszeit;
    }

    public String getGefahreneKM() {
        return this.gefahreneKM;
    }

    public void setGefahreneKM(String gefahreneKM) {
        this.gefahreneKM = gefahreneKM;
    }

    public String getAnkunftszeit() {
        return this.Ankunftszeit;
    }

    public void setAnkunftszeit(String ankunftszeit) {
        this.Ankunftszeit = ankunftszeit;
    }

    public String getKategorie() {
        return this.kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }
}
