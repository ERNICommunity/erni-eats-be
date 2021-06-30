package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.Establishment;

import java.util.List;
import java.util.Optional;

public interface EstablishmentConfigurationService {
    List<Establishment> findAll();
    Optional<Establishment> findById(String id);
}
