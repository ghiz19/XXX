package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Etat;
import com.gmao.repository.EtatRepository;
import com.gmao.service.EtatService;
import com.gmao.service.dto.EtatDTO;
import com.gmao.service.mapper.EtatMapper;
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
 * Test class for the EtatResource REST controller.
 *
 * @see EtatResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class EtatResourceIntTest {

    private static final String DEFAULT_NOMETAT = "AAAAAAAAAA";
    private static final String UPDATED_NOMETAT = "BBBBBBBBBB";

    @Autowired
    private EtatRepository etatRepository;

    @Autowired
    private EtatMapper etatMapper;

    @Autowired
    private EtatService etatService;

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

    private MockMvc restEtatMockMvc;

    private Etat etat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtatResource etatResource = new EtatResource(etatService);
        this.restEtatMockMvc = MockMvcBuilders.standaloneSetup(etatResource)
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
    public static Etat createEntity(EntityManager em) {
        Etat etat = new Etat()
            .nometat(DEFAULT_NOMETAT);
        return etat;
    }

    @Before
    public void initTest() {
        etat = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtat() throws Exception {
        int databaseSizeBeforeCreate = etatRepository.findAll().size();

        // Create the Etat
        EtatDTO etatDTO = etatMapper.toDto(etat);
        restEtatMockMvc.perform(post("/api/etats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etatDTO)))
            .andExpect(status().isCreated());

        // Validate the Etat in the database
        List<Etat> etatList = etatRepository.findAll();
        assertThat(etatList).hasSize(databaseSizeBeforeCreate + 1);
        Etat testEtat = etatList.get(etatList.size() - 1);
        assertThat(testEtat.getNometat()).isEqualTo(DEFAULT_NOMETAT);
    }

    @Test
    @Transactional
    public void createEtatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatRepository.findAll().size();

        // Create the Etat with an existing ID
        etat.setId(1L);
        EtatDTO etatDTO = etatMapper.toDto(etat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatMockMvc.perform(post("/api/etats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Etat in the database
        List<Etat> etatList = etatRepository.findAll();
        assertThat(etatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNometatIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatRepository.findAll().size();
        // set the field null
        etat.setNometat(null);

        // Create the Etat, which fails.
        EtatDTO etatDTO = etatMapper.toDto(etat);

        restEtatMockMvc.perform(post("/api/etats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etatDTO)))
            .andExpect(status().isBadRequest());

        List<Etat> etatList = etatRepository.findAll();
        assertThat(etatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtats() throws Exception {
        // Initialize the database
        etatRepository.saveAndFlush(etat);

        // Get all the etatList
        restEtatMockMvc.perform(get("/api/etats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etat.getId().intValue())))
            .andExpect(jsonPath("$.[*].nometat").value(hasItem(DEFAULT_NOMETAT.toString())));
    }
    
    @Test
    @Transactional
    public void getEtat() throws Exception {
        // Initialize the database
        etatRepository.saveAndFlush(etat);

        // Get the etat
        restEtatMockMvc.perform(get("/api/etats/{id}", etat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etat.getId().intValue()))
            .andExpect(jsonPath("$.nometat").value(DEFAULT_NOMETAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEtat() throws Exception {
        // Get the etat
        restEtatMockMvc.perform(get("/api/etats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtat() throws Exception {
        // Initialize the database
        etatRepository.saveAndFlush(etat);

        int databaseSizeBeforeUpdate = etatRepository.findAll().size();

        // Update the etat
        Etat updatedEtat = etatRepository.findById(etat.getId()).get();
        // Disconnect from session so that the updates on updatedEtat are not directly saved in db
        em.detach(updatedEtat);
        updatedEtat
            .nometat(UPDATED_NOMETAT);
        EtatDTO etatDTO = etatMapper.toDto(updatedEtat);

        restEtatMockMvc.perform(put("/api/etats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etatDTO)))
            .andExpect(status().isOk());

        // Validate the Etat in the database
        List<Etat> etatList = etatRepository.findAll();
        assertThat(etatList).hasSize(databaseSizeBeforeUpdate);
        Etat testEtat = etatList.get(etatList.size() - 1);
        assertThat(testEtat.getNometat()).isEqualTo(UPDATED_NOMETAT);
    }

    @Test
    @Transactional
    public void updateNonExistingEtat() throws Exception {
        int databaseSizeBeforeUpdate = etatRepository.findAll().size();

        // Create the Etat
        EtatDTO etatDTO = etatMapper.toDto(etat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatMockMvc.perform(put("/api/etats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Etat in the database
        List<Etat> etatList = etatRepository.findAll();
        assertThat(etatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtat() throws Exception {
        // Initialize the database
        etatRepository.saveAndFlush(etat);

        int databaseSizeBeforeDelete = etatRepository.findAll().size();

        // Delete the etat
        restEtatMockMvc.perform(delete("/api/etats/{id}", etat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Etat> etatList = etatRepository.findAll();
        assertThat(etatList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Etat.class);
        Etat etat1 = new Etat();
        etat1.setId(1L);
        Etat etat2 = new Etat();
        etat2.setId(etat1.getId());
        assertThat(etat1).isEqualTo(etat2);
        etat2.setId(2L);
        assertThat(etat1).isNotEqualTo(etat2);
        etat1.setId(null);
        assertThat(etat1).isNotEqualTo(etat2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatDTO.class);
        EtatDTO etatDTO1 = new EtatDTO();
        etatDTO1.setId(1L);
        EtatDTO etatDTO2 = new EtatDTO();
        assertThat(etatDTO1).isNotEqualTo(etatDTO2);
        etatDTO2.setId(etatDTO1.getId());
        assertThat(etatDTO1).isEqualTo(etatDTO2);
        etatDTO2.setId(2L);
        assertThat(etatDTO1).isNotEqualTo(etatDTO2);
        etatDTO1.setId(null);
        assertThat(etatDTO1).isNotEqualTo(etatDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etatMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etatMapper.fromId(null)).isNull();
    }
}
