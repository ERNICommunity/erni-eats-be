package erni.betterask.eats.be.controller;

import erni.betterask.eats.be.model.Establishment;
import erni.betterask.eats.be.model.Meal;
import erni.betterask.eats.be.model.Review;
import erni.betterask.eats.be.service.EstablishmentConfigurationService;
import erni.betterask.eats.be.service.EstablishmentMenuService;
import erni.betterask.eats.be.service.EstablishmentReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/establishments")
public class EstablishmentsController {
    private final Clock clock;
    private final EstablishmentConfigurationService establishmentConfigurationService;
    private final EstablishmentMenuService establishmentMenuService;
    private final EstablishmentReviewsService establishmentReviewsService;

    @Autowired
    public EstablishmentsController(
            Clock clock,
            EstablishmentConfigurationService establishmentConfigurationService,
            EstablishmentMenuService establishmentMenuService,
            EstablishmentReviewsService establishmentReviewsService) {
        super();
        this.clock = clock;
        this.establishmentConfigurationService = establishmentConfigurationService;
        this.establishmentMenuService = establishmentMenuService;
        this.establishmentReviewsService = establishmentReviewsService;
    }

    @GetMapping()
    public Mono<List<Establishment>> getAllEstablishmentConfigurations() {
        return Mono.just(establishmentConfigurationService.findAll());
    }

    @GetMapping(path = "/{establishmentId}")
    public Mono<Establishment> getEstablishmentConfiguration(@PathVariable String establishmentId) {
        return establishmentConfigurationService.findById(establishmentId)
                .map(Mono::just)
                .orElseThrow();
    }

    @GetMapping(path = "/{establishmentId}/daily-menu")
    public Mono<List<Meal>> getDailyMenu(@PathVariable String establishmentId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
        date = Optional
                .ofNullable(date)
                .orElse(LocalDate.now(this.clock));

        return establishmentMenuService.getMeals(establishmentId, date);
    }

    @GetMapping(value = "/{establishmentId}/logo")
    @ResponseBody
    public ResponseEntity<Resource> getLogo(@PathVariable String establishmentId) {
        establishmentConfigurationService.findById(establishmentId)
                .map(Establishment::getId)
                .orElseThrow();

        var resource = new ClassPathResource("images/" + establishmentId + ".png");
        if (!resource.exists()) {
            throw new NoSuchElementException("Logo doesn't exist for given establishment");
        }

        var headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{establishmentId}/reviews")
    @ResponseBody
    public List<Review> getReviewsByEstablishmentId(@PathVariable String establishmentId) {
        return establishmentReviewsService.findByEstablishmentId(establishmentId);
    }
}
