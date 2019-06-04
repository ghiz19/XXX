package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Demandeintervention;
import com.gmao.repository.DemandeinterventionRepository;
import com.gmao.service.DemandeinterventionService;
import com.gmao.service.dto.DemandeinterventionDTO;
import com.gmao.service.mapper.DemandeinterventionMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.gmao.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DemandeinterventionResource REST controller.
 *
 * @see DemandeinterventionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class DemandeinterventionResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DEGREEURGENCE = "AAAAAAAAAA";
    private static final String UPDATED_DEGREEURGENCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATEDEMANDE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEDEMANDE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PRIORITE = "AAAAAAAAAA";
    private static final String UPDATED_PRIORITE = "BBBBBBBBBB";

    private static final String DEFAULT_DATEPREVU = "AAAAAAAAAA";
    private static final String UPDATED_DATEPREVU = "BBBBBBBBBB";

    private static final String DEFAULT_DATEREEL = "AAAAAAAAAA";
    private static final String UPDATED_DATEREEL = "BBBBBBBBBB";

    @Autowired
    private DemandeinterventionRepository demandeinterventionRepository;

    @Autowired
    private DemandeinterventionMapper demandeinterventionMapper;

    @Autowired
    private DemandeinterventionService demandeinterventionService;

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

    private MockMvc restDemandeinterventionMockMvc;

    private Demandeintervention demandeintervention;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DemandeinterventionResource demandeinterventionResource = new DemandeinterventionResource(demandeinterventionService);
        this.restDemandeinterventionMockMvc = MockMvcBuilders.standaloneSetup(demandeinterventionResource)
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
    public static Demandeintervention createEntity(EntityManager em) {
        Demandeintervention demandeintervention = new Demandeintervention()
            .description(DEFAULT_DESCRIPTION)
            .degreeurgence(DEFAULT_DEGREEURGENCE)
            .datedemande(DEFAULT_DATEDEMANDE)
            .priorite(DEFAULT_PRIORITE)
            .dateprevu(DEFAULT_DATEPREVU)
            .datereel(DEFAULT_DATEREEL);
        return demandeintervention;
    }

    @Before
    public void initTest() {
        demandeintervention = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemandeintervention() throws Exception {
        int databaseSizeBeforeCreate = demandeinterventionRepository.findAll().size();

        // Create the Demandeintervention
        DemandeinterventionDTO demandeinterventionDTO = demandeinterventionMapper.toDto(demandeintervention);
        restDemandeinterventionMockMvc.perform(post("/api/demandeinterventions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeinterventionDTO)))
            .andExpect(status().isCreated());

        // Validate the Demandeintervention in the database
        List<Demandeintervention> demandeinterventionList = demandeinterventionRepository.findAll();
        assertThat(demandeinterventionList).hasSize(databaseSizeBeforeCreate + 1);
        Demandeintervention testDemandeintervention = demandeinterventionList.get(demandeinterventionList.size() - 1);
        assertThat(testDemandeintervention.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDemandeintervention.getDegreeurgence()).isEqualTo(DEFAULT_DEGREEURGENCE);
        assertThat(testDemandeintervention.getDatedemande()).isEqualTo(DEFAULT_DATEDEMANDE);
        assertThat(testDemandeintervention.getPriorite()).isEqualTo(DEFAULT_PRIORITE);
        assertThat(testDemandeintervention.getDateprevu()).isEqualTo(DEFAULT_DATEPREVU);
        assertThat(testDemandeintervention.getDatereel()).isEqualTo(DEFAULT_DATEREEL);
    }

    @Test
    @Transactional
    public void createDemandeinterventionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeinterventionRepository.findAll().size();

        // Create the Demandeintervention with an existing ID
        demandeintervention.setId(1L);
        DemandeinterventionDTO demandeinterventionDTO = demandeinterventionMapper.toDto(demandeintervention);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeinterventionMockMvc.perform(post("/api/demandeinterventions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeinterventionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demandeintervention in the database
        List<Demandeintervention> demandeinterventionList = demandeinterventionRepository.findAll();
        assertThat(demandeinterventionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeinterventionRepository.findAll().size();
        // set the field null
        demandeintervention.setDescription(null);

        // Create the Demandeintervention, which fails.
        DemandeinterventionDTO demandeinterventionDTO = demandeinterventionMapper.toDto(demandeintervention);

        restDemandeinterventionMockMvc.perform(post("/api/demandeinterventions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeinterventionDTO)))
            .andExpect(status().isBadRequest());

        List<Demandeintervention> demandeinterventionList = demandeinterventionRepository.findAll();
        assertThat(demandeinterventionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDemandeinterventions() throws Exception {
        // Initialize the database
        demandeinterventionRepository.saveAndFlush(demandeintervention);

        // Get all the demandeinterventionList
        restDemandeinterventionMockMvc.perform(get("/api/demandeinterventions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandeintervention.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].degreeurgence").value(hasItem(DEFAULT_DEGREEURGENCE.toString())))
            .andExpect(jsonPath("$.[*].datedemande").value(hasItem(DEFAULT_DATEDEMANDE.toString())))
            .andExpect(jsonPath("$.[*].priorite").value(hasItem(DEFAULT_PRIORITE.toString())))
            .andExpect(jsonPath("$.[*].dateprevu").value(hasItem(DEFAULT_DATEPREVU.toString())))
            .andExpect(jsonPath("$.[*].datereel").value(hasItem(DEFAULT_DATEREEL.toString())));
    }
    
    @Test
    @Transactional
    public void getDemandeintervention() throws Exception {
        // Initialize the database
        demandeinterventionRepository.saveAndFlush(demandeintervention);

        // Get the demandeintervention
        restDemandeinterventionMockMvc.perform(get("/api/demandeinterventions/{id}", demandeintervention.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(demandeintervention.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.degreeurgence").value(DEFAULT_DEGREEURGENCE.toString()))
            .andExpect(jsonPath("$.datedemande").value(DEFAULT_DATEDEMANDE.toString()))
            .andExpect(jsonPath("$.priorite").value(DEFAULT_PRIORITE.toString()))
            .andExpect(jsonPath("$.dateprevu").value(DEFAULT_DATEPREVU.toString()))
            .andExpect(jsonPath("$.datereel").value(DEFAULT_DATEREEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDemandeintervention() throws Exception {
        // Get the demandeintervention
        restDemandeinterventionMockMvc.perform(get("/api/demandeinterventions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemandeintervention() throws Exception {
        // Initialize the database
        demandeinterventionRepository.saveAndFlush(demandeintervention);

        int databaseSizeBeforeUpdate = demandeinterventionRepository.findAll().size();

        // Update the demandeintervention
        Demandeintervention updatedDemandeintervention = demandeinterventionRepository.findById(demandeintervention.getId()).get();
        // Disconnect from session so that the updates on updatedDemandeintervention are not directly saved in db
        em.detach(updatedDemandeintervention);
        updatedDemandeintervention
            .description(UPDATED_DESCRIPTION)
            .degreeurgence(UPDATED_DEGREEURGENCE)
            .datedemande(UPDATED_DATEDEMANDE)
            .priorite(UPDATED_PRIORITE)
            .dateprevu(UPDATED_DATEPREVU)
            .datereel(UPDATED_DATEREEL);
        DemandeinterventionDTO demandeinterventionDTO = demandeinterventionMapper.toDto(updatedDemandeintervention);

        restDemandeinterventionMockMvc.perform(put("/api/demandeinterventions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeinterventionDTO)))
            .andExpect(status().isOk());

        // Validate the Demandeintervention in the database
        List<Demandeintervention> demandeinterventionList = demandeinterventionRepository.findAll();
        assertThat(demandeinterventionList).hasSize(databaseSizeBeforeUpdate);
        Demandeintervention testDemandeintervention = demandeinterventionList.get(demandeinterventionList.size() - 1);
        assertThat(testDemandeintervention.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDemandeintervention.getDegreeurgence()).isEqualTo(UPDATED_DEGREEURGENCE);
        assertThat(testDemandeintervention.getDatedemande()).isEqualTo(UPDATED_DATEDEMANDE);
        assertThat(testDemandeintervention.getPriorite()).isEqualTo(UPDATED_PRIORITE);
        assertThat(testDemandeintervention.getDateprevu()).isEqualTo(UPDATED_DATEPREVU);
        assertThat(testDemandeintervention.getDatereel()).isEqualTo(UPDATED_DATEREEL);
    }

    @Test
    @Transactional
    public void updateNonExistingDemandeintervention() throws Exception {
        int databaseSizeBeforeUpdate = demandeinterventionRepository.findAll().size();

        // Create the Demandeintervention
        DemandeinterventionDTO demandeinterventionDTO = demandeinterventionMapper.toDto(demandeintervention);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeinterventionMockMvc.perform(put("/api/demandeinterventions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeinterventionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demandeintervention in the database
        List<Demandeintervention> demandeinterventionList = demandeinterventionRepository.findAll();
        assertThat(demandeinterventionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemandeintervention() throws Exception {
        // Initialize the database
        demandeinterventionRepository.saveAndFlush(demandeintervention);

        int databaseSizeBeforeDelete = demandeinterventionRepository.findAll().size();

        // Delete the demandeintervention
        restDemandeinterventionMockMvc.perform(delete("/api/demandeinterventions/{id}", demandeintervention.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Demandeintervention> demandeinterventionList = demandeinterventionRepository.findAll();
        assertThat(demandeinterventionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demandeintervention.class);
        Demandeintervention demandeintervention1 = new Demandeintervention();
        demandeintervention1.setId(1L);
        Demandeintervention demandeintervention2 = new Demandeintervention();
        demandeintervention2.setId(demandeintervention1.getId());
        assertThat(demandeintervention1).isEqualTo(demandeintervention2);
        demandeintervention2.setId(2L);
        assertThat(demandeintervention1).isNotEqualTo(demandeintervention2);
        demandeintervention1.setId(null);
        assertThat(demandeintervention1).isNotEqualTo(demandeintervention2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandeinterventionDTO.class);
        DemandeinterventionDTO demandeinterventionDTO1 = new DemandeinterventionDTO();
        demandeinterventionDTO1.setId(1L);
        DemandeinterventionDTO demandeinterventionDTO2 = new DemandeinterventionDTO();
        assertThat(demandeinterventionDTO1).isNotEqualTo(demandeinterventionDTO2);
        demandeinterventionDTO2.setId(demandeinterventionDTO1.getId());
        assertThat(demandeinterventionDTO1).isEqualTo(demandeinterventionDTO2);
        demandeinterventionDTO2.setId(2L);
        assertThat(demandeinterventionDTO1).isNotEqualTo(demandeinterventionDTO2);
        demandeinterventionDTO1.setId(null);
        assertThat(demandeinterventionDTO1).isNotEqualTo(demandeinterventionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(demandeinterventionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(demandeinterventionMapper.fromId(null)).isNull();
    }
}
