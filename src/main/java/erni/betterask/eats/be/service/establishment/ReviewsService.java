package erni.betterask.eats.be.service.establishment;

import erni.betterask.eats.be.model.Review;
import io.vavr.collection.List;

public interface ReviewsService {
    List<Review> findByEstablishmentId(String establishmentId);
}
