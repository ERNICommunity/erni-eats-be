package erni.betterask.eats.be.model;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
@Builder
public class Establishment {
    @NonNull
    public String id;

    /** ID used by the at11 system. */
    @NonNull
    public String restaurantId;

    @NonNull
    public String name;

    /** Alternative name of the establishment. */
    public String alias;

    /** Some description to the establishment. */
    public String description;

    public EstablishmentType type;

    /** Main website of the establishment. */
    public String websiteUrl;

    /** Website used for parsing the daily menu. */
    public String dailyMenuUrl;

    /** Rating of the establishment */
    public float rating;

    /** The total number of users that rate this establishment. */
    public int userRatingsTotal;

    public PriceLevel priceLevel;

    @Nullable
    public String address;

    @Nullable
    public List<OpenHours> openHours;

    @NonNull
    public String coordinates;
}
