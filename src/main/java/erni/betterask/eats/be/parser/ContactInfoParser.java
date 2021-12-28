package erni.betterask.eats.be.parser;

import erni.betterask.eats.be.model.ContactInfo;
import erni.betterask.eats.be.model.OpenHours;
import erni.betterask.eats.be.parser.constant.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ContactInfoParser {

    private ContactInfoParser() {}

    public static ContactInfo getContactInfoParsed() {
        Document doc;
        List<OpenHours> openHours = null;
        String address = null;
        try {
            doc = Jsoup.parse(
                    new URL(Constant.CLOCK_BLOCK_CONTACT_INFO_URL).openStream(),
                    "UTF-8",
                    Constant.CLOCK_BLOCK_CONTACT_INFO_URL);

            Elements preElement = doc.select("pre");

            String openHoursContent = preElement.get(0).text();
            openHours = getOpenHours(openHoursContent);

            String addressContent = preElement.get(1).html();
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

    protected static List<OpenHours> getOpenHours(String openHoursContent) {
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

    protected static String getAddress(String addressContent) {
        List<String> addressFields = Arrays
                .stream(addressContent.split("\r\n"))
                .collect(Collectors.toList());
        return String.format("%s, %s", addressFields.get(1), addressFields.get(2));
    }

    protected static String clearCharsDay(String value) {
        return value.replaceAll("[:\\p{Z}]", "");
    }

    protected static String clearCharsTime(String value) {
        return value.replaceAll("[^0-9:]", "");
    }
}
