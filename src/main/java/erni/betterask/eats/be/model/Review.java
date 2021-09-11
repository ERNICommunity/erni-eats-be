package erni.betterask.eats.be.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Review {
    @NonNull
    public String id;

    @NonNull
    public String establishmentId;

    public String authorName;

    public float rating;

    public String imageUrl;

    @NonNull
    public String reviewText;
}
