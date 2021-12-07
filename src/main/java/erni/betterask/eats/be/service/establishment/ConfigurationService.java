package erni.betterask.eats.be.service.establishment;

import erni.betterask.eats.be.model.Establishment;

import java.util.List;
import java.util.Optional;

public interface ConfigurationService {
    List<Establishment> findAll();
    Optional<Establishment> findById(String id);
}
