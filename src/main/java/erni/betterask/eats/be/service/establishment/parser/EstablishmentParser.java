package erni.betterask.eats.be.service.establishment.parser;

import erni.betterask.eats.be.model.Establishment;
import erni.betterask.eats.be.model.EstablishmentType;
import erni.betterask.eats.be.model.OpenHours;
import erni.betterask.eats.be.model.PriceLevel;
import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Slf4j
@Component
public class EstablishmentParser {

    public static final String CLOCK_BLOCK_WEB_URL = "https://clockblock.sk";
    public static final String CLOCK_BLOCK_CONTACT_INFO_URL = "https://clockblock.sk/KONTAKT";

    public Establishment getEstablishmentParsed() {
        var address = "";
        var openHours = List.of(new OpenHours("",""));
        try {
            var doc = Jsoup.parse(
                    new URL(CLOCK_BLOCK_CONTACT_INFO_URL).openStream(),
                    "UTF-8",
                    CLOCK_BLOCK_CONTACT_INFO_URL);

            var preElement = doc.select("pre");

            var openHoursContent = preElement.get(0).text();
            openHours = getOpenHours(openHoursContent);

            var addressContent = preElement.get(1).html();
            address = getAddress(addressContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Establishment.builder()
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
                .address(address)
                .openHours(openHours)
                .build();
    }

    public List<OpenHours> getOpenHours(String openHoursContent) {
        return List.of(openHoursContent.split("\r\n"))
                .map(weekday -> weekday.split("\\s+"))
                .map(dayTimePerWeekday -> new OpenHours(
                        clearCharsDay(dayTimePerWeekday[0]),
                        String.format(
                                "%s–%s",
                                clearCharsTime(dayTimePerWeekday[1]),
                                clearCharsTime(dayTimePerWeekday[3])))
                );
    }

    public String getAddress(String addressContent) {

        return List.of(addressContent.split("\r\n")).removeAt(0).mkString(", ");
    }

    public static String clearCharsDay(String value) {
        return value.replaceAll("[:\\p{Z}]", "");
    }

    public static String clearCharsTime(String value) {
        return value.replaceAll("[^0-9:]", "");
    }
}
