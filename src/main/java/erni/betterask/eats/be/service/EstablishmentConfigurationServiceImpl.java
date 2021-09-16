package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.*;
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
                    .contactInfo(
                            ContactInfo.builder()
                                    .id("00")
                                    .establishmentId("clock-block")
                                    .address("Zadunajská cesta 12, 851 01 Petržalka")
                                    .openHours(List.of(
                                            new OpenHours("pondelok", "11:03–22"),
                                            new OpenHours("utorok", "11:03–22"),
                                            new OpenHours("streda", "11:03–22"),
                                            new OpenHours("štvrtok", "11:03–22"),
                                            new OpenHours("piatok", "11:03–0"),
                                            new OpenHours("sobota", "15–0"),
                                            new OpenHours("nedeľa", "15–22")
                                    ))
                                    .coordinates("48.1308528,17.1012008")
                                    .build()
                    )
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
                    .contactInfo(
                            ContactInfo.builder()
                                    .id("01")
                                    .establishmentId("derby-pub")
                                    .address("Nobelovo námestie 1, Nad Billou, 851 01 Petržalka")
                                    .openHours(List.of(
                                            new OpenHours("pondelok", "11–0"),
                                            new OpenHours("utorok", "11–0"),
                                            new OpenHours("streda", "11–0"),
                                            new OpenHours("štvrtok", "11–0"),
                                            new OpenHours("piatok", "11–1:30"),
                                            new OpenHours("sobota", "11:30–1"),
                                            new OpenHours("nedeľa", "15–23")
                                    ))
                                    .coordinates("48.1304956,17.0977073")
                                    .build()
                    )
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
                    .contactInfo(
                            ContactInfo.builder()
                                    .id("02")
                                    .establishmentId("mkm-pizza-restaurant")
                                    .address("11, Pečnianska 1211, 851 01 Petržalka")
                                    .openHours(List.of(
                                            new OpenHours("pondelok", "10–15"),
                                            new OpenHours("utorok", "10–15"),
                                            new OpenHours("streda", "10–15"),
                                            new OpenHours("štvrtok", "10–15"),
                                            new OpenHours("piatok", "10–15"),
                                            new OpenHours("sobota", "Zatvorené"),
                                            new OpenHours("nedeľa", "Zatvorené")
                                    ))
                                    .coordinates("48.1305038,17.0937977")
                                    .build()
                    )
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
                    .contactInfo(
                            ContactInfo.builder()
                                    .id("03")
                                    .establishmentId("sidliskova-pivarnicka")
                                    .address("Nobelovo námestie 7/8, 851 01 Petržalka")
                                    .openHours(List.of(
                                            new OpenHours("pondelok", "11–22"),
                                            new OpenHours("utorok", "11–22"),
                                            new OpenHours("streda", "11–22"),
                                            new OpenHours("štvrtok", "11–22"),
                                            new OpenHours("piatok", "11–22"),
                                            new OpenHours("sobota", "14–22"),
                                            new OpenHours("nedeľa", "15–22")
                                    ))
                                    .coordinates("48.1294767,17.0990021")
                                    .build()
                    )
                    .build(),
            Establishment.builder()
                    .id("positive-restaurant")
                    .restaurantId("einpark-6")
                    .name("Positive Restaurant")
                    .type(EstablishmentType.RESTAURANT)
                    .websiteUrl("https://www.positiverestaurant.sk/")
                    .dailyMenuUrl("https://www.instagram.com/positiverestaurant/")
                    .priceLevel(PriceLevel.MODERATE)
                    .rating(4.4f)
                    .userRatingsTotal(89)
                    .contactInfo(
                            ContactInfo.builder()
                                    .id("04")
                                    .establishmentId("positive-restaurant")
                                    .address("Zadunajská cesta 3557/10, 851 01 Petržalka")
                                    .openHours(List.of(
                                            new OpenHours("pondelok", "10–19"),
                                            new OpenHours("utorok", "10–20"),
                                            new OpenHours("streda", "10–19"),
                                            new OpenHours("štvrtok", "10–19"),
                                            new OpenHours("piatok", "10–20"),
                                            new OpenHours("sobota", "10–22"),
                                            new OpenHours("nedeľa", "Zatvorené")
                                    ))
                                    .coordinates("48.1305753,17.1007048")
                                    .build()
                    )
                    .build(),
            Establishment.builder()
                    .id("bistronomy")
                    .restaurantId("einpark-7")
                    .name("Bistronomy")
                    .type(EstablishmentType.RESTAURANT)
                    .websiteUrl("https://bistronomy.sk/")
                    .dailyMenuUrl("https://bistronomy.sk/aktualnemenu/")
                    .priceLevel(PriceLevel.VERY_EXPENSIVE)
                    .rating(4.9f)
                    .userRatingsTotal(36)
                    .contactInfo(
                            ContactInfo.builder()
                                    .id("05")
                                    .establishmentId("bistronomy")
                                    .address("Zadunajská cesta 348, 851 01 Petržalka")
                                    .openHours(List.of(
                                            new OpenHours("pondelok", "11–14:30"),
                                            new OpenHours("utorok", "11–14:30"),
                                            new OpenHours("streda", "11–15, 17–21:30"),
                                            new OpenHours("štvrtok", "11–15, 17–21:30"),
                                            new OpenHours("piatok", "11–15, 17–21:30"),
                                            new OpenHours("sobota", "11–15, 17–21:30"),
                                            new OpenHours("nedeľa", "Zatvorené")
                                    ))
                                    .coordinates("48.1305753,17.1007048")
                                    .build()
                    )
                    .build(),
            Establishment.builder()
                    .id("veg-life")
                    .restaurantId("einpark-8")
                    .name("Veg Life")
                    .type(EstablishmentType.RESTAURANT)
                    .websiteUrl("https://www.veglife.sk/sk/")
                    .dailyMenuUrl("https://www.veglife.sk/sk/")
                    .priceLevel(PriceLevel.MODERATE)
                    .rating(4.9f)
                    .userRatingsTotal(36)
                    .contactInfo(
                            ContactInfo.builder()
                                    .id("06")
                                    .establishmentId("veg-life")
                                    .address("Einsteinova 33, 851 01 Petržalka")
                                    .openHours(List.of(
                                            new OpenHours("pondelok", "11–14"),
                                            new OpenHours("utorok", "11–14"),
                                            new OpenHours("streda", "11–14"),
                                            new OpenHours("štvrtok", "11–14"),
                                            new OpenHours("piatok", "11–14"),
                                            new OpenHours("sobota", "Zatvorené"),
                                            new OpenHours("nedeľa", "Zatvorené")
                                    ))
                                    .coordinates("48.1314919,17.0989739")
                                    .build()
                    )
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
