package erni.betterask.eats.be.parser;

import erni.betterask.eats.be.service.establishment.parser.ContactInfoParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ContactInfoParserTest {

    @Test
    void getOpenHoursTest() {
        var content = """
                Pondelok: 11:00 - 22:00 hod\r
                Utorok:   11:03 - 22:00 hod\r
                Streda:   11:03 - 22:00 hod\r
                štvrtok:  11:03 - 22:00 hod\r
                Piatok:   11:03 - 00:00 hod\r
                sobota:   15:03 - 00:00 hod\r
                nedeľa:  15:03 - 22:00 hod""";

        // exercise
        var result = ContactInfoParser.getOpenHours(content);

        // verify
        assertThat(result.size()).isEqualTo(7);
        assertThat(result.get(0).getDay()).isEqualTo("Pondelok");
        assertThat(result.get(0).getOpenHours()).isEqualTo("11:00–22:00");
        assertThat(result.get(1).getDay()).isEqualTo("Utorok");
        assertThat(result.get(1).getOpenHours()).isEqualTo("11:03-22:00");
        assertThat(result.get(2).getDay()).isEqualTo("Streda");
        assertThat(result.get(2).getOpenHours()).isEqualTo("11:03-22:00");
        assertThat(result.get(3).getDay()).isEqualTo("štvrtok");
        assertThat(result.get(3).getOpenHours()).isEqualTo("11:03-22:00");
        assertThat(result.get(4).getDay()).isEqualTo("Piatok");
        assertThat(result.get(4).getOpenHours()).isEqualTo("11:03-00:00");
        assertThat(result.get(5).getDay()).isEqualTo("sobota");
        assertThat(result.get(5).getOpenHours()).isEqualTo("15:03-00:00");
        assertThat(result.get(6).getDay()).isEqualTo("nedeľa");
        assertThat(result.get(6).getOpenHours()).isEqualTo("15:03-22:00");
    }

    @Test
    void getAddressTest() {
        var content = """
                CLOCK-BLOCK Localbeer &amp; Restaurant\r
                Zadunajská cesta 12\r
                851 01 Bratislava""";

        // exercise
        var result = ContactInfoParser.getAddress(content);

        // verify
        assertEquals("Zadunajská cesta 12, 851 01 Bratislava", result);
    }

    @Test
    void clearCharsDayTest() {
        var input = "štvrtok:  ";

        // exercise
        var result = ContactInfoParser.clearCharsDay(input);

        // verify
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
