package com.location.voiture.web.rest;

import com.location.voiture.LocationVoitureApp;
import com.location.voiture.domain.Vehicule;
import com.location.voiture.repository.VehiculeRepository;
import com.location.voiture.service.VehiculeService;

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
 * Integration tests for the {@link VehiculeResource} REST controller.
 */
@SpringBootTest(classes = LocationVoitureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VehiculeResourceIT {

    private static final String DEFAULT_MATRICULE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE = "BBBBBBBBBB";

    private static final String DEFAULT_MODELE = "AAAAAAAAAA";
    private static final String UPDATED_MODELE = "BBBBBBBBBB";

    private static final String DEFAULT_MARQUE = "AAAAAAAAAA";
    private static final String UPDATED_MARQUE = "BBBBBBBBBB";

    private static final Long DEFAULT_PRIX = 1L;
    private static final Long UPDATED_PRIX = 2L;

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private VehiculeService vehiculeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVehiculeMockMvc;

    private Vehicule vehicule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehicule createEntity(EntityManager em) {
        Vehicule vehicule = new Vehicule()
            .matricule(DEFAULT_MATRICULE)
            .modele(DEFAULT_MODELE)
            .marque(DEFAULT_MARQUE)
            .prix(DEFAULT_PRIX)
            .image(DEFAULT_IMAGE);
        return vehicule;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehicule createUpdatedEntity(EntityManager em) {
        Vehicule vehicule = new Vehicule()
            .matricule(UPDATED_MATRICULE)
            .modele(UPDATED_MODELE)
            .marque(UPDATED_MARQUE)
            .prix(UPDATED_PRIX)
            .image(UPDATED_IMAGE);
        return vehicule;
    }

    @BeforeEach
    public void initTest() {
        vehicule = createEntity(em);
    }

    @Test
    @Transactional
    public void createVehicule() throws Exception {
        int databaseSizeBeforeCreate = vehiculeRepository.findAll().size();
        // Create the Vehicule
        restVehiculeMockMvc.perform(post("/api/vehicules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicule)))
            .andExpect(status().isCreated());

        // Validate the Vehicule in the database
        List<Vehicule> vehiculeList = vehiculeRepository.findAll();
        assertThat(vehiculeList).hasSize(databaseSizeBeforeCreate + 1);
        Vehicule testVehicule = vehiculeList.get(vehiculeList.size() - 1);
        assertThat(testVehicule.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testVehicule.getModele()).isEqualTo(DEFAULT_MODELE);
        assertThat(testVehicule.getMarque()).isEqualTo(DEFAULT_MARQUE);
        assertThat(testVehicule.getPrix()).isEqualTo(DEFAULT_PRIX);
        assertThat(testVehicule.getImage()).isEqualTo(DEFAULT_IMAGE);
    }

    @Test
    @Transactional
    public void createVehiculeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vehiculeRepository.findAll().size();

        // Create the Vehicule with an existing ID
        vehicule.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehiculeMockMvc.perform(post("/api/vehicules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicule)))
            .andExpect(status().isBadRequest());

        // Validate the Vehicule in the database
        List<Vehicule> vehiculeList = vehiculeRepository.findAll();
        assertThat(vehiculeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMatriculeIsRequired() throws Exception {
        int databaseSizeBeforeTest = vehiculeRepository.findAll().size();
        // set the field null
        vehicule.setMatricule(null);

        // Create the Vehicule, which fails.


        restVehiculeMockMvc.perform(post("/api/vehicules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicule)))
            .andExpect(status().isBadRequest());

        List<Vehicule> vehiculeList = vehiculeRepository.findAll();
        assertThat(vehiculeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModeleIsRequired() throws Exception {
        int databaseSizeBeforeTest = vehiculeRepository.findAll().size();
        // set the field null
        vehicule.setModele(null);

        // Create the Vehicule, which fails.


        restVehiculeMockMvc.perform(post("/api/vehicules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicule)))
            .andExpect(status().isBadRequest());

        List<Vehicule> vehiculeList = vehiculeRepository.findAll();
        assertThat(vehiculeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMarqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = vehiculeRepository.findAll().size();
        // set the field null
        vehicule.setMarque(null);

        // Create the Vehicule, which fails.


        restVehiculeMockMvc.perform(post("/api/vehicules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicule)))
            .andExpect(status().isBadRequest());

        List<Vehicule> vehiculeList = vehiculeRepository.findAll();
        assertThat(vehiculeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixIsRequired() throws Exception {
        int databaseSizeBeforeTest = vehiculeRepository.findAll().size();
        // set the field null
        vehicule.setPrix(null);

        // Create the Vehicule, which fails.


        restVehiculeMockMvc.perform(post("/api/vehicules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicule)))
            .andExpect(status().isBadRequest());

        List<Vehicule> vehiculeList = vehiculeRepository.findAll();
        assertThat(vehiculeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVehicules() throws Exception {
        // Initialize the database
        vehiculeRepository.saveAndFlush(vehicule);

        // Get all the vehiculeList
        restVehiculeMockMvc.perform(get("/api/vehicules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicule.getId().intValue())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].modele").value(hasItem(DEFAULT_MODELE)))
            .andExpect(jsonPath("$.[*].marque").value(hasItem(DEFAULT_MARQUE)))
            .andExpect(jsonPath("$.[*].prix").value(hasItem(DEFAULT_PRIX.intValue())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)));
    }
    
    @Test
    @Transactional
    public void getVehicule() throws Exception {
        // Initialize the database
        vehiculeRepository.saveAndFlush(vehicule);

        // Get the vehicule
        restVehiculeMockMvc.perform(get("/api/vehicules/{id}", vehicule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vehicule.getId().intValue()))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.modele").value(DEFAULT_MODELE))
            .andExpect(jsonPath("$.marque").value(DEFAULT_MARQUE))
            .andExpect(jsonPath("$.prix").value(DEFAULT_PRIX.intValue()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE));
    }
    @Test
    @Transactional
    public void getNonExistingVehicule() throws Exception {
        // Get the vehicule
        restVehiculeMockMvc.perform(get("/api/vehicules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVehicule() throws Exception {
        // Initialize the database
        vehiculeService.save(vehicule);

        int databaseSizeBeforeUpdate = vehiculeRepository.findAll().size();

        // Update the vehicule
        Vehicule updatedVehicule = vehiculeRepository.findById(vehicule.getId()).get();
        // Disconnect from session so that the updates on updatedVehicule are not directly saved in db
        em.detach(updatedVehicule);
        updatedVehicule
            .matricule(UPDATED_MATRICULE)
            .modele(UPDATED_MODELE)
            .marque(UPDATED_MARQUE)
            .prix(UPDATED_PRIX)
            .image(UPDATED_IMAGE);

        restVehiculeMockMvc.perform(put("/api/vehicules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVehicule)))
            .andExpect(status().isOk());

        // Validate the Vehicule in the database
        List<Vehicule> vehiculeList = vehiculeRepository.findAll();
        assertThat(vehiculeList).hasSize(databaseSizeBeforeUpdate);
        Vehicule testVehicule = vehiculeList.get(vehiculeList.size() - 1);
        assertThat(testVehicule.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testVehicule.getModele()).isEqualTo(UPDATED_MODELE);
        assertThat(testVehicule.getMarque()).isEqualTo(UPDATED_MARQUE);
        assertThat(testVehicule.getPrix()).isEqualTo(UPDATED_PRIX);
        assertThat(testVehicule.getImage()).isEqualTo(UPDATED_IMAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingVehicule() throws Exception {
        int databaseSizeBeforeUpdate = vehiculeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehiculeMockMvc.perform(put("/api/vehicules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicule)))
            .andExpect(status().isBadRequest());

        // Validate the Vehicule in the database
        List<Vehicule> vehiculeList = vehiculeRepository.findAll();
        assertThat(vehiculeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVehicule() throws Exception {
        // Initialize the database
        vehiculeService.save(vehicule);

        int databaseSizeBeforeDelete = vehiculeRepository.findAll().size();

        // Delete the vehicule
        restVehiculeMockMvc.perform(delete("/api/vehicules/{id}", vehicule.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vehicule> vehiculeList = vehiculeRepository.findAll();
        assertThat(vehiculeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
