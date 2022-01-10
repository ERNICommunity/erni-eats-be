package erni.betterask.eats.be.service.establishment.parser;

import erni.betterask.eats.be.model.ContactInfo;
import erni.betterask.eats.be.model.OpenHours;
import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ContactInfoParser {

    public static final String CLOCK_BLOCK_WEB_URL = "https://clockblock.sk";
    public static final String CLOCK_BLOCK_CONTACT_INFO_URL = "https://clockblock.sk/KONTAKT";

    private ContactInfoParser() {
    }

    public static ContactInfo getContactInfoParsed() {
        var address = "";
        var openHours = List.empty();
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

        return ContactInfo.builder()
                .id("00")
                .establishmentId("clock-block")
                .address(address)
                .openHours(openHours)
                .coordinates("48.1308528,17.1012008")
                .build();
    }

    public static List<OpenHours> getOpenHours(String openHoursContent) {
        return Arrays
                .stream(openHoursContent.split("\r\n"))
                .map(weekday -> weekday.split("\\s+"))
                .map(dayTimePerWeekday -> new OpenHours(
                        clearCharsDay(dayTimePerWeekday[0]),
                        String.format(
                                "%s–%s",
                                clearCharsTime(dayTimePerWeekday[1]),
                                clearCharsTime(dayTimePerWeekday[3]))))
                .collect(Collectors.toList());
    }

    public static String getAddress(String addressContent) {
        return addressContent.replaceAll("\r\n","");
    }

    public static String clearCharsDay(String value) {
        return value.replaceAll("[:\\p{Z}]", "");
    }

    public static String clearCharsTime(String value) {
        return value.replaceAll("[^0-9:]", "");
    }
}
