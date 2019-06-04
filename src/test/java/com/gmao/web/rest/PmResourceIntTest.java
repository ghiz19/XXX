package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Pm;
import com.gmao.repository.PmRepository;
import com.gmao.service.PmService;
import com.gmao.service.dto.PmDTO;
import com.gmao.service.mapper.PmMapper;
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
 * Test class for the PmResource REST controller.
 *
 * @see PmResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class PmResourceIntTest {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_UNITE = "AAAAAAAAAA";
    private static final String UPDATED_UNITE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_AVOIRPLANPREVETINF = false;
    private static final Boolean UPDATED_AVOIRPLANPREVETINF = true;

    @Autowired
    private PmRepository pmRepository;

    @Autowired
    private PmMapper pmMapper;

    @Autowired
    private PmService pmService;

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

    private MockMvc restPmMockMvc;

    private Pm pm;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PmResource pmResource = new PmResource(pmService);
        this.restPmMockMvc = MockMvcBuilders.standaloneSetup(pmResource)
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
    public static Pm createEntity(EntityManager em) {
        Pm pm = new Pm()
            .libelle(DEFAULT_LIBELLE)
            .unite(DEFAULT_UNITE)
            .avoirplanprevetinf(DEFAULT_AVOIRPLANPREVETINF);
        return pm;
    }

    @Before
    public void initTest() {
        pm = createEntity(em);
    }

    @Test
    @Transactional
    public void createPm() throws Exception {
        int databaseSizeBeforeCreate = pmRepository.findAll().size();

        // Create the Pm
        PmDTO pmDTO = pmMapper.toDto(pm);
        restPmMockMvc.perform(post("/api/pms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pmDTO)))
            .andExpect(status().isCreated());

        // Validate the Pm in the database
        List<Pm> pmList = pmRepository.findAll();
        assertThat(pmList).hasSize(databaseSizeBeforeCreate + 1);
        Pm testPm = pmList.get(pmList.size() - 1);
        assertThat(testPm.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testPm.getUnite()).isEqualTo(DEFAULT_UNITE);
        assertThat(testPm.isAvoirplanprevetinf()).isEqualTo(DEFAULT_AVOIRPLANPREVETINF);
    }

    @Test
    @Transactional
    public void createPmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pmRepository.findAll().size();

        // Create the Pm with an existing ID
        pm.setId(1L);
        PmDTO pmDTO = pmMapper.toDto(pm);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPmMockMvc.perform(post("/api/pms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pm in the database
        List<Pm> pmList = pmRepository.findAll();
        assertThat(pmList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = pmRepository.findAll().size();
        // set the field null
        pm.setLibelle(null);

        // Create the Pm, which fails.
        PmDTO pmDTO = pmMapper.toDto(pm);

        restPmMockMvc.perform(post("/api/pms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pmDTO)))
            .andExpect(status().isBadRequest());

        List<Pm> pmList = pmRepository.findAll();
        assertThat(pmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvoirplanprevetinfIsRequired() throws Exception {
        int databaseSizeBeforeTest = pmRepository.findAll().size();
        // set the field null
        pm.setAvoirplanprevetinf(null);

        // Create the Pm, which fails.
        PmDTO pmDTO = pmMapper.toDto(pm);

        restPmMockMvc.perform(post("/api/pms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pmDTO)))
            .andExpect(status().isBadRequest());

        List<Pm> pmList = pmRepository.findAll();
        assertThat(pmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPms() throws Exception {
        // Initialize the database
        pmRepository.saveAndFlush(pm);

        // Get all the pmList
        restPmMockMvc.perform(get("/api/pms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pm.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].unite").value(hasItem(DEFAULT_UNITE.toString())))
            .andExpect(jsonPath("$.[*].avoirplanprevetinf").value(hasItem(DEFAULT_AVOIRPLANPREVETINF.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPm() throws Exception {
        // Initialize the database
        pmRepository.saveAndFlush(pm);

        // Get the pm
        restPmMockMvc.perform(get("/api/pms/{id}", pm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pm.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.unite").value(DEFAULT_UNITE.toString()))
            .andExpect(jsonPath("$.avoirplanprevetinf").value(DEFAULT_AVOIRPLANPREVETINF.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPm() throws Exception {
        // Get the pm
        restPmMockMvc.perform(get("/api/pms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePm() throws Exception {
        // Initialize the database
        pmRepository.saveAndFlush(pm);

        int databaseSizeBeforeUpdate = pmRepository.findAll().size();

        // Update the pm
        Pm updatedPm = pmRepository.findById(pm.getId()).get();
        // Disconnect from session so that the updates on updatedPm are not directly saved in db
        em.detach(updatedPm);
        updatedPm
            .libelle(UPDATED_LIBELLE)
            .unite(UPDATED_UNITE)
            .avoirplanprevetinf(UPDATED_AVOIRPLANPREVETINF);
        PmDTO pmDTO = pmMapper.toDto(updatedPm);

        restPmMockMvc.perform(put("/api/pms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pmDTO)))
            .andExpect(status().isOk());

        // Validate the Pm in the database
        List<Pm> pmList = pmRepository.findAll();
        assertThat(pmList).hasSize(databaseSizeBeforeUpdate);
        Pm testPm = pmList.get(pmList.size() - 1);
        assertThat(testPm.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testPm.getUnite()).isEqualTo(UPDATED_UNITE);
        assertThat(testPm.isAvoirplanprevetinf()).isEqualTo(UPDATED_AVOIRPLANPREVETINF);
    }

    @Test
    @Transactional
    public void updateNonExistingPm() throws Exception {
        int databaseSizeBeforeUpdate = pmRepository.findAll().size();

        // Create the Pm
        PmDTO pmDTO = pmMapper.toDto(pm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPmMockMvc.perform(put("/api/pms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pm in the database
        List<Pm> pmList = pmRepository.findAll();
        assertThat(pmList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePm() throws Exception {
        // Initialize the database
        pmRepository.saveAndFlush(pm);

        int databaseSizeBeforeDelete = pmRepository.findAll().size();

        // Delete the pm
        restPmMockMvc.perform(delete("/api/pms/{id}", pm.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pm> pmList = pmRepository.findAll();
        assertThat(pmList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pm.class);
        Pm pm1 = new Pm();
        pm1.setId(1L);
        Pm pm2 = new Pm();
        pm2.setId(pm1.getId());
        assertThat(pm1).isEqualTo(pm2);
        pm2.setId(2L);
        assertThat(pm1).isNotEqualTo(pm2);
        pm1.setId(null);
        assertThat(pm1).isNotEqualTo(pm2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PmDTO.class);
        PmDTO pmDTO1 = new PmDTO();
        pmDTO1.setId(1L);
        PmDTO pmDTO2 = new PmDTO();
        assertThat(pmDTO1).isNotEqualTo(pmDTO2);
        pmDTO2.setId(pmDTO1.getId());
        assertThat(pmDTO1).isEqualTo(pmDTO2);
        pmDTO2.setId(2L);
        assertThat(pmDTO1).isNotEqualTo(pmDTO2);
        pmDTO1.setId(null);
        assertThat(pmDTO1).isNotEqualTo(pmDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pmMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pmMapper.fromId(null)).isNull();
    }
}
