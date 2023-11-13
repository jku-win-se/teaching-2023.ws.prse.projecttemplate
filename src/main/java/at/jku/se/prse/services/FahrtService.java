package at.jku.se.prse.services;

import at.jku.se.prse.model.Fahrt;

import java.util.List;

public interface FahrtService {

    Fahrt save(Fahrt fahrt);

    List<Fahrt> findAll();

    void delete(Fahrt fahrt);
}
