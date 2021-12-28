package erni.betterask.eats.be.parser;

import erni.betterask.eats.be.model.OpenHours;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactInfoParserTest {

    @Test
    void getOpenHoursTest() {
        // given
        String openHoursContent = """
                Pondelok: 11:00 - 22:00 hod\r
                Utorok:   11:03 - 22:00 hod\r
                Streda:   11:03 - 22:00 hod\r
                štvrtok:  11:03 - 22:00 hod\r
                Piatok:   11:03 - 00:00 hod\r
                sobota:   15:03 - 00:00 hod\r
                nedeľa:  15:03 - 22:00 hod""";

        // when
        List<OpenHours> result = ContactInfoParser.getOpenHours(openHoursContent);

        // then
        assertEquals(7, result.size());
        assertEquals("Pondelok", result.get(0).getDay());
        assertEquals("11:00–22:00", result.get(0).getOpenHours());
        assertEquals("Piatok", result.get(4).getDay());
        assertEquals("11:03–00:00", result.get(4).getOpenHours());
        assertEquals("nedeľa", result.get(6).getDay());
        assertEquals("15:03–22:00", result.get(6).getOpenHours());
    }

    @Test
    void getAddressTest() {
        // given
        String addressContent = """
                CLOCK-BLOCK Localbeer &amp; Restaurant\r
                Zadunajská cesta 12\r
                851 01 Bratislava""";

        // when
        String result = ContactInfoParser.getAddress(addressContent);

        // then
        assertEquals("Zadunajská cesta 12, 851 01 Bratislava", result);
    }

    @Test
    void clearCharsDayTest() {
        // given
        String input = "štvrtok:  ";

        // when
        String result = ContactInfoParser.clearCharsDay(input);

        // then
        assertEquals("štvrtok", result);
    }

    @Test
    void clearCharsTimeTest() {
        // given
        String input = "  11:03";

        // when
        String result = ContactInfoParser.clearCharsTime(input);

        // then
        assertEquals("11:03", result);
    }
}
