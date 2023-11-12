package at.jku.se.prse.services;

import at.jku.se.prse.model.Kategorie;
import java.util.List;


public interface KategorieService {
    void delete(Kategorie k);
    void save(Kategorie k);
    List<Kategorie> findAll();
}
