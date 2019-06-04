package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Historiquetache;
import com.gmao.repository.HistoriquetacheRepository;
import com.gmao.service.HistoriquetacheService;
import com.gmao.service.dto.HistoriquetacheDTO;
import com.gmao.service.mapper.HistoriquetacheMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.gmao.web.rest.TestUtil.sameInstant;
import static com.gmao.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HistoriquetacheResource REST controller.
 *
 * @see HistoriquetacheResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class HistoriquetacheResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATETIMEDEBUT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATETIMEDEBUT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DETETIMEDEBUT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DETETIMEDEBUT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private HistoriquetacheRepository historiquetacheRepository;

    @Autowired
    private HistoriquetacheMapper historiquetacheMapper;

    @Autowired
    private HistoriquetacheService historiquetacheService;

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

    private MockMvc restHistoriquetacheMockMvc;

    private Historiquetache historiquetache;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HistoriquetacheResource historiquetacheResource = new HistoriquetacheResource(historiquetacheService);
        this.restHistoriquetacheMockMvc = MockMvcBuilders.standaloneSetup(historiquetacheResource)
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
    public static Historiquetache createEntity(EntityManager em) {
        Historiquetache historiquetache = new Historiquetache()
            .description(DEFAULT_DESCRIPTION)
            .datetimedebut(DEFAULT_DATETIMEDEBUT)
            .detetimedebut(DEFAULT_DETETIMEDEBUT);
        return historiquetache;
    }

    @Before
    public void initTest() {
        historiquetache = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistoriquetache() throws Exception {
        int databaseSizeBeforeCreate = historiquetacheRepository.findAll().size();

        // Create the Historiquetache
        HistoriquetacheDTO historiquetacheDTO = historiquetacheMapper.toDto(historiquetache);
        restHistoriquetacheMockMvc.perform(post("/api/historiquetaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historiquetacheDTO)))
            .andExpect(status().isCreated());

        // Validate the Historiquetache in the database
        List<Historiquetache> historiquetacheList = historiquetacheRepository.findAll();
        assertThat(historiquetacheList).hasSize(databaseSizeBeforeCreate + 1);
        Historiquetache testHistoriquetache = historiquetacheList.get(historiquetacheList.size() - 1);
        assertThat(testHistoriquetache.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testHistoriquetache.getDatetimedebut()).isEqualTo(DEFAULT_DATETIMEDEBUT);
        assertThat(testHistoriquetache.getDetetimedebut()).isEqualTo(DEFAULT_DETETIMEDEBUT);
    }

    @Test
    @Transactional
    public void createHistoriquetacheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historiquetacheRepository.findAll().size();

        // Create the Historiquetache with an existing ID
        historiquetache.setId(1L);
        HistoriquetacheDTO historiquetacheDTO = historiquetacheMapper.toDto(historiquetache);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoriquetacheMockMvc.perform(post("/api/historiquetaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historiquetacheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Historiquetache in the database
        List<Historiquetache> historiquetacheList = historiquetacheRepository.findAll();
        assertThat(historiquetacheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = historiquetacheRepository.findAll().size();
        // set the field null
        historiquetache.setDescription(null);

        // Create the Historiquetache, which fails.
        HistoriquetacheDTO historiquetacheDTO = historiquetacheMapper.toDto(historiquetache);

        restHistoriquetacheMockMvc.perform(post("/api/historiquetaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historiquetacheDTO)))
            .andExpect(status().isBadRequest());

        List<Historiquetache> historiquetacheList = historiquetacheRepository.findAll();
        assertThat(historiquetacheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDatetimedebutIsRequired() throws Exception {
        int databaseSizeBeforeTest = historiquetacheRepository.findAll().size();
        // set the field null
        historiquetache.setDatetimedebut(null);

        // Create the Historiquetache, which fails.
        HistoriquetacheDTO historiquetacheDTO = historiquetacheMapper.toDto(historiquetache);

        restHistoriquetacheMockMvc.perform(post("/api/historiquetaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historiquetacheDTO)))
            .andExpect(status().isBadRequest());

        List<Historiquetache> historiquetacheList = historiquetacheRepository.findAll();
        assertThat(historiquetacheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDetetimedebutIsRequired() throws Exception {
        int databaseSizeBeforeTest = historiquetacheRepository.findAll().size();
        // set the field null
        historiquetache.setDetetimedebut(null);

        // Create the Historiquetache, which fails.
        HistoriquetacheDTO historiquetacheDTO = historiquetacheMapper.toDto(historiquetache);

        restHistoriquetacheMockMvc.perform(post("/api/historiquetaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historiquetacheDTO)))
            .andExpect(status().isBadRequest());

        List<Historiquetache> historiquetacheList = historiquetacheRepository.findAll();
        assertThat(historiquetacheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHistoriquetaches() throws Exception {
        // Initialize the database
        historiquetacheRepository.saveAndFlush(historiquetache);

        // Get all the historiquetacheList
        restHistoriquetacheMockMvc.perform(get("/api/historiquetaches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historiquetache.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].datetimedebut").value(hasItem(sameInstant(DEFAULT_DATETIMEDEBUT))))
            .andExpect(jsonPath("$.[*].detetimedebut").value(hasItem(sameInstant(DEFAULT_DETETIMEDEBUT))));
    }
    
    @Test
    @Transactional
    public void getHistoriquetache() throws Exception {
        // Initialize the database
        historiquetacheRepository.saveAndFlush(historiquetache);

        // Get the historiquetache
        restHistoriquetacheMockMvc.perform(get("/api/historiquetaches/{id}", historiquetache.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(historiquetache.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.datetimedebut").value(sameInstant(DEFAULT_DATETIMEDEBUT)))
            .andExpect(jsonPath("$.detetimedebut").value(sameInstant(DEFAULT_DETETIMEDEBUT)));
    }

    @Test
    @Transactional
    public void getNonExistingHistoriquetache() throws Exception {
        // Get the historiquetache
        restHistoriquetacheMockMvc.perform(get("/api/historiquetaches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistoriquetache() throws Exception {
        // Initialize the database
        historiquetacheRepository.saveAndFlush(historiquetache);

        int databaseSizeBeforeUpdate = historiquetacheRepository.findAll().size();

        // Update the historiquetache
        Historiquetache updatedHistoriquetache = historiquetacheRepository.findById(historiquetache.getId()).get();
        // Disconnect from session so that the updates on updatedHistoriquetache are not directly saved in db
        em.detach(updatedHistoriquetache);
        updatedHistoriquetache
            .description(UPDATED_DESCRIPTION)
            .datetimedebut(UPDATED_DATETIMEDEBUT)
            .detetimedebut(UPDATED_DETETIMEDEBUT);
        HistoriquetacheDTO historiquetacheDTO = historiquetacheMapper.toDto(updatedHistoriquetache);

        restHistoriquetacheMockMvc.perform(put("/api/historiquetaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historiquetacheDTO)))
            .andExpect(status().isOk());

        // Validate the Historiquetache in the database
        List<Historiquetache> historiquetacheList = historiquetacheRepository.findAll();
        assertThat(historiquetacheList).hasSize(databaseSizeBeforeUpdate);
        Historiquetache testHistoriquetache = historiquetacheList.get(historiquetacheList.size() - 1);
        assertThat(testHistoriquetache.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testHistoriquetache.getDatetimedebut()).isEqualTo(UPDATED_DATETIMEDEBUT);
        assertThat(testHistoriquetache.getDetetimedebut()).isEqualTo(UPDATED_DETETIMEDEBUT);
    }

    @Test
    @Transactional
    public void updateNonExistingHistoriquetache() throws Exception {
        int databaseSizeBeforeUpdate = historiquetacheRepository.findAll().size();

        // Create the Historiquetache
        HistoriquetacheDTO historiquetacheDTO = historiquetacheMapper.toDto(historiquetache);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoriquetacheMockMvc.perform(put("/api/historiquetaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historiquetacheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Historiquetache in the database
        List<Historiquetache> historiquetacheList = historiquetacheRepository.findAll();
        assertThat(historiquetacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistoriquetache() throws Exception {
        // Initialize the database
        historiquetacheRepository.saveAndFlush(historiquetache);

        int databaseSizeBeforeDelete = historiquetacheRepository.findAll().size();

        // Delete the historiquetache
        restHistoriquetacheMockMvc.perform(delete("/api/historiquetaches/{id}", historiquetache.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Historiquetache> historiquetacheList = historiquetacheRepository.findAll();
        assertThat(historiquetacheList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Historiquetache.class);
        Historiquetache historiquetache1 = new Historiquetache();
        historiquetache1.setId(1L);
        Historiquetache historiquetache2 = new Historiquetache();
        historiquetache2.setId(historiquetache1.getId());
        assertThat(historiquetache1).isEqualTo(historiquetache2);
        historiquetache2.setId(2L);
        assertThat(historiquetache1).isNotEqualTo(historiquetache2);
        historiquetache1.setId(null);
        assertThat(historiquetache1).isNotEqualTo(historiquetache2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoriquetacheDTO.class);
        HistoriquetacheDTO historiquetacheDTO1 = new HistoriquetacheDTO();
        historiquetacheDTO1.setId(1L);
        HistoriquetacheDTO historiquetacheDTO2 = new HistoriquetacheDTO();
        assertThat(historiquetacheDTO1).isNotEqualTo(historiquetacheDTO2);
        historiquetacheDTO2.setId(historiquetacheDTO1.getId());
        assertThat(historiquetacheDTO1).isEqualTo(historiquetacheDTO2);
        historiquetacheDTO2.setId(2L);
        assertThat(historiquetacheDTO1).isNotEqualTo(historiquetacheDTO2);
        historiquetacheDTO1.setId(null);
        assertThat(historiquetacheDTO1).isNotEqualTo(historiquetacheDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(historiquetacheMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(historiquetacheMapper.fromId(null)).isNull();
    }
}
