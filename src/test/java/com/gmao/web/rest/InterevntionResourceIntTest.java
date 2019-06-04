package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Interevntion;
import com.gmao.repository.InterevntionRepository;
import com.gmao.service.InterevntionService;
import com.gmao.service.dto.InterevntionDTO;
import com.gmao.service.mapper.InterevntionMapper;
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
 * Test class for the InterevntionResource REST controller.
 *
 * @see InterevntionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class InterevntionResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATEDEBUTINTERVENTION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEDEBUTINTERVENTION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATEFININTERVENTION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEFININTERVENTION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DUREE = "AAAAAAAAAA";
    private static final String UPDATED_DUREE = "BBBBBBBBBB";

    private static final String DEFAULT_COUTMAINDEUVRE = "AAAAAAAAAA";
    private static final String UPDATED_COUTMAINDEUVRE = "BBBBBBBBBB";

    private static final String DEFAULT_COUTINTEREVNTION = "AAAAAAAAAA";
    private static final String UPDATED_COUTINTEREVNTION = "BBBBBBBBBB";

    private static final String DEFAULT_SOLUTIONS = "AAAAAAAAAA";
    private static final String UPDATED_SOLUTIONS = "BBBBBBBBBB";

    @Autowired
    private InterevntionRepository interevntionRepository;

    @Autowired
    private InterevntionMapper interevntionMapper;

    @Autowired
    private InterevntionService interevntionService;

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

    private MockMvc restInterevntionMockMvc;

    private Interevntion interevntion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InterevntionResource interevntionResource = new InterevntionResource(interevntionService);
        this.restInterevntionMockMvc = MockMvcBuilders.standaloneSetup(interevntionResource)
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
    public static Interevntion createEntity(EntityManager em) {
        Interevntion interevntion = new Interevntion()
            .datedebutintervention(DEFAULT_DATEDEBUTINTERVENTION)
            .datefinintervention(DEFAULT_DATEFININTERVENTION)
            .duree(DEFAULT_DUREE)
            .coutmaindeuvre(DEFAULT_COUTMAINDEUVRE)
            .coutinterevntion(DEFAULT_COUTINTEREVNTION)
            .solutions(DEFAULT_SOLUTIONS);
        return interevntion;
    }

    @Before
    public void initTest() {
        interevntion = createEntity(em);
    }

    @Test
    @Transactional
    public void createInterevntion() throws Exception {
        int databaseSizeBeforeCreate = interevntionRepository.findAll().size();

        // Create the Interevntion
        InterevntionDTO interevntionDTO = interevntionMapper.toDto(interevntion);
        restInterevntionMockMvc.perform(post("/api/interevntions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interevntionDTO)))
            .andExpect(status().isCreated());

        // Validate the Interevntion in the database
        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeCreate + 1);
        Interevntion testInterevntion = interevntionList.get(interevntionList.size() - 1);
        assertThat(testInterevntion.getDatedebutintervention()).isEqualTo(DEFAULT_DATEDEBUTINTERVENTION);
        assertThat(testInterevntion.getDatefinintervention()).isEqualTo(DEFAULT_DATEFININTERVENTION);
        assertThat(testInterevntion.getDuree()).isEqualTo(DEFAULT_DUREE);
        assertThat(testInterevntion.getCoutmaindeuvre()).isEqualTo(DEFAULT_COUTMAINDEUVRE);
        assertThat(testInterevntion.getCoutinterevntion()).isEqualTo(DEFAULT_COUTINTEREVNTION);
        assertThat(testInterevntion.getSolutions()).isEqualTo(DEFAULT_SOLUTIONS);
    }

    @Test
    @Transactional
    public void createInterevntionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = interevntionRepository.findAll().size();

        // Create the Interevntion with an existing ID
        interevntion.setId(1L);
        InterevntionDTO interevntionDTO = interevntionMapper.toDto(interevntion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInterevntionMockMvc.perform(post("/api/interevntions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interevntionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Interevntion in the database
        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDatedebutinterventionIsRequired() throws Exception {
        int databaseSizeBeforeTest = interevntionRepository.findAll().size();
        // set the field null
        interevntion.setDatedebutintervention(null);

        // Create the Interevntion, which fails.
        InterevntionDTO interevntionDTO = interevntionMapper.toDto(interevntion);

        restInterevntionMockMvc.perform(post("/api/interevntions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interevntionDTO)))
            .andExpect(status().isBadRequest());

        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDatefininterventionIsRequired() throws Exception {
        int databaseSizeBeforeTest = interevntionRepository.findAll().size();
        // set the field null
        interevntion.setDatefinintervention(null);

        // Create the Interevntion, which fails.
        InterevntionDTO interevntionDTO = interevntionMapper.toDto(interevntion);

        restInterevntionMockMvc.perform(post("/api/interevntions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interevntionDTO)))
            .andExpect(status().isBadRequest());

        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoutmaindeuvreIsRequired() throws Exception {
        int databaseSizeBeforeTest = interevntionRepository.findAll().size();
        // set the field null
        interevntion.setCoutmaindeuvre(null);

        // Create the Interevntion, which fails.
        InterevntionDTO interevntionDTO = interevntionMapper.toDto(interevntion);

        restInterevntionMockMvc.perform(post("/api/interevntions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interevntionDTO)))
            .andExpect(status().isBadRequest());

        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoutinterevntionIsRequired() throws Exception {
        int databaseSizeBeforeTest = interevntionRepository.findAll().size();
        // set the field null
        interevntion.setCoutinterevntion(null);

        // Create the Interevntion, which fails.
        InterevntionDTO interevntionDTO = interevntionMapper.toDto(interevntion);

        restInterevntionMockMvc.perform(post("/api/interevntions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interevntionDTO)))
            .andExpect(status().isBadRequest());

        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSolutionsIsRequired() throws Exception {
        int databaseSizeBeforeTest = interevntionRepository.findAll().size();
        // set the field null
        interevntion.setSolutions(null);

        // Create the Interevntion, which fails.
        InterevntionDTO interevntionDTO = interevntionMapper.toDto(interevntion);

        restInterevntionMockMvc.perform(post("/api/interevntions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interevntionDTO)))
            .andExpect(status().isBadRequest());

        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInterevntions() throws Exception {
        // Initialize the database
        interevntionRepository.saveAndFlush(interevntion);

        // Get all the interevntionList
        restInterevntionMockMvc.perform(get("/api/interevntions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interevntion.getId().intValue())))
            .andExpect(jsonPath("$.[*].datedebutintervention").value(hasItem(sameInstant(DEFAULT_DATEDEBUTINTERVENTION))))
            .andExpect(jsonPath("$.[*].datefinintervention").value(hasItem(sameInstant(DEFAULT_DATEFININTERVENTION))))
            .andExpect(jsonPath("$.[*].duree").value(hasItem(DEFAULT_DUREE.toString())))
            .andExpect(jsonPath("$.[*].coutmaindeuvre").value(hasItem(DEFAULT_COUTMAINDEUVRE.toString())))
            .andExpect(jsonPath("$.[*].coutinterevntion").value(hasItem(DEFAULT_COUTINTEREVNTION.toString())))
            .andExpect(jsonPath("$.[*].solutions").value(hasItem(DEFAULT_SOLUTIONS.toString())));
    }
    
    @Test
    @Transactional
    public void getInterevntion() throws Exception {
        // Initialize the database
        interevntionRepository.saveAndFlush(interevntion);

        // Get the interevntion
        restInterevntionMockMvc.perform(get("/api/interevntions/{id}", interevntion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(interevntion.getId().intValue()))
            .andExpect(jsonPath("$.datedebutintervention").value(sameInstant(DEFAULT_DATEDEBUTINTERVENTION)))
            .andExpect(jsonPath("$.datefinintervention").value(sameInstant(DEFAULT_DATEFININTERVENTION)))
            .andExpect(jsonPath("$.duree").value(DEFAULT_DUREE.toString()))
            .andExpect(jsonPath("$.coutmaindeuvre").value(DEFAULT_COUTMAINDEUVRE.toString()))
            .andExpect(jsonPath("$.coutinterevntion").value(DEFAULT_COUTINTEREVNTION.toString()))
            .andExpect(jsonPath("$.solutions").value(DEFAULT_SOLUTIONS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInterevntion() throws Exception {
        // Get the interevntion
        restInterevntionMockMvc.perform(get("/api/interevntions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInterevntion() throws Exception {
        // Initialize the database
        interevntionRepository.saveAndFlush(interevntion);

        int databaseSizeBeforeUpdate = interevntionRepository.findAll().size();

        // Update the interevntion
        Interevntion updatedInterevntion = interevntionRepository.findById(interevntion.getId()).get();
        // Disconnect from session so that the updates on updatedInterevntion are not directly saved in db
        em.detach(updatedInterevntion);
        updatedInterevntion
            .datedebutintervention(UPDATED_DATEDEBUTINTERVENTION)
            .datefinintervention(UPDATED_DATEFININTERVENTION)
            .duree(UPDATED_DUREE)
            .coutmaindeuvre(UPDATED_COUTMAINDEUVRE)
            .coutinterevntion(UPDATED_COUTINTEREVNTION)
            .solutions(UPDATED_SOLUTIONS);
        InterevntionDTO interevntionDTO = interevntionMapper.toDto(updatedInterevntion);

        restInterevntionMockMvc.perform(put("/api/interevntions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interevntionDTO)))
            .andExpect(status().isOk());

        // Validate the Interevntion in the database
        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeUpdate);
        Interevntion testInterevntion = interevntionList.get(interevntionList.size() - 1);
        assertThat(testInterevntion.getDatedebutintervention()).isEqualTo(UPDATED_DATEDEBUTINTERVENTION);
        assertThat(testInterevntion.getDatefinintervention()).isEqualTo(UPDATED_DATEFININTERVENTION);
        assertThat(testInterevntion.getDuree()).isEqualTo(UPDATED_DUREE);
        assertThat(testInterevntion.getCoutmaindeuvre()).isEqualTo(UPDATED_COUTMAINDEUVRE);
        assertThat(testInterevntion.getCoutinterevntion()).isEqualTo(UPDATED_COUTINTEREVNTION);
        assertThat(testInterevntion.getSolutions()).isEqualTo(UPDATED_SOLUTIONS);
    }

    @Test
    @Transactional
    public void updateNonExistingInterevntion() throws Exception {
        int databaseSizeBeforeUpdate = interevntionRepository.findAll().size();

        // Create the Interevntion
        InterevntionDTO interevntionDTO = interevntionMapper.toDto(interevntion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterevntionMockMvc.perform(put("/api/interevntions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interevntionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Interevntion in the database
        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInterevntion() throws Exception {
        // Initialize the database
        interevntionRepository.saveAndFlush(interevntion);

        int databaseSizeBeforeDelete = interevntionRepository.findAll().size();

        // Delete the interevntion
        restInterevntionMockMvc.perform(delete("/api/interevntions/{id}", interevntion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Interevntion> interevntionList = interevntionRepository.findAll();
        assertThat(interevntionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Interevntion.class);
        Interevntion interevntion1 = new Interevntion();
        interevntion1.setId(1L);
        Interevntion interevntion2 = new Interevntion();
        interevntion2.setId(interevntion1.getId());
        assertThat(interevntion1).isEqualTo(interevntion2);
        interevntion2.setId(2L);
        assertThat(interevntion1).isNotEqualTo(interevntion2);
        interevntion1.setId(null);
        assertThat(interevntion1).isNotEqualTo(interevntion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InterevntionDTO.class);
        InterevntionDTO interevntionDTO1 = new InterevntionDTO();
        interevntionDTO1.setId(1L);
        InterevntionDTO interevntionDTO2 = new InterevntionDTO();
        assertThat(interevntionDTO1).isNotEqualTo(interevntionDTO2);
        interevntionDTO2.setId(interevntionDTO1.getId());
        assertThat(interevntionDTO1).isEqualTo(interevntionDTO2);
        interevntionDTO2.setId(2L);
        assertThat(interevntionDTO1).isNotEqualTo(interevntionDTO2);
        interevntionDTO1.setId(null);
        assertThat(interevntionDTO1).isNotEqualTo(interevntionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(interevntionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(interevntionMapper.fromId(null)).isNull();
    }
}
