package at.jku.se.prse.views;


import at.jku.se.prse.model.Fahrt;
import at.jku.se.prse.services.FahrtService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.View;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@View
public class AdministrationView {

    @Autowired
    FahrtService fahrtService;

    @Getter
    @Setter
    private List<Fahrt> fahrten;

    @Getter
    @Setter
    private Fahrt newFahrt;

    @PostConstruct
    public void initFahrten() {
        fahrten = fahrtService.findAll();
        newFahrt = new Fahrt();
    }

    public void saveNewFahrt() {
        fahrtService.save(newFahrt);
        initFahrten();
    }
}
