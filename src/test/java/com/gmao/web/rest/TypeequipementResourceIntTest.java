package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Typeequipement;
import com.gmao.repository.TypeequipementRepository;
import com.gmao.service.TypeequipementService;
import com.gmao.service.dto.TypeequipementDTO;
import com.gmao.service.mapper.TypeequipementMapper;
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
 * Test class for the TypeequipementResource REST controller.
 *
 * @see TypeequipementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class TypeequipementResourceIntTest {

    private static final String DEFAULT_TYPEEQUIPEM = "AAAAAAAAAA";
    private static final String UPDATED_TYPEEQUIPEM = "BBBBBBBBBB";

    @Autowired
    private TypeequipementRepository typeequipementRepository;

    @Autowired
    private TypeequipementMapper typeequipementMapper;

    @Autowired
    private TypeequipementService typeequipementService;

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

    private MockMvc restTypeequipementMockMvc;

    private Typeequipement typeequipement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeequipementResource typeequipementResource = new TypeequipementResource(typeequipementService);
        this.restTypeequipementMockMvc = MockMvcBuilders.standaloneSetup(typeequipementResource)
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
    public static Typeequipement createEntity(EntityManager em) {
        Typeequipement typeequipement = new Typeequipement()
            .typeequipem(DEFAULT_TYPEEQUIPEM);
        return typeequipement;
    }

    @Before
    public void initTest() {
        typeequipement = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeequipement() throws Exception {
        int databaseSizeBeforeCreate = typeequipementRepository.findAll().size();

        // Create the Typeequipement
        TypeequipementDTO typeequipementDTO = typeequipementMapper.toDto(typeequipement);
        restTypeequipementMockMvc.perform(post("/api/typeequipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeequipementDTO)))
            .andExpect(status().isCreated());

        // Validate the Typeequipement in the database
        List<Typeequipement> typeequipementList = typeequipementRepository.findAll();
        assertThat(typeequipementList).hasSize(databaseSizeBeforeCreate + 1);
        Typeequipement testTypeequipement = typeequipementList.get(typeequipementList.size() - 1);
        assertThat(testTypeequipement.getTypeequipem()).isEqualTo(DEFAULT_TYPEEQUIPEM);
    }

    @Test
    @Transactional
    public void createTypeequipementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeequipementRepository.findAll().size();

        // Create the Typeequipement with an existing ID
        typeequipement.setId(1L);
        TypeequipementDTO typeequipementDTO = typeequipementMapper.toDto(typeequipement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeequipementMockMvc.perform(post("/api/typeequipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeequipementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Typeequipement in the database
        List<Typeequipement> typeequipementList = typeequipementRepository.findAll();
        assertThat(typeequipementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeequipemIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeequipementRepository.findAll().size();
        // set the field null
        typeequipement.setTypeequipem(null);

        // Create the Typeequipement, which fails.
        TypeequipementDTO typeequipementDTO = typeequipementMapper.toDto(typeequipement);

        restTypeequipementMockMvc.perform(post("/api/typeequipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeequipementDTO)))
            .andExpect(status().isBadRequest());

        List<Typeequipement> typeequipementList = typeequipementRepository.findAll();
        assertThat(typeequipementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeequipements() throws Exception {
        // Initialize the database
        typeequipementRepository.saveAndFlush(typeequipement);

        // Get all the typeequipementList
        restTypeequipementMockMvc.perform(get("/api/typeequipements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeequipement.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeequipem").value(hasItem(DEFAULT_TYPEEQUIPEM.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeequipement() throws Exception {
        // Initialize the database
        typeequipementRepository.saveAndFlush(typeequipement);

        // Get the typeequipement
        restTypeequipementMockMvc.perform(get("/api/typeequipements/{id}", typeequipement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeequipement.getId().intValue()))
            .andExpect(jsonPath("$.typeequipem").value(DEFAULT_TYPEEQUIPEM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeequipement() throws Exception {
        // Get the typeequipement
        restTypeequipementMockMvc.perform(get("/api/typeequipements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeequipement() throws Exception {
        // Initialize the database
        typeequipementRepository.saveAndFlush(typeequipement);

        int databaseSizeBeforeUpdate = typeequipementRepository.findAll().size();

        // Update the typeequipement
        Typeequipement updatedTypeequipement = typeequipementRepository.findById(typeequipement.getId()).get();
        // Disconnect from session so that the updates on updatedTypeequipement are not directly saved in db
        em.detach(updatedTypeequipement);
        updatedTypeequipement
            .typeequipem(UPDATED_TYPEEQUIPEM);
        TypeequipementDTO typeequipementDTO = typeequipementMapper.toDto(updatedTypeequipement);

        restTypeequipementMockMvc.perform(put("/api/typeequipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeequipementDTO)))
            .andExpect(status().isOk());

        // Validate the Typeequipement in the database
        List<Typeequipement> typeequipementList = typeequipementRepository.findAll();
        assertThat(typeequipementList).hasSize(databaseSizeBeforeUpdate);
        Typeequipement testTypeequipement = typeequipementList.get(typeequipementList.size() - 1);
        assertThat(testTypeequipement.getTypeequipem()).isEqualTo(UPDATED_TYPEEQUIPEM);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeequipement() throws Exception {
        int databaseSizeBeforeUpdate = typeequipementRepository.findAll().size();

        // Create the Typeequipement
        TypeequipementDTO typeequipementDTO = typeequipementMapper.toDto(typeequipement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeequipementMockMvc.perform(put("/api/typeequipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeequipementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Typeequipement in the database
        List<Typeequipement> typeequipementList = typeequipementRepository.findAll();
        assertThat(typeequipementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeequipement() throws Exception {
        // Initialize the database
        typeequipementRepository.saveAndFlush(typeequipement);

        int databaseSizeBeforeDelete = typeequipementRepository.findAll().size();

        // Delete the typeequipement
        restTypeequipementMockMvc.perform(delete("/api/typeequipements/{id}", typeequipement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Typeequipement> typeequipementList = typeequipementRepository.findAll();
        assertThat(typeequipementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Typeequipement.class);
        Typeequipement typeequipement1 = new Typeequipement();
        typeequipement1.setId(1L);
        Typeequipement typeequipement2 = new Typeequipement();
        typeequipement2.setId(typeequipement1.getId());
        assertThat(typeequipement1).isEqualTo(typeequipement2);
        typeequipement2.setId(2L);
        assertThat(typeequipement1).isNotEqualTo(typeequipement2);
        typeequipement1.setId(null);
        assertThat(typeequipement1).isNotEqualTo(typeequipement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeequipementDTO.class);
        TypeequipementDTO typeequipementDTO1 = new TypeequipementDTO();
        typeequipementDTO1.setId(1L);
        TypeequipementDTO typeequipementDTO2 = new TypeequipementDTO();
        assertThat(typeequipementDTO1).isNotEqualTo(typeequipementDTO2);
        typeequipementDTO2.setId(typeequipementDTO1.getId());
        assertThat(typeequipementDTO1).isEqualTo(typeequipementDTO2);
        typeequipementDTO2.setId(2L);
        assertThat(typeequipementDTO1).isNotEqualTo(typeequipementDTO2);
        typeequipementDTO1.setId(null);
        assertThat(typeequipementDTO1).isNotEqualTo(typeequipementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeequipementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeequipementMapper.fromId(null)).isNull();
    }
}
