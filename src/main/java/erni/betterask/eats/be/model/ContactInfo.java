package erni.betterask.eats.be.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ContactInfo {
    @NonNull
    public String id;

    @NonNull
    public String establishmentId;

    @NonNull
    public String address;

    public String openHours;

    @NonNull
    public String coordinates;
}
