package com.location.voiture.web.rest;

import com.location.voiture.LocationVoitureApp;
import com.location.voiture.domain.StatusReservation;
import com.location.voiture.repository.StatusReservationRepository;
import com.location.voiture.service.StatusReservationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StatusReservationResource} REST controller.
 */
@SpringBootTest(classes = LocationVoitureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StatusReservationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private StatusReservationRepository statusReservationRepository;

    @Autowired
    private StatusReservationService statusReservationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatusReservationMockMvc;

    private StatusReservation statusReservation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatusReservation createEntity(EntityManager em) {
        StatusReservation statusReservation = new StatusReservation()
            .name(DEFAULT_NAME);
        return statusReservation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatusReservation createUpdatedEntity(EntityManager em) {
        StatusReservation statusReservation = new StatusReservation()
            .name(UPDATED_NAME);
        return statusReservation;
    }

    @BeforeEach
    public void initTest() {
        statusReservation = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatusReservation() throws Exception {
        int databaseSizeBeforeCreate = statusReservationRepository.findAll().size();
        // Create the StatusReservation
        restStatusReservationMockMvc.perform(post("/api/status-reservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statusReservation)))
            .andExpect(status().isCreated());

        // Validate the StatusReservation in the database
        List<StatusReservation> statusReservationList = statusReservationRepository.findAll();
        assertThat(statusReservationList).hasSize(databaseSizeBeforeCreate + 1);
        StatusReservation testStatusReservation = statusReservationList.get(statusReservationList.size() - 1);
        assertThat(testStatusReservation.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createStatusReservationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statusReservationRepository.findAll().size();

        // Create the StatusReservation with an existing ID
        statusReservation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatusReservationMockMvc.perform(post("/api/status-reservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statusReservation)))
            .andExpect(status().isBadRequest());

        // Validate the StatusReservation in the database
        List<StatusReservation> statusReservationList = statusReservationRepository.findAll();
        assertThat(statusReservationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStatusReservations() throws Exception {
        // Initialize the database
        statusReservationRepository.saveAndFlush(statusReservation);

        // Get all the statusReservationList
        restStatusReservationMockMvc.perform(get("/api/status-reservations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statusReservation.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getStatusReservation() throws Exception {
        // Initialize the database
        statusReservationRepository.saveAndFlush(statusReservation);

        // Get the statusReservation
        restStatusReservationMockMvc.perform(get("/api/status-reservations/{id}", statusReservation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statusReservation.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingStatusReservation() throws Exception {
        // Get the statusReservation
        restStatusReservationMockMvc.perform(get("/api/status-reservations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatusReservation() throws Exception {
        // Initialize the database
        statusReservationService.save(statusReservation);

        int databaseSizeBeforeUpdate = statusReservationRepository.findAll().size();

        // Update the statusReservation
        StatusReservation updatedStatusReservation = statusReservationRepository.findById(statusReservation.getId()).get();
        // Disconnect from session so that the updates on updatedStatusReservation are not directly saved in db
        em.detach(updatedStatusReservation);
        updatedStatusReservation
            .name(UPDATED_NAME);

        restStatusReservationMockMvc.perform(put("/api/status-reservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatusReservation)))
            .andExpect(status().isOk());

        // Validate the StatusReservation in the database
        List<StatusReservation> statusReservationList = statusReservationRepository.findAll();
        assertThat(statusReservationList).hasSize(databaseSizeBeforeUpdate);
        StatusReservation testStatusReservation = statusReservationList.get(statusReservationList.size() - 1);
        assertThat(testStatusReservation.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingStatusReservation() throws Exception {
        int databaseSizeBeforeUpdate = statusReservationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatusReservationMockMvc.perform(put("/api/status-reservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statusReservation)))
            .andExpect(status().isBadRequest());

        // Validate the StatusReservation in the database
        List<StatusReservation> statusReservationList = statusReservationRepository.findAll();
        assertThat(statusReservationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatusReservation() throws Exception {
        // Initialize the database
        statusReservationService.save(statusReservation);

        int databaseSizeBeforeDelete = statusReservationRepository.findAll().size();

        // Delete the statusReservation
        restStatusReservationMockMvc.perform(delete("/api/status-reservations/{id}", statusReservation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StatusReservation> statusReservationList = statusReservationRepository.findAll();
        assertThat(statusReservationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
