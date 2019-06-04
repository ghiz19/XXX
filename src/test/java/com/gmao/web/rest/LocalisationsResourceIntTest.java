package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Localisations;
import com.gmao.repository.LocalisationsRepository;
import com.gmao.service.LocalisationsService;
import com.gmao.service.dto.LocalisationsDTO;
import com.gmao.service.mapper.LocalisationsMapper;
import com.gmao.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.gmao.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LocalisationsResource REST controller.
 *
 * @see LocalisationsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class LocalisationsResourceIntTest {

    private static final String DEFAULT_NOMLOCALISATION = "AAAAAAAAAA";
    private static final String UPDATED_NOMLOCALISATION = "BBBBBBBBBB";

    @Autowired
    private LocalisationsRepository localisationsRepository;

    @Autowired
    private LocalisationsMapper localisationsMapper;

    @Autowired
    private LocalisationsService localisationsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restLocalisationsMockMvc;

    private Localisations localisations;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocalisationsResource localisationsResource = new LocalisationsResource(localisationsService);
        this.restLocalisationsMockMvc = MockMvcBuilders.standaloneSetup(localisationsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Localisations createEntity(EntityManager em) {
        Localisations localisations = new Localisations()
            .nomlocalisation(DEFAULT_NOMLOCALISATION);
        return localisations;
    }

    @Before
    public void initTest() {
        localisations = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocalisations() throws Exception {
        int databaseSizeBeforeCreate = localisationsRepository.findAll().size();

        // Create the Localisations
        LocalisationsDTO localisationsDTO = localisationsMapper.toDto(localisations);
        restLocalisationsMockMvc.perform(post("/api/localisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(localisationsDTO)))
            .andExpect(status().isCreated());

        // Validate the Localisations in the database
        List<Localisations> localisationsList = localisationsRepository.findAll();
        assertThat(localisationsList).hasSize(databaseSizeBeforeCreate + 1);
        Localisations testLocalisations = localisationsList.get(localisationsList.size() - 1);
        assertThat(testLocalisations.getNomlocalisation()).isEqualTo(DEFAULT_NOMLOCALISATION);
    }

    @Test
    @Transactional
    public void createLocalisationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = localisationsRepository.findAll().size();

        // Create the Localisations with an existing ID
        localisations.setId(1L);
        LocalisationsDTO localisationsDTO = localisationsMapper.toDto(localisations);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocalisationsMockMvc.perform(post("/api/localisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(localisationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Localisations in the database
        List<Localisations> localisationsList = localisationsRepository.findAll();
        assertThat(localisationsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomlocalisationIsRequired() throws Exception {
        int databaseSizeBeforeTest = localisationsRepository.findAll().size();
        // set the field null
        localisations.setNomlocalisation(null);

        // Create the Localisations, which fails.
        LocalisationsDTO localisationsDTO = localisationsMapper.toDto(localisations);

        restLocalisationsMockMvc.perform(post("/api/localisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(localisationsDTO)))
            .andExpect(status().isBadRequest());

        List<Localisations> localisationsList = localisationsRepository.findAll();
        assertThat(localisationsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLocalisations() throws Exception {
        // Initialize the database
        localisationsRepository.saveAndFlush(localisations);

        // Get all the localisationsList
        restLocalisationsMockMvc.perform(get("/api/localisations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(localisations.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomlocalisation").value(hasItem(DEFAULT_NOMLOCALISATION.toString())));
    }
    
    @Test
    @Transactional
    public void getLocalisations() throws Exception {
        // Initialize the database
        localisationsRepository.saveAndFlush(localisations);

        // Get the localisations
        restLocalisationsMockMvc.perform(get("/api/localisations/{id}", localisations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(localisations.getId().intValue()))
            .andExpect(jsonPath("$.nomlocalisation").value(DEFAULT_NOMLOCALISATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLocalisations() throws Exception {
        // Get the localisations
        restLocalisationsMockMvc.perform(get("/api/localisations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocalisations() throws Exception {
        // Initialize the database
        localisationsRepository.saveAndFlush(localisations);

        int databaseSizeBeforeUpdate = localisationsRepository.findAll().size();

        // Update the localisations
        Localisations updatedLocalisations = localisationsRepository.findById(localisations.getId()).get();
        // Disconnect from session so that the updates on updatedLocalisations are not directly saved in db
        em.detach(updatedLocalisations);
        updatedLocalisations
            .nomlocalisation(UPDATED_NOMLOCALISATION);
        LocalisationsDTO localisationsDTO = localisationsMapper.toDto(updatedLocalisations);

        restLocalisationsMockMvc.perform(put("/api/localisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(localisationsDTO)))
            .andExpect(status().isOk());

        // Validate the Localisations in the database
        List<Localisations> localisationsList = localisationsRepository.findAll();
        assertThat(localisationsList).hasSize(databaseSizeBeforeUpdate);
        Localisations testLocalisations = localisationsList.get(localisationsList.size() - 1);
        assertThat(testLocalisations.getNomlocalisation()).isEqualTo(UPDATED_NOMLOCALISATION);
    }

    @Test
    @Transactional
    public void updateNonExistingLocalisations() throws Exception {
        int databaseSizeBeforeUpdate = localisationsRepository.findAll().size();

        // Create the Localisations
        LocalisationsDTO localisationsDTO = localisationsMapper.toDto(localisations);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocalisationsMockMvc.perform(put("/api/localisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(localisationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Localisations in the database
        List<Localisations> localisationsList = localisationsRepository.findAll();
        assertThat(localisationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocalisations() throws Exception {
        // Initialize the database
        localisationsRepository.saveAndFlush(localisations);

        int databaseSizeBeforeDelete = localisationsRepository.findAll().size();

        // Delete the localisations
        restLocalisationsMockMvc.perform(delete("/api/localisations/{id}", localisations.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Localisations> localisationsList = localisationsRepository.findAll();
        assertThat(localisationsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Localisations.class);
        Localisations localisations1 = new Localisations();
        localisations1.setId(1L);
        Localisations localisations2 = new Localisations();
        localisations2.setId(localisations1.getId());
        assertThat(localisations1).isEqualTo(localisations2);
        localisations2.setId(2L);
        assertThat(localisations1).isNotEqualTo(localisations2);
        localisations1.setId(null);
        assertThat(localisations1).isNotEqualTo(localisations2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocalisationsDTO.class);
        LocalisationsDTO localisationsDTO1 = new LocalisationsDTO();
        localisationsDTO1.setId(1L);
        LocalisationsDTO localisationsDTO2 = new LocalisationsDTO();
        assertThat(localisationsDTO1).isNotEqualTo(localisationsDTO2);
        localisationsDTO2.setId(localisationsDTO1.getId());
        assertThat(localisationsDTO1).isEqualTo(localisationsDTO2);
        localisationsDTO2.setId(2L);
        assertThat(localisationsDTO1).isNotEqualTo(localisationsDTO2);
        localisationsDTO1.setId(null);
        assertThat(localisationsDTO1).isNotEqualTo(localisationsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(localisationsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(localisationsMapper.fromId(null)).isNull();
    }
}
