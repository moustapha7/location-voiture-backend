package com.location.voiture.repository;

import com.location.voiture.domain.StatusReservation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StatusReservation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatusReservationRepository extends JpaRepository<StatusReservation, Long> {
}
