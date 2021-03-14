package com.location.voiture.service.impl;

import com.location.voiture.service.OffreLocationService;
import com.location.voiture.domain.OffreLocation;
import com.location.voiture.repository.OffreLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link OffreLocation}.
 */
@Service
@Transactional
public class OffreLocationServiceImpl implements OffreLocationService {

    private final Logger log = LoggerFactory.getLogger(OffreLocationServiceImpl.class);

    private final OffreLocationRepository offreLocationRepository;

    public OffreLocationServiceImpl(OffreLocationRepository offreLocationRepository) {
        this.offreLocationRepository = offreLocationRepository;
    }

    @Override
    public OffreLocation save(OffreLocation offreLocation) {
        log.debug("Request to save OffreLocation : {}", offreLocation);
        return offreLocationRepository.save(offreLocation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OffreLocation> findAll() {
        log.debug("Request to get all OffreLocations");
        return offreLocationRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OffreLocation> findOne(Long id) {
        log.debug("Request to get OffreLocation : {}", id);
        return offreLocationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OffreLocation : {}", id);
        offreLocationRepository.deleteById(id);
    }
}
