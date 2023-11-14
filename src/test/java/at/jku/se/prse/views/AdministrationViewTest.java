package at.jku.se.prse.views;

import at.jku.se.prse.enums.Status;
import at.jku.se.prse.model.Fahrt;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AdministrationViewTest {
    @Test
    public void testGetStatusAbsolviert1(){
        Fahrt fahrt = new Fahrt();
        fahrt.setDate(LocalDate.now().minusWeeks(1));
        fahrt.setDepTime(LocalTime.now().minusHours(5));
        fahrt.setArrTime(LocalTime.now());
        assertEquals(Status.ABSOLVIERT, fahrt.getStatus());
    }

    @Test
    public void testGetStatusAbsolviert2(){
        Fahrt fahrt = new Fahrt();
        fahrt.setDate(LocalDate.now());
        fahrt.setDepTime(LocalTime.now().minusHours(5));
        fahrt.setArrTime(LocalTime.now().minusHours(2));
        assertEquals(Status.ABSOLVIERT, fahrt.getStatus());
    }

    @Test
    public void testGetStatusAufFahrt(){
        Fahrt fahrt = new Fahrt();
        fahrt.setDate(LocalDate.now());
        fahrt.setDepTime(LocalTime.now().minusHours(3));
        fahrt.setArrTime(LocalTime.now().plusMinutes(3));
        assertEquals(Status.AUF_FAHRT, fahrt.getStatus());
    }

    @Test
    public void testGetStatusZukuenftig1(){
        Fahrt fahrt = new Fahrt();
        fahrt.setDate(LocalDate.now().plusWeeks(1));
        fahrt.setDepTime(LocalTime.now());
        fahrt.setArrTime(LocalTime.now().plusMinutes(2));
        assertEquals(Status.ZUKUENFTIG, fahrt.getStatus());
    }

    @Test
    public void testGetStatusZukuenftig2(){
        Fahrt fahrt = new Fahrt();
        fahrt.setDate(LocalDate.now());
        fahrt.setDepTime(LocalTime.now().plusMinutes(2));
        fahrt.setArrTime(LocalTime.now().plusMinutes(4));
        assertEquals(Status.ZUKUENFTIG, fahrt.getStatus());
    }
}