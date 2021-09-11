package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstablishmentReviewsServiceImpl implements EstablishmentReviewsService {

    private static final List<Review> mockedReviews = List.of(
            Review.builder()
                .id("00")
                .establishmentId("clock-block")
                .authorName("John Doe")
                .reviewText("Awesome!")
                .rating(4.5f)
                .build(),
            Review.builder()
                    .id("01")
                    .establishmentId("clock-block")
                    .authorName("John Doe")
                    .reviewText("Terrible!")
                    .rating(1.0f)
                    .build()
    );

    @Override
    public List<Review> getReviewsByEstablishmentId(String establishmentId) {
        return mockedReviews.stream()
                .filter(review -> review.establishmentId.equals(establishmentId))
                .collect(Collectors.toList());
    }
}
