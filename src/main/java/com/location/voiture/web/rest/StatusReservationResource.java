package com.location.voiture.web.rest;

import com.location.voiture.domain.StatusReservation;
import com.location.voiture.service.StatusReservationService;
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
 * REST controller for managing {@link com.location.voiture.domain.StatusReservation}.
 */
@RestController
@RequestMapping("/api")
public class StatusReservationResource {

    private final Logger log = LoggerFactory.getLogger(StatusReservationResource.class);

    private static final String ENTITY_NAME = "statusReservation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatusReservationService statusReservationService;

    public StatusReservationResource(StatusReservationService statusReservationService) {
        this.statusReservationService = statusReservationService;
    }

    /**
     * {@code POST  /status-reservations} : Create a new statusReservation.
     *
     * @param statusReservation the statusReservation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statusReservation, or with status {@code 400 (Bad Request)} if the statusReservation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/status-reservations")
    public ResponseEntity<StatusReservation> createStatusReservation(@RequestBody StatusReservation statusReservation) throws URISyntaxException {
        log.debug("REST request to save StatusReservation : {}", statusReservation);
        if (statusReservation.getId() != null) {
            throw new BadRequestAlertException("A new statusReservation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatusReservation result = statusReservationService.save(statusReservation);
        return ResponseEntity.created(new URI("/api/status-reservations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /status-reservations} : Updates an existing statusReservation.
     *
     * @param statusReservation the statusReservation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statusReservation,
     * or with status {@code 400 (Bad Request)} if the statusReservation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statusReservation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/status-reservations")
    public ResponseEntity<StatusReservation> updateStatusReservation(@RequestBody StatusReservation statusReservation) throws URISyntaxException {
        log.debug("REST request to update StatusReservation : {}", statusReservation);
        if (statusReservation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatusReservation result = statusReservationService.save(statusReservation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, statusReservation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /status-reservations} : get all the statusReservations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statusReservations in body.
     */
    @GetMapping("/status-reservations")
    public List<StatusReservation> getAllStatusReservations() {
        log.debug("REST request to get all StatusReservations");
        return statusReservationService.findAll();
    }

    /**
     * {@code GET  /status-reservations/:id} : get the "id" statusReservation.
     *
     * @param id the id of the statusReservation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statusReservation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/status-reservations/{id}")
    public ResponseEntity<StatusReservation> getStatusReservation(@PathVariable Long id) {
        log.debug("REST request to get StatusReservation : {}", id);
        Optional<StatusReservation> statusReservation = statusReservationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statusReservation);
    }

    /**
     * {@code DELETE  /status-reservations/:id} : delete the "id" statusReservation.
     *
     * @param id the id of the statusReservation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/status-reservations/{id}")
    public ResponseEntity<Void> deleteStatusReservation(@PathVariable Long id) {
        log.debug("REST request to delete StatusReservation : {}", id);
        statusReservationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
