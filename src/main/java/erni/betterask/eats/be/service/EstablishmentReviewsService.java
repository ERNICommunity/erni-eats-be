package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.Review;

import java.util.List;

public interface EstablishmentReviewsService {
    List<Review> getReviewsByEstablishmentId(String establishmentId);
}
