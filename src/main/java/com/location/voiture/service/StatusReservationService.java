package com.location.voiture.service;

import com.location.voiture.domain.StatusReservation;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link StatusReservation}.
 */
public interface StatusReservationService {

    /**
     * Save a statusReservation.
     *
     * @param statusReservation the entity to save.
     * @return the persisted entity.
     */
    StatusReservation save(StatusReservation statusReservation);

    /**
     * Get all the statusReservations.
     *
     * @return the list of entities.
     */
    List<StatusReservation> findAll();


    /**
     * Get the "id" statusReservation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StatusReservation> findOne(Long id);

    /**
     * Delete the "id" statusReservation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
