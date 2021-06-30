package erni.betterask.eats.be.controller;

import erni.betterask.eats.be.model.Establishment;
import erni.betterask.eats.be.model.Meal;
import erni.betterask.eats.be.service.EstablishmentConfigurationService;
import erni.betterask.eats.be.service.EstablishmentMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/establishments")
public class EstablishmentsController {
    private final EstablishmentConfigurationService establishmentConfigurationService;
    private final EstablishmentMenuService establishmentMenuService;

    @Autowired
    public EstablishmentsController(EstablishmentConfigurationService establishmentConfigurationService, EstablishmentMenuService establishmentMenuService) {
        super();
        this.establishmentConfigurationService = establishmentConfigurationService;
        this.establishmentMenuService = establishmentMenuService;
    }

    @GetMapping()
    public Mono<List<Establishment>> getAllEstablishments() {
        return Mono.just(establishmentConfigurationService.findAll());
    }

    @GetMapping(path = "/{establishmentId}")
    public Mono<Establishment> getEstablishment(@PathVariable String establishmentId) {
        return establishmentConfigurationService.findById(establishmentId)
                .map(Mono::just)
                .orElseThrow();
    }

    @GetMapping(path = "/{establishmentId}/daily-menu")
    public Mono<ResponseEntity<List<Meal>>> getDailyMenu(@PathVariable String establishmentId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
        date = Optional
                .ofNullable(date)
                .orElse(LocalDate.now());

        return establishmentMenuService.getMeals(establishmentId, date)
                .map(result -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(result));
    }
}
