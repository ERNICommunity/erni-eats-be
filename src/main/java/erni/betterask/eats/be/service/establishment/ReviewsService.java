package erni.betterask.eats.be.service.establishment;

import erni.betterask.eats.be.model.Review;

import java.util.List;

public interface ReviewsService {
    List<Review> findByEstablishmentId(String establishmentId);
}
