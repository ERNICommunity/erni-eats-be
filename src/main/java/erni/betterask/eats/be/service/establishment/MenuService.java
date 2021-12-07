package erni.betterask.eats.be.service.establishment;

import erni.betterask.eats.be.model.Meal;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {
    Mono<List<Meal>> getMeals(String establishmentId, LocalDate date);
}
