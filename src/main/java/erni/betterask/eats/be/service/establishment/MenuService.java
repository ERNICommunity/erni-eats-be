package erni.betterask.eats.be.service.establishment;

import erni.betterask.eats.be.model.Meal;
import io.vavr.collection.List;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface MenuService {
    Mono<List<Meal>> getMeals(String establishmentId, LocalDate date);
}
