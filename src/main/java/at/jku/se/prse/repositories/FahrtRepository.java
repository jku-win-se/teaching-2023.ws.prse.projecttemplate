package at.jku.se.prse.repositories;

import at.jku.se.prse.model.Fahrt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FahrtRepository extends JpaRepository<Fahrt, Integer> {

}