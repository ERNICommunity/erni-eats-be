package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.ContactInfo;
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
                    .openHours("11:03AM–10PM")
                    .coordinates("48.1308528,17.1012008")
                    .build(),
            ContactInfo.builder()
                    .id("01")
                    .establishmentId("derby-pub")
                    .address("Nobelovo námestie 1, Nad Billou, 851 01 Petržalka")
                    .openHours("11AM–12AM")
                    .coordinates("48.1304956,17.0977073")
                    .build(),
            ContactInfo.builder()
                    .id("02")
                    .establishmentId("mkm-pizza-restaurant")
                    .address("11, Pečnianska 1211, 851 01 Petržalka")
                    .openHours("10AM–3PM")
                    .coordinates("48.1305038,17.0937977")
                    .build(),
            ContactInfo.builder()
                    .id("03")
                    .establishmentId("classic-restaurant-pub")
                    .address("Pečnianska 3430/6, 851 01 Petržalka")
                    .openHours("Temporarily closed")
                    .coordinates("48.1296472,17.0919258")
                    .build(),
            ContactInfo.builder()
                    .id("04")
                    .establishmentId("sidliskova-pivarnicka")
                    .address("Nobelovo námestie 7/8, 851 01 Petržalka")
                    .openHours("11AM–10PM")
                    .coordinates("48.1294767,17.0990021")
                    .build(),
            ContactInfo.builder()
                    .id("05")
                    .establishmentId("positive-restaurant")
                    .address("Zadunajská cesta 3557/10, 851 01 Petržalka")
                    .openHours("10AM–7PM")
                    .coordinates("48.1305753,17.1007048")
                    .build(),
            ContactInfo.builder()
                    .id("06")
                    .establishmentId("bistronomy")
                    .address("Zadunajská cesta 348, 851 01 Petržalka")
                    .openHours("11AM–2:30PM")
                    .coordinates("48.1305753,17.1007048")
                    .build(),
            ContactInfo.builder()
                    .id("07")
                    .establishmentId("veg-life")
                    .address("Einsteinova 33, 851 01 Petržalka")
                    .openHours("11AM–2PM")
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
