package com.location.voiture.service;

import com.location.voiture.domain.OffreLocation;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link OffreLocation}.
 */
public interface OffreLocationService {

    /**
     * Save a offreLocation.
     *
     * @param offreLocation the entity to save.
     * @return the persisted entity.
     */
    OffreLocation save(OffreLocation offreLocation);

    /**
     * Get all the offreLocations.
     *
     * @return the list of entities.
     */
    List<OffreLocation> findAll();


    /**
     * Get the "id" offreLocation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OffreLocation> findOne(Long id);

    /**
     * Delete the "id" offreLocation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
