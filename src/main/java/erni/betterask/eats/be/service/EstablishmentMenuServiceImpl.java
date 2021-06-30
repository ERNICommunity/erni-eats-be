package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.Establishment;
import erni.betterask.eats.be.model.Meal;
import erni.betterask.eats.be.model.at11.MenuResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstablishmentMenuServiceImpl implements EstablishmentMenuService {
    private final Logger logger = LoggerFactory.getLogger(EstablishmentMenuServiceImpl.class);
    private final EstablishmentConfigurationService establishmentConfigurationService;
    private final WebClient webClient;

    @Autowired
    public EstablishmentMenuServiceImpl(WebClient.Builder webClientBuilder, @Value( "${at11.endpoint.url}") String at11Url, EstablishmentConfigurationService establishmentConfigurationService) {
        this.webClient = webClientBuilder.baseUrl(at11Url).build();
        this.establishmentConfigurationService = establishmentConfigurationService;
    }

    @Override
    public Mono<List<Meal>> getMeals(String establishmentId, LocalDate date) {
        var restaurantId = establishmentConfigurationService.findById(establishmentId).map(Establishment::getRestaurantId).orElseThrow();

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/menu/{restaurantId}")
                        .queryParam("date", date.format(DateTimeFormatter.ISO_DATE))
                        .build(restaurantId))
                .retrieve()
                .bodyToMono(MenuResult.class)
                .doOnNext(response -> logger.debug(response.toString()))
                .map(MenuResult::getMenu)
                .map(menu -> menu.stream()
                        .map(Meal::fromMenuItem)
                        .collect(Collectors.toList())
                );
    }
}
