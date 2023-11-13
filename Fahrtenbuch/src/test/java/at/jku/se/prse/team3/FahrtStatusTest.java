package at.jku.se.prse.team3;

import static org.junit.Assert.*;
import org.junit.Test;

public class FahrtStatusTest {

    @Test
    public void testFahrtStatusWerte() {
        FahrtStatus[] statuses = FahrtStatus.values();
        assertTrue("Sollte drei Status haben", statuses.length == 3);
        assertEquals("Erster Status sollte ABSOLVIERT sein", FahrtStatus.ABSOLVIERT, statuses[0]);
        assertEquals("Zweiter Status sollte AUF_FAHRT sein", FahrtStatus.AUF_FAHRT, statuses[1]);
        assertEquals("Dritter Status sollte ZUKUENFTIG sein", FahrtStatus.ZUKUENFTIG, statuses[2]);
    }

    @Test
    public void testFahrtStatusWertOf() {
        assertEquals("FahrtStatus.valueOf(\"ABSOLVIERT\") sollte ABSOLVIERT zurückgeben", FahrtStatus.ABSOLVIERT, FahrtStatus.valueOf("ABSOLVIERT"));
        assertEquals("FahrtStatus.valueOf(\"AUF_FAHRT\") sollte AUF_FAHRT zurückgeben", FahrtStatus.AUF_FAHRT, FahrtStatus.valueOf("AUF_FAHRT"));
        assertEquals("FahrtStatus.valueOf(\"ZUKUENFTIG\") sollte ZUKUENFTIG zurückgeben", FahrtStatus.ZUKUENFTIG, FahrtStatus.valueOf("ZUKUENFTIG"));
    }

}
