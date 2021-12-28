package erni.betterask.eats.be.controller;

import erni.betterask.eats.be.model.Establishment;
import erni.betterask.eats.be.model.Meal;
import erni.betterask.eats.be.model.Review;
import erni.betterask.eats.be.service.establishment.ConfigurationService;
import erni.betterask.eats.be.service.establishment.MenuService;
import erni.betterask.eats.be.service.establishment.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final ConfigurationService configurationService;
    private final MenuService menuService;
    private final ReviewsService reviewsService;

    @Autowired
    public EstablishmentsController(
            Clock clock,
            @Qualifier("mockService")ConfigurationService configurationService,
            MenuService menuService,
            ReviewsService reviewsService) {
        super();
        this.clock = clock;
        this.configurationService = configurationService;
        this.menuService = menuService;
        this.reviewsService = reviewsService;
    }

    @GetMapping()
    public Mono<List<Establishment>> getAllEstablishmentConfigurations() {
        return Mono.just(configurationService.findAll());
    }

    @GetMapping(path = "/{establishmentId}")
    public Mono<Establishment> getEstablishmentConfiguration(@PathVariable String establishmentId) {
        return configurationService.findById(establishmentId)
                .map(Mono::just)
                .orElseThrow();
    }

    @GetMapping(path = "/{establishmentId}/daily-menu")
    public Mono<List<Meal>> getDailyMenu(@PathVariable String establishmentId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
        date = Optional
                .ofNullable(date)
                .orElse(LocalDate.now(this.clock));

        return menuService.getMeals(establishmentId, date);
    }

    @GetMapping(value = "/{establishmentId}/logo")
    @ResponseBody
    public ResponseEntity<Resource> getLogo(@PathVariable String establishmentId) {
        configurationService.findById(establishmentId)
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
        return reviewsService.findByEstablishmentId(establishmentId);
    }
}
