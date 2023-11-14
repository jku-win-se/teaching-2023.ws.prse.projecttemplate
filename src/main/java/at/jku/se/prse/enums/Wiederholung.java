package at.jku.se.prse.enums;

public enum Wiederholung {
    WOECHENTLICH ("woechentlich"),
    MONATLICH ("monatlich"),
    JAEHRLICH ("jaehrlich"),
    NICHT_DEFINIERT ("")
    ;

    private final String label;

    Wiederholung(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
