package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.Meal;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface EstablishmentMenuService {
    Mono<List<Meal>> getMeals(String establishmentId, LocalDate date);
}
