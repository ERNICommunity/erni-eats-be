package erni.betterask.eats.be.service.establishment.at11;

import erni.betterask.eats.be.model.Establishment;
import erni.betterask.eats.be.model.Meal;
import erni.betterask.eats.be.model.at11.MenuResult;
import erni.betterask.eats.be.service.establishment.ConfigurationService;
import erni.betterask.eats.be.service.establishment.MenuService;
import io.vavr.collection.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    private final ConfigurationService configurationService;
    private final WebClient webClient;

    @Autowired
    public MenuServiceImpl(WebClient.Builder webClientBuilder, @Value( "${at11.endpoint.url}") String at11Url, @Qualifier("parsedService")ConfigurationService configurationService) {
        this.webClient = webClientBuilder.baseUrl(at11Url).build();
        this.configurationService = configurationService;
    }

    @Override
    public Mono<List<Meal>> getMeals(String establishmentId, LocalDate date) {
        var restaurantId = configurationService.findById(establishmentId).map(Establishment::getRestaurantId).getOrElseThrow(NoSuchElementException::new);

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
                        .collect(List.collector())
                );
    }
}
