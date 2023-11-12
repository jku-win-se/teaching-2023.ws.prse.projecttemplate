package at.jku.se.prse.services;

import at.jku.se.prse.model.Kategorie;
import at.jku.se.prse.repositories.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategorieServiceImpl implements KategorieService{

    @Autowired
    private KategorieRepository katRepo;

    @Override
    public void delete(Kategorie k) {
        katRepo.delete(k);
    }

    @Override
    public void save(Kategorie k) {
        katRepo.save(k);
    }

    @Override
    public List<Kategorie> findAll() {
        return katRepo.findAll();
    }
}
