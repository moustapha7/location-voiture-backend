package com.location.voiture.repository;

import com.location.voiture.domain.OffreLocation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OffreLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OffreLocationRepository extends JpaRepository<OffreLocation, Long> {
}
