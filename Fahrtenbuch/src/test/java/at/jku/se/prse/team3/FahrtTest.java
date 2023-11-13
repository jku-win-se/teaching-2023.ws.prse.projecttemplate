package at.jku.se.prse.team3;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FahrtTest {

    private Fahrt fahrt;

    @Before
    public void setUp() {
        fahrt = new Fahrt("AB-1234", LocalDate.of(2023, 1, 1), LocalTime.of(10, 0), LocalTime.of(11, 0), 100.0, LocalTime.of(1, 0), Arrays.asList("Geschäftlich", "Stadt"), FahrtStatus.ABSOLVIERT);
    }

    @Test
    public void testKfzKennzeichen() {
        assertEquals("AB-1234", fahrt.getKfzKennzeichen());
    }

    @Test
    public void testDatum() {
        assertEquals(LocalDate.of(2023, 1, 1), fahrt.getDatum());
    }

    @Test
    public void testAbfahrtszeit() {
        assertEquals(LocalTime.of(10, 0), fahrt.getAbfahrtszeit());
    }

    @Test
    public void testAnkunftszeit() {
        assertEquals(LocalTime.of(11, 0), fahrt.getAnkunftszeit());
    }

    @Test
    public void testGefahreneKilometer() {
        assertEquals(100.0, fahrt.getGefahreneKilometer(), 0.0);
    }

    @Test
    public void testAktiveFahrzeit() {
        assertEquals(LocalTime.of(1, 0), fahrt.getAktiveFahrzeit());
    }

    @Test
    public void testKategorien() {
        assertEquals(Arrays.asList("Geschäftlich", "Stadt"), fahrt.getKategorien());
    }

    @Test
    public void testFahrtstatus() {
        assertEquals(FahrtStatus.ABSOLVIERT, fahrt.getFahrtstatus());
    }

    @Test
    public void testAddKategorie() {
        fahrt.addKategorie("Privat");
        assertTrue(fahrt.getKategorien().contains("Privat"));
    }
}
