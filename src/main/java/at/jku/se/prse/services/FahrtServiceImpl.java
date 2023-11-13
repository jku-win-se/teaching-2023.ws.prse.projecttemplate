package at.jku.se.prse.services;

import at.jku.se.prse.model.Fahrt;
import at.jku.se.prse.repositories.FahrtRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FahrtServiceImpl implements FahrtService {

    @Autowired
    FahrtRepository repository;

    @Override
    public Fahrt save(Fahrt fahrt) {
        return repository.save(fahrt);
    }

    @Override
    public List<Fahrt> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Fahrt fahrt) {
        repository.delete(fahrt);
    }
}
