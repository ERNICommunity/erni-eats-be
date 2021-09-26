package erni.betterask.eats.be.controller;

import erni.betterask.eats.be.model.Meal;
import erni.betterask.eats.be.model.Establishment;
import erni.betterask.eats.be.model.EstablishmentType;
import erni.betterask.eats.be.model.PriceLevel;
import erni.betterask.eats.be.model.MealType;
import erni.betterask.eats.be.service.EstablishmentConfigurationService;
import erni.betterask.eats.be.service.EstablishmentMenuService;
import erni.betterask.eats.be.service.EstablishmentReviewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebFluxTest(EstablishmentsController.class)
public class EstablishmentsControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    EstablishmentConfigurationService establishmentConfigurationService;
    @MockBean
    EstablishmentMenuService establishmentMenuService;
    @MockBean
    EstablishmentReviewsService establishmentReviewsService;

    @MockBean
    Clock clock;

    private Clock fixedClock;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
        fixedClock = Clock.fixed(LocalDate.of(2050, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }

    @Test
    public void shouldReturnEstablishments() {
        Establishment establishment = Establishment.builder()
                .id("id-1")
                .restaurantId("restaurantId")
                .name("My Restaurant")
                .description("The best ever")
                .type(EstablishmentType.RESTAURANT)
                .websiteUrl("https://ristorante.sk/")
                .dailyMenuUrl("https://ristorante.sk/denne-menu")
                .priceLevel(PriceLevel.MODERATE)
                .rating(4.9f)
                .userRatingsTotal(666)
                .build();
        when(establishmentConfigurationService.findAll()).thenReturn(List.of(establishment));

        webTestClient.get()
                .uri("/api/v1/establishments")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Establishment.class)
                .hasSize(1)
                .value(establishments -> establishments.get(0), equalTo(establishment));
    }

    @Test
    public void shouldReturnEstablishmentById() {
        Establishment establishment = Establishment.builder()
                .id("id-1")
                .restaurantId("restaurantId")
                .name("My Restaurant")
                .description("The best ever")
                .type(EstablishmentType.RESTAURANT)
                .websiteUrl("https://ristorante.sk/")
                .dailyMenuUrl("https://ristorante.sk/denne-menu")
                .priceLevel(PriceLevel.MODERATE)
                .rating(4.9f)
                .userRatingsTotal(666)
                .build();
        when(establishmentConfigurationService.findById(anyString())).thenReturn(Optional.of(establishment));

        webTestClient.get()
                .uri("/api/v1/establishments/id-1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Establishment.class)
                .value(result -> result, equalTo(establishment));
    }

    @Test
    public void shouldReturn404WhenEstablishmentIdIsNotFound() {
        when(establishmentConfigurationService.findById(anyString())).thenReturn(Optional.empty());

        webTestClient.get()
                .uri("/api/v1/establishments/id-2")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void shouldReturnDailyMenuForToday() {
        var meal = Meal.builder()
                .name("Gulas")
                .type(MealType.MAIN_DISH)
                .price(BigDecimal.TEN)
                .build();
        when(establishmentMenuService.getMeals("id-1", LocalDate.of(2020, 1, 1))).thenReturn(Mono.just(Collections.emptyList()));
        when(establishmentMenuService.getMeals("id-1", LocalDate.now(clock))).thenReturn(Mono.just(List.of(meal)));

        webTestClient.get()
                .uri("/api/v1/establishments/id-1/daily-menu")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Meal.class)
                .value(result -> result, equalTo(List.of(meal)));
    }

    @Test
    public void shouldReturnDailyMenuForPassedDate() {
        var date = LocalDate.of(2020, 1, 1);
        var meal = Meal.builder()
                .name("Gulas")
                .type(MealType.MAIN_DISH)
                .price(BigDecimal.TEN)
                .build();
        when(establishmentMenuService.getMeals("id-1", date)).thenReturn(Mono.just(List.of(meal)));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/establishments/id-1/daily-menu")
                        .queryParam("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE))
                        .build() )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Meal.class)
                .value(result -> result, equalTo(List.of(meal)));
    }

    @Test
    public void shouldReturnEstablishmentLogo() {
        Establishment establishment = Establishment.builder()
                .id("clock-block")
                .restaurantId("restaurantId")
                .name("My Restaurant")
                .description("The best ever")
                .type(EstablishmentType.RESTAURANT)
                .websiteUrl("https://ristorante.sk/")
                .dailyMenuUrl("https://ristorante.sk/denne-menu")
                .priceLevel(PriceLevel.MODERATE)
                .rating(4.9f)
                .userRatingsTotal(666)
                .build();
        when(establishmentConfigurationService.findById(anyString())).thenReturn(Optional.of(establishment));

        webTestClient.get()
                .uri("/api/v1/establishments/clock-block/logo")
                .accept(MediaType.IMAGE_PNG)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Resource.class);
    }
}