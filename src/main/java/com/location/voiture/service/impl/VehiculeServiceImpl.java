package com.location.voiture.service.impl;

import com.location.voiture.service.VehiculeService;
import com.location.voiture.domain.Vehicule;
import com.location.voiture.repository.VehiculeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Vehicule}.
 */
@Service
@Transactional
public class VehiculeServiceImpl implements VehiculeService {

    private final Logger log = LoggerFactory.getLogger(VehiculeServiceImpl.class);

    private final VehiculeRepository vehiculeRepository;

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Vehicule save(Vehicule vehicule) {
        log.debug("Request to save Vehicule : {}", vehicule);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> findAll() {
        log.debug("Request to get all Vehicules");
        return vehiculeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicule> findOne(Long id) {
        log.debug("Request to get Vehicule : {}", id);
        return vehiculeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vehicule : {}", id);
        vehiculeRepository.deleteById(id);
    }
}
