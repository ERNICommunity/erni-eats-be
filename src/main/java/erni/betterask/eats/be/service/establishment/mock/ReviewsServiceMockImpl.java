package erni.betterask.eats.be.service.establishment.mock;

import erni.betterask.eats.be.model.Review;
import erni.betterask.eats.be.service.establishment.ReviewsService;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ReviewsServiceMockImpl implements ReviewsService {

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
                    .authorName("Jane Doe")
                    .reviewText("Terrible!")
                    .rating(1.0f)
                    .build()
    );

    @Override
    public List<Review> findByEstablishmentId(String establishmentId) {
        return mockedReviews.filter(review -> review.establishmentId.equals(establishmentId));
    }
}
