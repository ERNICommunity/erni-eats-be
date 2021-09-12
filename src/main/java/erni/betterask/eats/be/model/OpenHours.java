package erni.betterask.eats.be.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class OpenHours {
    @NonNull
    public String day;

    @NonNull
    public String openHours;

    public OpenHours(@NonNull String day, @NonNull String openHours) {
        this.day = day;
        this.openHours = openHours;
    }
}
