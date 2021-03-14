package com.location.voiture.web.rest;

import com.location.voiture.domain.OffreLocation;
import com.location.voiture.service.OffreLocationService;
import com.location.voiture.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.location.voiture.domain.OffreLocation}.
 */
@RestController
@RequestMapping("/api")
public class OffreLocationResource {

    private final Logger log = LoggerFactory.getLogger(OffreLocationResource.class);

    private static final String ENTITY_NAME = "offreLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OffreLocationService offreLocationService;

    public OffreLocationResource(OffreLocationService offreLocationService) {
        this.offreLocationService = offreLocationService;
    }

    /**
     * {@code POST  /offre-locations} : Create a new offreLocation.
     *
     * @param offreLocation the offreLocation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offreLocation, or with status {@code 400 (Bad Request)} if the offreLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offre-locations")
    public ResponseEntity<OffreLocation> createOffreLocation(@RequestBody OffreLocation offreLocation) throws URISyntaxException {
        log.debug("REST request to save OffreLocation : {}", offreLocation);
        if (offreLocation.getId() != null) {
            throw new BadRequestAlertException("A new offreLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OffreLocation result = offreLocationService.save(offreLocation);
        return ResponseEntity.created(new URI("/api/offre-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offre-locations} : Updates an existing offreLocation.
     *
     * @param offreLocation the offreLocation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offreLocation,
     * or with status {@code 400 (Bad Request)} if the offreLocation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offreLocation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offre-locations")
    public ResponseEntity<OffreLocation> updateOffreLocation(@RequestBody OffreLocation offreLocation) throws URISyntaxException {
        log.debug("REST request to update OffreLocation : {}", offreLocation);
        if (offreLocation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OffreLocation result = offreLocationService.save(offreLocation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, offreLocation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offre-locations} : get all the offreLocations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offreLocations in body.
     */
    @GetMapping("/offre-locations")
    public List<OffreLocation> getAllOffreLocations() {
        log.debug("REST request to get all OffreLocations");
        return offreLocationService.findAll();
    }

    /**
     * {@code GET  /offre-locations/:id} : get the "id" offreLocation.
     *
     * @param id the id of the offreLocation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offreLocation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offre-locations/{id}")
    public ResponseEntity<OffreLocation> getOffreLocation(@PathVariable Long id) {
        log.debug("REST request to get OffreLocation : {}", id);
        Optional<OffreLocation> offreLocation = offreLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offreLocation);
    }

    /**
     * {@code DELETE  /offre-locations/:id} : delete the "id" offreLocation.
     *
     * @param id the id of the offreLocation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offre-locations/{id}")
    public ResponseEntity<Void> deleteOffreLocation(@PathVariable Long id) {
        log.debug("REST request to delete OffreLocation : {}", id);
        offreLocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
