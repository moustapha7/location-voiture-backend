package com.location.voiture.service.impl;

import com.location.voiture.service.StatusReservationService;
import com.location.voiture.domain.StatusReservation;
import com.location.voiture.repository.StatusReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link StatusReservation}.
 */
@Service
@Transactional
public class StatusReservationServiceImpl implements StatusReservationService {

    private final Logger log = LoggerFactory.getLogger(StatusReservationServiceImpl.class);

    private final StatusReservationRepository statusReservationRepository;

    public StatusReservationServiceImpl(StatusReservationRepository statusReservationRepository) {
        this.statusReservationRepository = statusReservationRepository;
    }

    @Override
    public StatusReservation save(StatusReservation statusReservation) {
        log.debug("Request to save StatusReservation : {}", statusReservation);
        return statusReservationRepository.save(statusReservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatusReservation> findAll() {
        log.debug("Request to get all StatusReservations");
        return statusReservationRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<StatusReservation> findOne(Long id) {
        log.debug("Request to get StatusReservation : {}", id);
        return statusReservationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StatusReservation : {}", id);
        statusReservationRepository.deleteById(id);
    }
}
