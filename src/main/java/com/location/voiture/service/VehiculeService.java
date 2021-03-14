package com.location.voiture.service;

import com.location.voiture.domain.Vehicule;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Vehicule}.
 */
public interface VehiculeService {

    /**
     * Save a vehicule.
     *
     * @param vehicule the entity to save.
     * @return the persisted entity.
     */
    Vehicule save(Vehicule vehicule);

    /**
     * Get all the vehicules.
     *
     * @return the list of entities.
     */
    List<Vehicule> findAll();


    /**
     * Get the "id" vehicule.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Vehicule> findOne(Long id);

    /**
     * Delete the "id" vehicule.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
