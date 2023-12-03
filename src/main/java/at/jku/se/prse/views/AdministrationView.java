package at.jku.se.prse.views;


import at.jku.se.prse.enums.Wiederholung;
import at.jku.se.prse.model.Fahrt;
import at.jku.se.prse.model.Kategorie;
import at.jku.se.prse.services.KategorieService;
import at.jku.se.prse.services.FahrtService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.View;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoField;
import java.util.List;

@Component
@View
public class AdministrationView {

    @Autowired
    FahrtService fahrtService;

    @Autowired
    KategorieService kategorieService;

    @Getter
    @Setter
    private List<Fahrt> fahrten;

    @Getter
    @Setter
    private List<Kategorie> kategorien;

    @Getter
    @Setter
    private Fahrt newFahrt;

    @PostConstruct
    public void initAll() {
        initKategorien();
        initFahrten();
    }
    public void initFahrten() {
        fahrten = fahrtService.findAll();
        newFahrt = new Fahrt();
    }
    public void initKategorien() {
        kategorien = kategorieService.findAll();
    }

    public void saveNewFahrt() {
        //Issue #34
        if(newFahrt.getArrTime()!=null && newFahrt.getDepTime()!=null && newFahrt.getTimeStood()!=null && newFahrt.getRiddenKM()!=null) {
            newFahrt.setAverageSpeed(newFahrt.getRiddenKM() / aktiveFahrzeit(newFahrt));
        }
        else newFahrt.setAverageSpeed(0.0);
        //End Issue #34

        fahrtService.save(newFahrt);

        //Issue #6
        if(newFahrt.getNumberOfRepetitions() > 1 && newFahrt.getRepetition() != Wiederholung.NICHT_DEFINIERT) {
            Fahrt fahrt = new Fahrt();
            fahrt = setAdditionalFahrt(fahrt);
            if(fahrt.getRepetition() == Wiederholung.WOECHENTLICH) repetitionWeekly(fahrt);
            else if(fahrt.getRepetition() == Wiederholung.MONATLICH) repetitionMonthly(fahrt);
            else if(fahrt.getRepetition() == Wiederholung.JAEHRLICH) repetitionYearly(fahrt);
        }
        //End Issue #6

        initFahrten();
    }

    //Issue #6
    public Fahrt setAdditionalFahrt(Fahrt fahrt){
        fahrt.setCarPlate(newFahrt.getCarPlate());
        fahrt.setDate(newFahrt.getDate());
        fahrt.setDepTime(newFahrt.getDepTime());
        fahrt.setArrTime(newFahrt.getArrTime());
        fahrt.setRiddenKM(newFahrt.getRiddenKM());
        fahrt.setTimeStood(newFahrt.getTimeStood());
        fahrt.setAverageSpeed(newFahrt.getAverageSpeed());
        //fahrt.setCategories(newFahrt.getCategories());
        fahrt.setRepetition(newFahrt.getRepetition());
        fahrt.setNumberOfRepetitions(newFahrt.getNumberOfRepetitions());
        return fahrt;
    }

    //Issue #6
    public void repetitionWeekly(Fahrt fahrt){
        newFahrt = fahrt;
        newFahrt.setDate(fahrt.getDate().plusDays(7));
        newFahrt.setNumberOfRepetitions(fahrt.getNumberOfRepetitions() - 1);
        saveNewFahrt();
    }

    //Issue #6
    public void repetitionMonthly(Fahrt fahrt){                 //Monthly is equivalent to 4 weeks, because otherwise it would not be the same weekday
        newFahrt = fahrt;
        newFahrt.setDate(fahrt.getDate().plusWeeks(4));
        newFahrt.setNumberOfRepetitions(fahrt.getNumberOfRepetitions() - 1);
        saveNewFahrt();
    }

    //Issue #6
    public void repetitionYearly(Fahrt fahrt){
        newFahrt = fahrt;
        newFahrt.setDate(fahrt.getDate().plusYears(1));
        newFahrt.setNumberOfRepetitions(fahrt.getNumberOfRepetitions() - 1);
        saveNewFahrt();
    }

    public void rowEditFahrt(RowEditEvent<Fahrt> event) {
        fahrtService.save(event.getObject());
        FacesMessage msg = new FacesMessage("Edited", "Fahrt " + event.getObject().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initFahrten();
    }
    public void rowCancelFahrt(RowEditEvent<Fahrt> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Fahrt " + event.getObject().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void onRowEditKat(RowEditEvent<Kategorie> event) {
        kategorieService.save(event.getObject());
        FacesMessage msg = new FacesMessage("Edited", "Kategorie " + event.getObject().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initKategorien();
    }
    public void onRowCancelKat(RowEditEvent<Kategorie> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Kategorie " + event.getObject().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void addNewKategorie() {
        Kategorie newKat = new Kategorie();
        kategorieService.save(newKat);
        initKategorien();
    }

    public void deleteKategorie(Kategorie kat) {
        if (kat.getFahrten().isEmpty()) {
            kategorieService.delete(kat);
            initKategorien();
            FacesMessage msg = new FacesMessage("Kategorie gelöscht", "Kategorie " + kat.getName() + " gelöscht.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Löschen fehlgeschlagen", "Kategorie wird verwendet.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //Issue #5
    public void deleteFahrt(Fahrt fahrt) {
        fahrtService.delete(fahrt);
        initFahrten();
    }

    //Issue #34
    public double aktiveFahrzeit(Fahrt newFahrt){
        int arrMinutes = newFahrt.getArrTime().getHour()*60 + newFahrt.getArrTime().getMinute();
        int depMinutes = newFahrt.getDepTime().getHour()*60 + newFahrt.getDepTime().getMinute();
        int stoodMinutes = newFahrt.getTimeStood().getHour()*60 + newFahrt.getTimeStood().getMinute();
        double arrTime = (double) arrMinutes;
        double depTime = (double) depMinutes;
        double stoodTime = (double) stoodMinutes;
        double time = (arrTime - depTime - stoodTime) / 60.0;
        return time;
    }
}
