package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.ContactInfo;
import erni.betterask.eats.be.model.OpenHours;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentContactInfoServiceImpl implements EstablishmentContactInfoService {

    private static final List<ContactInfo> mockedContactInfo = List.of(
            ContactInfo.builder()
                    .id("00")
                    .establishmentId("clock-block")
                    .address("Zadunajská cesta 12, 851 01 Petržalka")
                    .openHours(List.of(
                            new OpenHours("pondelok","11:03–22"),
                            new OpenHours("utorok", "11:03–22"),
                            new OpenHours("streda", "11:03–22"),
                            new OpenHours("štvrtok", "11:03–22"),
                            new OpenHours("piatok", "11:03–0"),
                            new OpenHours("sobota", "15–0"),
                            new OpenHours("nedeľa", "15–22")
                    ))
                    .coordinates("48.1308528,17.1012008")
                    .build(),
            ContactInfo.builder()
                    .id("01")
                    .establishmentId("derby-pub")
                    .address("Nobelovo námestie 1, Nad Billou, 851 01 Petržalka")
                    .openHours(List.of(
                            new OpenHours("pondelok","11–0"),
                            new OpenHours("utorok", "11–0"),
                            new OpenHours("streda", "11–0"),
                            new OpenHours("štvrtok", "11–0"),
                            new OpenHours("piatok", "11–1:30"),
                            new OpenHours("sobota", "11:30–1"),
                            new OpenHours("nedeľa", "15–23")
                    ))
                    .coordinates("48.1304956,17.0977073")
                    .build(),
            ContactInfo.builder()
                    .id("02")
                    .establishmentId("mkm-pizza-restaurant")
                    .address("11, Pečnianska 1211, 851 01 Petržalka")
                    .openHours(List.of(
                            new OpenHours("pondelok","10–15"),
                            new OpenHours("utorok", "10–15"),
                            new OpenHours("streda", "10–15"),
                            new OpenHours("štvrtok", "10–15"),
                            new OpenHours("piatok", "10–15"),
                            new OpenHours("sobota", "Zatvorené"),
                            new OpenHours("nedeľa", "Zatvorené")
                    ))
                    .coordinates("48.1305038,17.0937977")
                    .build(),
            ContactInfo.builder()
                    .id("03")
                    .establishmentId("sidliskova-pivarnicka")
                    .address("Nobelovo námestie 7/8, 851 01 Petržalka")
                    .openHours(List.of(
                            new OpenHours("pondelok","11–22"),
                            new OpenHours("utorok", "11–22"),
                            new OpenHours("streda", "11–22"),
                            new OpenHours("štvrtok", "11–22"),
                            new OpenHours("piatok", "11–22"),
                            new OpenHours("sobota", "14–22"),
                            new OpenHours("nedeľa", "15–22")
                    ))
                    .coordinates("48.1294767,17.0990021")
                    .build(),
            ContactInfo.builder()
                    .id("04")
                    .establishmentId("positive-restaurant")
                    .address("Zadunajská cesta 3557/10, 851 01 Petržalka")
                    .openHours(List.of(
                            new OpenHours("pondelok","10–19"),
                            new OpenHours("utorok", "10–20"),
                            new OpenHours("streda", "10–19"),
                            new OpenHours("štvrtok", "10–19"),
                            new OpenHours("piatok", "10–20"),
                            new OpenHours("sobota", "10–22"),
                            new OpenHours("nedeľa", "Zatvorené")
                    ))
                    .coordinates("48.1305753,17.1007048")
                    .build(),
            ContactInfo.builder()
                    .id("05")
                    .establishmentId("bistronomy")
                    .address("Zadunajská cesta 348, 851 01 Petržalka")
                    .openHours(List.of(
                            new OpenHours("pondelok","11–14:30"),
                            new OpenHours("utorok", "11–14:30"),
                            new OpenHours("streda", "11–15, 17–21:30"),
                            new OpenHours("štvrtok", "11–15, 17–21:30"),
                            new OpenHours("piatok", "11–15, 17–21:30"),
                            new OpenHours("sobota", "11–15, 17–21:30"),
                            new OpenHours("nedeľa", "Zatvorené")
                    ))
                    .coordinates("48.1305753,17.1007048")
                    .build(),
            ContactInfo.builder()
                    .id("06")
                    .establishmentId("veg-life")
                    .address("Einsteinova 33, 851 01 Petržalka")
                    .openHours(List.of(
                            new OpenHours("pondelok","11–14"),
                            new OpenHours("utorok", "11–14"),
                            new OpenHours("streda", "11–14"),
                            new OpenHours("štvrtok", "11–14"),
                            new OpenHours("piatok", "11–14"),
                            new OpenHours("sobota", "Zatvorené"),
                            new OpenHours("nedeľa", "Zatvorené")
                    ))
                    .coordinates("48.1314919,17.0989739")
                    .build()
    );

    @Override
    public Optional<ContactInfo> findByEstablishmentId(String establishmentId) {
        return mockedContactInfo.stream()
                .filter(contactInfo -> contactInfo.establishmentId.equals(establishmentId))
                .findFirst();
    }
}
