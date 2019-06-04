package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Servicee;
import com.gmao.repository.ServiceeRepository;
import com.gmao.service.ServiceeService;
import com.gmao.service.dto.ServiceeDTO;
import com.gmao.service.mapper.ServiceeMapper;
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
 * Test class for the ServiceeResource REST controller.
 *
 * @see ServiceeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class ServiceeResourceIntTest {

    private static final String DEFAULT_NOM_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SERVICE = "BBBBBBBBBB";

    @Autowired
    private ServiceeRepository serviceeRepository;

    @Autowired
    private ServiceeMapper serviceeMapper;

    @Autowired
    private ServiceeService serviceeService;

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

    private MockMvc restServiceeMockMvc;

    private Servicee servicee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServiceeResource serviceeResource = new ServiceeResource(serviceeService);
        this.restServiceeMockMvc = MockMvcBuilders.standaloneSetup(serviceeResource)
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
    public static Servicee createEntity(EntityManager em) {
        Servicee servicee = new Servicee()
            .nomService(DEFAULT_NOM_SERVICE);
        return servicee;
    }

    @Before
    public void initTest() {
        servicee = createEntity(em);
    }

    @Test
    @Transactional
    public void createServicee() throws Exception {
        int databaseSizeBeforeCreate = serviceeRepository.findAll().size();

        // Create the Servicee
        ServiceeDTO serviceeDTO = serviceeMapper.toDto(servicee);
        restServiceeMockMvc.perform(post("/api/servicees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceeDTO)))
            .andExpect(status().isCreated());

        // Validate the Servicee in the database
        List<Servicee> serviceeList = serviceeRepository.findAll();
        assertThat(serviceeList).hasSize(databaseSizeBeforeCreate + 1);
        Servicee testServicee = serviceeList.get(serviceeList.size() - 1);
        assertThat(testServicee.getNomService()).isEqualTo(DEFAULT_NOM_SERVICE);
    }

    @Test
    @Transactional
    public void createServiceeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceeRepository.findAll().size();

        // Create the Servicee with an existing ID
        servicee.setId(1L);
        ServiceeDTO serviceeDTO = serviceeMapper.toDto(servicee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceeMockMvc.perform(post("/api/servicees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Servicee in the database
        List<Servicee> serviceeList = serviceeRepository.findAll();
        assertThat(serviceeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomServiceIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceeRepository.findAll().size();
        // set the field null
        servicee.setNomService(null);

        // Create the Servicee, which fails.
        ServiceeDTO serviceeDTO = serviceeMapper.toDto(servicee);

        restServiceeMockMvc.perform(post("/api/servicees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceeDTO)))
            .andExpect(status().isBadRequest());

        List<Servicee> serviceeList = serviceeRepository.findAll();
        assertThat(serviceeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllServicees() throws Exception {
        // Initialize the database
        serviceeRepository.saveAndFlush(servicee);

        // Get all the serviceeList
        restServiceeMockMvc.perform(get("/api/servicees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(servicee.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomService").value(hasItem(DEFAULT_NOM_SERVICE.toString())));
    }
    
    @Test
    @Transactional
    public void getServicee() throws Exception {
        // Initialize the database
        serviceeRepository.saveAndFlush(servicee);

        // Get the servicee
        restServiceeMockMvc.perform(get("/api/servicees/{id}", servicee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(servicee.getId().intValue()))
            .andExpect(jsonPath("$.nomService").value(DEFAULT_NOM_SERVICE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingServicee() throws Exception {
        // Get the servicee
        restServiceeMockMvc.perform(get("/api/servicees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServicee() throws Exception {
        // Initialize the database
        serviceeRepository.saveAndFlush(servicee);

        int databaseSizeBeforeUpdate = serviceeRepository.findAll().size();

        // Update the servicee
        Servicee updatedServicee = serviceeRepository.findById(servicee.getId()).get();
        // Disconnect from session so that the updates on updatedServicee are not directly saved in db
        em.detach(updatedServicee);
        updatedServicee
            .nomService(UPDATED_NOM_SERVICE);
        ServiceeDTO serviceeDTO = serviceeMapper.toDto(updatedServicee);

        restServiceeMockMvc.perform(put("/api/servicees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceeDTO)))
            .andExpect(status().isOk());

        // Validate the Servicee in the database
        List<Servicee> serviceeList = serviceeRepository.findAll();
        assertThat(serviceeList).hasSize(databaseSizeBeforeUpdate);
        Servicee testServicee = serviceeList.get(serviceeList.size() - 1);
        assertThat(testServicee.getNomService()).isEqualTo(UPDATED_NOM_SERVICE);
    }

    @Test
    @Transactional
    public void updateNonExistingServicee() throws Exception {
        int databaseSizeBeforeUpdate = serviceeRepository.findAll().size();

        // Create the Servicee
        ServiceeDTO serviceeDTO = serviceeMapper.toDto(servicee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceeMockMvc.perform(put("/api/servicees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Servicee in the database
        List<Servicee> serviceeList = serviceeRepository.findAll();
        assertThat(serviceeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServicee() throws Exception {
        // Initialize the database
        serviceeRepository.saveAndFlush(servicee);

        int databaseSizeBeforeDelete = serviceeRepository.findAll().size();

        // Delete the servicee
        restServiceeMockMvc.perform(delete("/api/servicees/{id}", servicee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Servicee> serviceeList = serviceeRepository.findAll();
        assertThat(serviceeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Servicee.class);
        Servicee servicee1 = new Servicee();
        servicee1.setId(1L);
        Servicee servicee2 = new Servicee();
        servicee2.setId(servicee1.getId());
        assertThat(servicee1).isEqualTo(servicee2);
        servicee2.setId(2L);
        assertThat(servicee1).isNotEqualTo(servicee2);
        servicee1.setId(null);
        assertThat(servicee1).isNotEqualTo(servicee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceeDTO.class);
        ServiceeDTO serviceeDTO1 = new ServiceeDTO();
        serviceeDTO1.setId(1L);
        ServiceeDTO serviceeDTO2 = new ServiceeDTO();
        assertThat(serviceeDTO1).isNotEqualTo(serviceeDTO2);
        serviceeDTO2.setId(serviceeDTO1.getId());
        assertThat(serviceeDTO1).isEqualTo(serviceeDTO2);
        serviceeDTO2.setId(2L);
        assertThat(serviceeDTO1).isNotEqualTo(serviceeDTO2);
        serviceeDTO1.setId(null);
        assertThat(serviceeDTO1).isNotEqualTo(serviceeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(serviceeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(serviceeMapper.fromId(null)).isNull();
    }
}
