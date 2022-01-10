package erni.betterask.eats.be.service.establishment.parser;

import erni.betterask.eats.be.model.*;
import erni.betterask.eats.be.service.establishment.ConfigurationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("parsedService")
public class ConfigurationServiceImpl implements ConfigurationService {
    @Override
    public List<Establishment> findAll() {
        return establishments;
    }

    @Override
    public Optional<Establishment> findById(String id) {
        return establishments.stream()
                .filter(establishment -> establishment.id.equals(id))
                .findFirst();
    }

    private static final List<Establishment> establishments = List.of(
            Establishment.builder()
                    .id("clock-block")
                    .restaurantId("einpark-1")
                    .name("Clock Block")
                    .description("Localbeer & Restaurant")
                    .type(EstablishmentType.RESTAURANT)
                    .websiteUrl("https://clockblock.sk/")
                    .dailyMenuUrl("https://restauracie.sme.sk/restauracia/clock-block_8537-petrzalka_664/denne-menu")
                    .priceLevel(PriceLevel.MODERATE)
                    .rating(4.5f)
                    .userRatingsTotal(495)
                    .contactInfo(ContactInfoParser.getContactInfoParsed())
                    .build()
    );

}
