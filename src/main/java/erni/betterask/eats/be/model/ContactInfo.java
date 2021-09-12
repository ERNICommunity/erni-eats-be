package erni.betterask.eats.be.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class ContactInfo {
    @NonNull
    public String id;

    @NonNull
    public String establishmentId;

    @NonNull
    public String address;

    @NonNull
    public List<OpenHours> openHours;

    @NonNull
    public String coordinates;
}
