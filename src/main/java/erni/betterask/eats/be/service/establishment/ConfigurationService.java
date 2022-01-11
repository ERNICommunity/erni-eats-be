package erni.betterask.eats.be.service.establishment;

import erni.betterask.eats.be.model.Establishment;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface ConfigurationService {
    List<Establishment> findAll();
    Option<Establishment> findById(String id);
}
