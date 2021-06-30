package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.Establishment;
import erni.betterask.eats.be.model.EstablishmentType;
import erni.betterask.eats.be.model.PriceLevel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentConfigurationServiceImpl implements EstablishmentConfigurationService {
    private static final List<Establishment> mockedEstablishments = List.of(
            Establishment.builder()
                    .id("clock-block")
                    .restaurantId("einpark-1")
                    .name("Clock Block")
                    .description("Local beer & restaurant")
                    .type(EstablishmentType.RESTAURANT)
                    .websiteUrl("https://clockblock.sk/")
                    .dailyMenuUrl("https://restauracie.sme.sk/restauracia/clock-block_8537-petrzalka_664/denne-menu")
                    .priceLevel(PriceLevel.MODERATE)
                    .rating(4.5f)
                    .userRatingsTotal(495)
                    .build(),
            Establishment.builder()
                    .id("derby-pub")
                    .restaurantId("einpark-2")
                    .name("Derby Pub")
                    .type(EstablishmentType.RESTAURANT)
                    .websiteUrl("https://www.derbypub.sk/")
                    .dailyMenuUrl("https://www.derbypub.sk/menu/obedove-menu")
                    .priceLevel(PriceLevel.MODERATE)
                    .rating(4.2f)
                    .userRatingsTotal(366)
                    .build(),
            Establishment.builder()
                    .id("mkm-pizza-restaurant")
                    .restaurantId("einpark-3")
                    .name("MKM Pizza Restaurant")
                    .type(EstablishmentType.RESTAURANT)
                    .websiteUrl("https://www.facebook.com/mkmpizzarestaurant/")
                    .dailyMenuUrl("https://restauracie.sme.sk/restauracia/mkm-pizzeria_1742-petrzalka_664/denne-menu")
                    .priceLevel(PriceLevel.INEXPENSIVE)
                    .rating(4.2f)
                    .userRatingsTotal(124)
                    .build(),
            Establishment.builder()
                    .id("classic-restaurant-pub")
                    .restaurantId("einpark-4")
                    .name("Classic restaurant & pub")
                    .type(EstablishmentType.RESTAURANT)
                    .websiteUrl("https://www.facebook.com/ClassicRestaurantPub/")
                    .dailyMenuUrl("https://restauracie.sme.sk/restauracia/classic-restaurant-pub_626-petrzalka_664")
                    .priceLevel(PriceLevel.MODERATE)
                    .rating(4.1f)
                    .userRatingsTotal(238)
                    .build(),
            Establishment.builder()
                    .id("sidliskova-pivarnicka")
                    .restaurantId("einpark-5")
                    .name("Sídliskova pivarnička")
                    .type(EstablishmentType.PUB)
                    .websiteUrl("https://www.facebook.com/S%C3%ADdliskova-pivarni%C4%8Dka-1765102690459713/")
                    .dailyMenuUrl("https://menucka.sk/denne-menu/bratislava/sidliskova-pivarnicka")
                    .priceLevel(PriceLevel.INEXPENSIVE)
                    .rating(4.2f)
                    .userRatingsTotal(91)
                    .build()
    );

    @Override
    public List<Establishment> findAll() {
        return mockedEstablishments;
    }

    @Override
    public Optional<Establishment> findById(String id) {
        return mockedEstablishments.stream()
                .filter(establishment -> establishment.id.equals(id))
                .findFirst();
    }
}
