package erni.betterask.eats.be.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Builder
public class ContactInfo {
    @NonNull
    public String id;

    @NonNull
    public String establishmentId;

    @Nullable
    public String address;

    @Nullable
    public List<OpenHours> openHours;

    @NonNull
    public String coordinates;
}
