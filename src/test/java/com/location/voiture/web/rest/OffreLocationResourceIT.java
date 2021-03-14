package com.location.voiture.web.rest;

import com.location.voiture.LocationVoitureApp;
import com.location.voiture.domain.OffreLocation;
import com.location.voiture.repository.OffreLocationRepository;
import com.location.voiture.service.OffreLocationService;

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
 * Integration tests for the {@link OffreLocationResource} REST controller.
 */
@SpringBootTest(classes = LocationVoitureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OffreLocationResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Long DEFAULT_NBRE_JOURS = 1L;
    private static final Long UPDATED_NBRE_JOURS = 2L;

    private static final Long DEFAULT_PRIX = 1L;
    private static final Long UPDATED_PRIX = 2L;

    @Autowired
    private OffreLocationRepository offreLocationRepository;

    @Autowired
    private OffreLocationService offreLocationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOffreLocationMockMvc;

    private OffreLocation offreLocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OffreLocation createEntity(EntityManager em) {
        OffreLocation offreLocation = new OffreLocation()
            .libelle(DEFAULT_LIBELLE)
            .nbreJours(DEFAULT_NBRE_JOURS)
            .prix(DEFAULT_PRIX);
        return offreLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OffreLocation createUpdatedEntity(EntityManager em) {
        OffreLocation offreLocation = new OffreLocation()
            .libelle(UPDATED_LIBELLE)
            .nbreJours(UPDATED_NBRE_JOURS)
            .prix(UPDATED_PRIX);
        return offreLocation;
    }

    @BeforeEach
    public void initTest() {
        offreLocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createOffreLocation() throws Exception {
        int databaseSizeBeforeCreate = offreLocationRepository.findAll().size();
        // Create the OffreLocation
        restOffreLocationMockMvc.perform(post("/api/offre-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offreLocation)))
            .andExpect(status().isCreated());

        // Validate the OffreLocation in the database
        List<OffreLocation> offreLocationList = offreLocationRepository.findAll();
        assertThat(offreLocationList).hasSize(databaseSizeBeforeCreate + 1);
        OffreLocation testOffreLocation = offreLocationList.get(offreLocationList.size() - 1);
        assertThat(testOffreLocation.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testOffreLocation.getNbreJours()).isEqualTo(DEFAULT_NBRE_JOURS);
        assertThat(testOffreLocation.getPrix()).isEqualTo(DEFAULT_PRIX);
    }

    @Test
    @Transactional
    public void createOffreLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offreLocationRepository.findAll().size();

        // Create the OffreLocation with an existing ID
        offreLocation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOffreLocationMockMvc.perform(post("/api/offre-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offreLocation)))
            .andExpect(status().isBadRequest());

        // Validate the OffreLocation in the database
        List<OffreLocation> offreLocationList = offreLocationRepository.findAll();
        assertThat(offreLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOffreLocations() throws Exception {
        // Initialize the database
        offreLocationRepository.saveAndFlush(offreLocation);

        // Get all the offreLocationList
        restOffreLocationMockMvc.perform(get("/api/offre-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offreLocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].nbreJours").value(hasItem(DEFAULT_NBRE_JOURS.intValue())))
            .andExpect(jsonPath("$.[*].prix").value(hasItem(DEFAULT_PRIX.intValue())));
    }
    
    @Test
    @Transactional
    public void getOffreLocation() throws Exception {
        // Initialize the database
        offreLocationRepository.saveAndFlush(offreLocation);

        // Get the offreLocation
        restOffreLocationMockMvc.perform(get("/api/offre-locations/{id}", offreLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offreLocation.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.nbreJours").value(DEFAULT_NBRE_JOURS.intValue()))
            .andExpect(jsonPath("$.prix").value(DEFAULT_PRIX.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingOffreLocation() throws Exception {
        // Get the offreLocation
        restOffreLocationMockMvc.perform(get("/api/offre-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOffreLocation() throws Exception {
        // Initialize the database
        offreLocationService.save(offreLocation);

        int databaseSizeBeforeUpdate = offreLocationRepository.findAll().size();

        // Update the offreLocation
        OffreLocation updatedOffreLocation = offreLocationRepository.findById(offreLocation.getId()).get();
        // Disconnect from session so that the updates on updatedOffreLocation are not directly saved in db
        em.detach(updatedOffreLocation);
        updatedOffreLocation
            .libelle(UPDATED_LIBELLE)
            .nbreJours(UPDATED_NBRE_JOURS)
            .prix(UPDATED_PRIX);

        restOffreLocationMockMvc.perform(put("/api/offre-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOffreLocation)))
            .andExpect(status().isOk());

        // Validate the OffreLocation in the database
        List<OffreLocation> offreLocationList = offreLocationRepository.findAll();
        assertThat(offreLocationList).hasSize(databaseSizeBeforeUpdate);
        OffreLocation testOffreLocation = offreLocationList.get(offreLocationList.size() - 1);
        assertThat(testOffreLocation.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testOffreLocation.getNbreJours()).isEqualTo(UPDATED_NBRE_JOURS);
        assertThat(testOffreLocation.getPrix()).isEqualTo(UPDATED_PRIX);
    }

    @Test
    @Transactional
    public void updateNonExistingOffreLocation() throws Exception {
        int databaseSizeBeforeUpdate = offreLocationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOffreLocationMockMvc.perform(put("/api/offre-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offreLocation)))
            .andExpect(status().isBadRequest());

        // Validate the OffreLocation in the database
        List<OffreLocation> offreLocationList = offreLocationRepository.findAll();
        assertThat(offreLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOffreLocation() throws Exception {
        // Initialize the database
        offreLocationService.save(offreLocation);

        int databaseSizeBeforeDelete = offreLocationRepository.findAll().size();

        // Delete the offreLocation
        restOffreLocationMockMvc.perform(delete("/api/offre-locations/{id}", offreLocation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OffreLocation> offreLocationList = offreLocationRepository.findAll();
        assertThat(offreLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
