package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Planprevetinf;
import com.gmao.repository.PlanprevetinfRepository;
import com.gmao.service.PlanprevetinfService;
import com.gmao.service.dto.PlanprevetinfDTO;
import com.gmao.service.mapper.PlanprevetinfMapper;
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
 * Test class for the PlanprevetinfResource REST controller.
 *
 * @see PlanprevetinfResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class PlanprevetinfResourceIntTest {

    private static final String DEFAULT_DESCRIPTIONPLAN = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTIONPLAN = "BBBBBBBBBB";

    @Autowired
    private PlanprevetinfRepository planprevetinfRepository;

    @Autowired
    private PlanprevetinfMapper planprevetinfMapper;

    @Autowired
    private PlanprevetinfService planprevetinfService;

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

    private MockMvc restPlanprevetinfMockMvc;

    private Planprevetinf planprevetinf;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanprevetinfResource planprevetinfResource = new PlanprevetinfResource(planprevetinfService);
        this.restPlanprevetinfMockMvc = MockMvcBuilders.standaloneSetup(planprevetinfResource)
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
    public static Planprevetinf createEntity(EntityManager em) {
        Planprevetinf planprevetinf = new Planprevetinf()
            .descriptionplan(DEFAULT_DESCRIPTIONPLAN);
        return planprevetinf;
    }

    @Before
    public void initTest() {
        planprevetinf = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanprevetinf() throws Exception {
        int databaseSizeBeforeCreate = planprevetinfRepository.findAll().size();

        // Create the Planprevetinf
        PlanprevetinfDTO planprevetinfDTO = planprevetinfMapper.toDto(planprevetinf);
        restPlanprevetinfMockMvc.perform(post("/api/planprevetinfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planprevetinfDTO)))
            .andExpect(status().isCreated());

        // Validate the Planprevetinf in the database
        List<Planprevetinf> planprevetinfList = planprevetinfRepository.findAll();
        assertThat(planprevetinfList).hasSize(databaseSizeBeforeCreate + 1);
        Planprevetinf testPlanprevetinf = planprevetinfList.get(planprevetinfList.size() - 1);
        assertThat(testPlanprevetinf.getDescriptionplan()).isEqualTo(DEFAULT_DESCRIPTIONPLAN);
    }

    @Test
    @Transactional
    public void createPlanprevetinfWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planprevetinfRepository.findAll().size();

        // Create the Planprevetinf with an existing ID
        planprevetinf.setId(1L);
        PlanprevetinfDTO planprevetinfDTO = planprevetinfMapper.toDto(planprevetinf);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanprevetinfMockMvc.perform(post("/api/planprevetinfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planprevetinfDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Planprevetinf in the database
        List<Planprevetinf> planprevetinfList = planprevetinfRepository.findAll();
        assertThat(planprevetinfList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionplanIsRequired() throws Exception {
        int databaseSizeBeforeTest = planprevetinfRepository.findAll().size();
        // set the field null
        planprevetinf.setDescriptionplan(null);

        // Create the Planprevetinf, which fails.
        PlanprevetinfDTO planprevetinfDTO = planprevetinfMapper.toDto(planprevetinf);

        restPlanprevetinfMockMvc.perform(post("/api/planprevetinfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planprevetinfDTO)))
            .andExpect(status().isBadRequest());

        List<Planprevetinf> planprevetinfList = planprevetinfRepository.findAll();
        assertThat(planprevetinfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanprevetinfs() throws Exception {
        // Initialize the database
        planprevetinfRepository.saveAndFlush(planprevetinf);

        // Get all the planprevetinfList
        restPlanprevetinfMockMvc.perform(get("/api/planprevetinfs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planprevetinf.getId().intValue())))
            .andExpect(jsonPath("$.[*].descriptionplan").value(hasItem(DEFAULT_DESCRIPTIONPLAN.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanprevetinf() throws Exception {
        // Initialize the database
        planprevetinfRepository.saveAndFlush(planprevetinf);

        // Get the planprevetinf
        restPlanprevetinfMockMvc.perform(get("/api/planprevetinfs/{id}", planprevetinf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planprevetinf.getId().intValue()))
            .andExpect(jsonPath("$.descriptionplan").value(DEFAULT_DESCRIPTIONPLAN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPlanprevetinf() throws Exception {
        // Get the planprevetinf
        restPlanprevetinfMockMvc.perform(get("/api/planprevetinfs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanprevetinf() throws Exception {
        // Initialize the database
        planprevetinfRepository.saveAndFlush(planprevetinf);

        int databaseSizeBeforeUpdate = planprevetinfRepository.findAll().size();

        // Update the planprevetinf
        Planprevetinf updatedPlanprevetinf = planprevetinfRepository.findById(planprevetinf.getId()).get();
        // Disconnect from session so that the updates on updatedPlanprevetinf are not directly saved in db
        em.detach(updatedPlanprevetinf);
        updatedPlanprevetinf
            .descriptionplan(UPDATED_DESCRIPTIONPLAN);
        PlanprevetinfDTO planprevetinfDTO = planprevetinfMapper.toDto(updatedPlanprevetinf);

        restPlanprevetinfMockMvc.perform(put("/api/planprevetinfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planprevetinfDTO)))
            .andExpect(status().isOk());

        // Validate the Planprevetinf in the database
        List<Planprevetinf> planprevetinfList = planprevetinfRepository.findAll();
        assertThat(planprevetinfList).hasSize(databaseSizeBeforeUpdate);
        Planprevetinf testPlanprevetinf = planprevetinfList.get(planprevetinfList.size() - 1);
        assertThat(testPlanprevetinf.getDescriptionplan()).isEqualTo(UPDATED_DESCRIPTIONPLAN);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanprevetinf() throws Exception {
        int databaseSizeBeforeUpdate = planprevetinfRepository.findAll().size();

        // Create the Planprevetinf
        PlanprevetinfDTO planprevetinfDTO = planprevetinfMapper.toDto(planprevetinf);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanprevetinfMockMvc.perform(put("/api/planprevetinfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planprevetinfDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Planprevetinf in the database
        List<Planprevetinf> planprevetinfList = planprevetinfRepository.findAll();
        assertThat(planprevetinfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlanprevetinf() throws Exception {
        // Initialize the database
        planprevetinfRepository.saveAndFlush(planprevetinf);

        int databaseSizeBeforeDelete = planprevetinfRepository.findAll().size();

        // Delete the planprevetinf
        restPlanprevetinfMockMvc.perform(delete("/api/planprevetinfs/{id}", planprevetinf.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Planprevetinf> planprevetinfList = planprevetinfRepository.findAll();
        assertThat(planprevetinfList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Planprevetinf.class);
        Planprevetinf planprevetinf1 = new Planprevetinf();
        planprevetinf1.setId(1L);
        Planprevetinf planprevetinf2 = new Planprevetinf();
        planprevetinf2.setId(planprevetinf1.getId());
        assertThat(planprevetinf1).isEqualTo(planprevetinf2);
        planprevetinf2.setId(2L);
        assertThat(planprevetinf1).isNotEqualTo(planprevetinf2);
        planprevetinf1.setId(null);
        assertThat(planprevetinf1).isNotEqualTo(planprevetinf2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanprevetinfDTO.class);
        PlanprevetinfDTO planprevetinfDTO1 = new PlanprevetinfDTO();
        planprevetinfDTO1.setId(1L);
        PlanprevetinfDTO planprevetinfDTO2 = new PlanprevetinfDTO();
        assertThat(planprevetinfDTO1).isNotEqualTo(planprevetinfDTO2);
        planprevetinfDTO2.setId(planprevetinfDTO1.getId());
        assertThat(planprevetinfDTO1).isEqualTo(planprevetinfDTO2);
        planprevetinfDTO2.setId(2L);
        assertThat(planprevetinfDTO1).isNotEqualTo(planprevetinfDTO2);
        planprevetinfDTO1.setId(null);
        assertThat(planprevetinfDTO1).isNotEqualTo(planprevetinfDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planprevetinfMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planprevetinfMapper.fromId(null)).isNull();
    }
}
