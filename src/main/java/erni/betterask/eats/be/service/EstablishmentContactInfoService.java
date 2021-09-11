package erni.betterask.eats.be.service;

import erni.betterask.eats.be.model.ContactInfo;

import java.util.Optional;

public interface EstablishmentContactInfoService {
    Optional<ContactInfo> findByEstablishmentId(String establishmentId);
}
