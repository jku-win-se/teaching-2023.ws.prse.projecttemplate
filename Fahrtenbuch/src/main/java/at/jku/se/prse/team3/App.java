package at.jku.se.prse.team3;

/**
 * Hello world!
 *
 */
public abstract class App
{
    public static void main( String[] args )
    {
        System.out.println( "  ___     _        _            _            _   " );
        System.out.println(" | __|_ _| |_  _ _| |_ ___ _ _ | |__ _  _ __| |_  ");
        System.out.println(" | _/ _` | ' \\| '_|  _/ -_) ' \\| '_ \\ || / _| ' \\ ");
        System.out.println(" |_|\\__,_|_||_|_|  \\__\\___|_||_|_.__/\\_,_\\__|_||_|");
        System.out.println("     by  TEAM 3 - JKU                                   ");

        List<String> kategorien = new ArrayList<>();
        List<Fahrt> fahrten = new ArrayList<>();
        Fahrtenbuch fahrtenbuch= new Fahrtenbuch(kategorien, fahrten);

        fahrtenbuch.neueFahrt("L-123", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2),
                50.0, LocalTime.of(1,0), FahrtStatus.ABSOLVIERT);




        Platform.startup(() -> new FahrtenbuchUI(fahrtenbuch).start(new Stage(StageStyle.DECORATED)));

    }

    public abstract void start(Stage primaryStage);
}
