package com.location.voiture.web.rest;

import com.location.voiture.domain.Vehicule;
import com.location.voiture.service.VehiculeService;
import com.location.voiture.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.location.voiture.domain.Vehicule}.
 */
@RestController
@RequestMapping("/api")
public class VehiculeResource {

    private final Logger log = LoggerFactory.getLogger(VehiculeResource.class);

    private static final String ENTITY_NAME = "vehicule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VehiculeService vehiculeService;

    public VehiculeResource(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    /**
     * {@code POST  /vehicules} : Create a new vehicule.
     *
     * @param vehicule the vehicule to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vehicule, or with status {@code 400 (Bad Request)} if the vehicule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vehicules")
    public ResponseEntity<Vehicule> createVehicule(@Valid @RequestBody Vehicule vehicule) throws URISyntaxException {
        log.debug("REST request to save Vehicule : {}", vehicule);
        if (vehicule.getId() != null) {
            throw new BadRequestAlertException("A new vehicule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Vehicule result = vehiculeService.save(vehicule);
        return ResponseEntity.created(new URI("/api/vehicules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vehicules} : Updates an existing vehicule.
     *
     * @param vehicule the vehicule to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehicule,
     * or with status {@code 400 (Bad Request)} if the vehicule is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vehicule couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vehicules")
    public ResponseEntity<Vehicule> updateVehicule(@Valid @RequestBody Vehicule vehicule) throws URISyntaxException {
        log.debug("REST request to update Vehicule : {}", vehicule);
        if (vehicule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Vehicule result = vehiculeService.save(vehicule);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehicule.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vehicules} : get all the vehicules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vehicules in body.
     */
    @GetMapping("/vehicules")
    public List<Vehicule> getAllVehicules() {
        log.debug("REST request to get all Vehicules");
        return vehiculeService.findAll();
    }

    /**
     * {@code GET  /vehicules/:id} : get the "id" vehicule.
     *
     * @param id the id of the vehicule to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vehicule, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vehicules/{id}")
    public ResponseEntity<Vehicule> getVehicule(@PathVariable Long id) {
        log.debug("REST request to get Vehicule : {}", id);
        Optional<Vehicule> vehicule = vehiculeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vehicule);
    }

    /**
     * {@code DELETE  /vehicules/:id} : delete the "id" vehicule.
     *
     * @param id the id of the vehicule to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vehicules/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        log.debug("REST request to delete Vehicule : {}", id);
        vehiculeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
