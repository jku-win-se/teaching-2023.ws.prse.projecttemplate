package at.jku.se.prse.enums;

public enum Status {
    ABSOLVIERT ("Absolviert"),
    AUF_FAHRT ("Auf Fahrt"),
    ZUKUENFTIG ("Zukünftig"),
    NICHT_DEFINIERT ("")
    ;

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

