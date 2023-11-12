package at.jku.se.prse.views;


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
        fahrtService.save(newFahrt);
        initFahrten();
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
}
