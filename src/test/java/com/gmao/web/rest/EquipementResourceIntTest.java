package com.gmao.web.rest;

import com.gmao.GmaovApp;

import com.gmao.domain.Equipement;
import com.gmao.repository.EquipementRepository;
import com.gmao.service.EquipementService;
import com.gmao.service.dto.EquipementDTO;
import com.gmao.service.mapper.EquipementMapper;
import com.gmao.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import static com.gmao.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EquipementResource REST controller.
 *
 * @see EquipementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmaovApp.class)
public class EquipementResourceIntTest {

    private static final String DEFAULT_NOM_EQUIPEMENT = "AAAAAAAAAA";
    private static final String UPDATED_NOM_EQUIPEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_MARQUE = "AAAAAAAAAA";
    private static final String UPDATED_MARQUE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ACHAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ACHAT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATEDERNIEREMAINTENANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEDERNIEREMAINTENANCE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATEEXPERATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEEXPERATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FABRICANT = "AAAAAAAAAA";
    private static final String UPDATED_FABRICANT = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIX_ACHAT = 1D;
    private static final Double UPDATED_PRIX_ACHAT = 2D;

    private static final Integer DEFAULT_NUMERO_SERIE = 1;
    private static final Integer UPDATED_NUMERO_SERIE = 2;

    private static final Integer DEFAULT_NUMERO_COMMANDE = 1;
    private static final Integer UPDATED_NUMERO_COMMANDE = 2;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private EquipementRepository equipementRepository;

    @Mock
    private EquipementRepository equipementRepositoryMock;

    @Autowired
    private EquipementMapper equipementMapper;

    @Mock
    private EquipementService equipementServiceMock;

    @Autowired
    private EquipementService equipementService;

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

    private MockMvc restEquipementMockMvc;

    private Equipement equipement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EquipementResource equipementResource = new EquipementResource(equipementService);
        this.restEquipementMockMvc = MockMvcBuilders.standaloneSetup(equipementResource)
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
    public static Equipement createEntity(EntityManager em) {
        Equipement equipement = new Equipement()
            .nomEquipement(DEFAULT_NOM_EQUIPEMENT)
            .marque(DEFAULT_MARQUE)
            .description(DEFAULT_DESCRIPTION)
            .dateAchat(DEFAULT_DATE_ACHAT)
            .datedernieremaintenance(DEFAULT_DATEDERNIEREMAINTENANCE)
            .dateexperation(DEFAULT_DATEEXPERATION)
            .fabricant(DEFAULT_FABRICANT)
            .prixAchat(DEFAULT_PRIX_ACHAT)
            .numeroSerie(DEFAULT_NUMERO_SERIE)
            .numeroCommande(DEFAULT_NUMERO_COMMANDE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return equipement;
    }

    @Before
    public void initTest() {
        equipement = createEntity(em);
    }

    @Test
    @Transactional
    public void createEquipement() throws Exception {
        int databaseSizeBeforeCreate = equipementRepository.findAll().size();

        // Create the Equipement
        EquipementDTO equipementDTO = equipementMapper.toDto(equipement);
        restEquipementMockMvc.perform(post("/api/equipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementDTO)))
            .andExpect(status().isCreated());

        // Validate the Equipement in the database
        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeCreate + 1);
        Equipement testEquipement = equipementList.get(equipementList.size() - 1);
        assertThat(testEquipement.getNomEquipement()).isEqualTo(DEFAULT_NOM_EQUIPEMENT);
        assertThat(testEquipement.getMarque()).isEqualTo(DEFAULT_MARQUE);
        assertThat(testEquipement.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testEquipement.getDateAchat()).isEqualTo(DEFAULT_DATE_ACHAT);
        assertThat(testEquipement.getDatedernieremaintenance()).isEqualTo(DEFAULT_DATEDERNIEREMAINTENANCE);
        assertThat(testEquipement.getDateexperation()).isEqualTo(DEFAULT_DATEEXPERATION);
        assertThat(testEquipement.getFabricant()).isEqualTo(DEFAULT_FABRICANT);
        assertThat(testEquipement.getPrixAchat()).isEqualTo(DEFAULT_PRIX_ACHAT);
        assertThat(testEquipement.getNumeroSerie()).isEqualTo(DEFAULT_NUMERO_SERIE);
        assertThat(testEquipement.getNumeroCommande()).isEqualTo(DEFAULT_NUMERO_COMMANDE);
        assertThat(testEquipement.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testEquipement.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createEquipementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = equipementRepository.findAll().size();

        // Create the Equipement with an existing ID
        equipement.setId(1L);
        EquipementDTO equipementDTO = equipementMapper.toDto(equipement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEquipementMockMvc.perform(post("/api/equipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Equipement in the database
        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomEquipementIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipementRepository.findAll().size();
        // set the field null
        equipement.setNomEquipement(null);

        // Create the Equipement, which fails.
        EquipementDTO equipementDTO = equipementMapper.toDto(equipement);

        restEquipementMockMvc.perform(post("/api/equipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementDTO)))
            .andExpect(status().isBadRequest());

        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMarqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipementRepository.findAll().size();
        // set the field null
        equipement.setMarque(null);

        // Create the Equipement, which fails.
        EquipementDTO equipementDTO = equipementMapper.toDto(equipement);

        restEquipementMockMvc.perform(post("/api/equipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementDTO)))
            .andExpect(status().isBadRequest());

        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateexperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipementRepository.findAll().size();
        // set the field null
        equipement.setDateexperation(null);

        // Create the Equipement, which fails.
        EquipementDTO equipementDTO = equipementMapper.toDto(equipement);

        restEquipementMockMvc.perform(post("/api/equipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementDTO)))
            .andExpect(status().isBadRequest());

        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFabricantIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipementRepository.findAll().size();
        // set the field null
        equipement.setFabricant(null);

        // Create the Equipement, which fails.
        EquipementDTO equipementDTO = equipementMapper.toDto(equipement);

        restEquipementMockMvc.perform(post("/api/equipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementDTO)))
            .andExpect(status().isBadRequest());

        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixAchatIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipementRepository.findAll().size();
        // set the field null
        equipement.setPrixAchat(null);

        // Create the Equipement, which fails.
        EquipementDTO equipementDTO = equipementMapper.toDto(equipement);

        restEquipementMockMvc.perform(post("/api/equipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementDTO)))
            .andExpect(status().isBadRequest());

        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEquipements() throws Exception {
        // Initialize the database
        equipementRepository.saveAndFlush(equipement);

        // Get all the equipementList
        restEquipementMockMvc.perform(get("/api/equipements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(equipement.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomEquipement").value(hasItem(DEFAULT_NOM_EQUIPEMENT.toString())))
            .andExpect(jsonPath("$.[*].marque").value(hasItem(DEFAULT_MARQUE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateAchat").value(hasItem(DEFAULT_DATE_ACHAT.toString())))
            .andExpect(jsonPath("$.[*].datedernieremaintenance").value(hasItem(DEFAULT_DATEDERNIEREMAINTENANCE.toString())))
            .andExpect(jsonPath("$.[*].dateexperation").value(hasItem(DEFAULT_DATEEXPERATION.toString())))
            .andExpect(jsonPath("$.[*].fabricant").value(hasItem(DEFAULT_FABRICANT.toString())))
            .andExpect(jsonPath("$.[*].prixAchat").value(hasItem(DEFAULT_PRIX_ACHAT.doubleValue())))
            .andExpect(jsonPath("$.[*].numeroSerie").value(hasItem(DEFAULT_NUMERO_SERIE)))
            .andExpect(jsonPath("$.[*].numeroCommande").value(hasItem(DEFAULT_NUMERO_COMMANDE)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllEquipementsWithEagerRelationshipsIsEnabled() throws Exception {
        EquipementResource equipementResource = new EquipementResource(equipementServiceMock);
        when(equipementServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restEquipementMockMvc = MockMvcBuilders.standaloneSetup(equipementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restEquipementMockMvc.perform(get("/api/equipements?eagerload=true"))
        .andExpect(status().isOk());

        verify(equipementServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllEquipementsWithEagerRelationshipsIsNotEnabled() throws Exception {
        EquipementResource equipementResource = new EquipementResource(equipementServiceMock);
            when(equipementServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restEquipementMockMvc = MockMvcBuilders.standaloneSetup(equipementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restEquipementMockMvc.perform(get("/api/equipements?eagerload=true"))
        .andExpect(status().isOk());

            verify(equipementServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getEquipement() throws Exception {
        // Initialize the database
        equipementRepository.saveAndFlush(equipement);

        // Get the equipement
        restEquipementMockMvc.perform(get("/api/equipements/{id}", equipement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(equipement.getId().intValue()))
            .andExpect(jsonPath("$.nomEquipement").value(DEFAULT_NOM_EQUIPEMENT.toString()))
            .andExpect(jsonPath("$.marque").value(DEFAULT_MARQUE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateAchat").value(DEFAULT_DATE_ACHAT.toString()))
            .andExpect(jsonPath("$.datedernieremaintenance").value(DEFAULT_DATEDERNIEREMAINTENANCE.toString()))
            .andExpect(jsonPath("$.dateexperation").value(DEFAULT_DATEEXPERATION.toString()))
            .andExpect(jsonPath("$.fabricant").value(DEFAULT_FABRICANT.toString()))
            .andExpect(jsonPath("$.prixAchat").value(DEFAULT_PRIX_ACHAT.doubleValue()))
            .andExpect(jsonPath("$.numeroSerie").value(DEFAULT_NUMERO_SERIE))
            .andExpect(jsonPath("$.numeroCommande").value(DEFAULT_NUMERO_COMMANDE))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }

    @Test
    @Transactional
    public void getNonExistingEquipement() throws Exception {
        // Get the equipement
        restEquipementMockMvc.perform(get("/api/equipements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEquipement() throws Exception {
        // Initialize the database
        equipementRepository.saveAndFlush(equipement);

        int databaseSizeBeforeUpdate = equipementRepository.findAll().size();

        // Update the equipement
        Equipement updatedEquipement = equipementRepository.findById(equipement.getId()).get();
        // Disconnect from session so that the updates on updatedEquipement are not directly saved in db
        em.detach(updatedEquipement);
        updatedEquipement
            .nomEquipement(UPDATED_NOM_EQUIPEMENT)
            .marque(UPDATED_MARQUE)
            .description(UPDATED_DESCRIPTION)
            .dateAchat(UPDATED_DATE_ACHAT)
            .datedernieremaintenance(UPDATED_DATEDERNIEREMAINTENANCE)
            .dateexperation(UPDATED_DATEEXPERATION)
            .fabricant(UPDATED_FABRICANT)
            .prixAchat(UPDATED_PRIX_ACHAT)
            .numeroSerie(UPDATED_NUMERO_SERIE)
            .numeroCommande(UPDATED_NUMERO_COMMANDE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        EquipementDTO equipementDTO = equipementMapper.toDto(updatedEquipement);

        restEquipementMockMvc.perform(put("/api/equipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementDTO)))
            .andExpect(status().isOk());

        // Validate the Equipement in the database
        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeUpdate);
        Equipement testEquipement = equipementList.get(equipementList.size() - 1);
        assertThat(testEquipement.getNomEquipement()).isEqualTo(UPDATED_NOM_EQUIPEMENT);
        assertThat(testEquipement.getMarque()).isEqualTo(UPDATED_MARQUE);
        assertThat(testEquipement.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEquipement.getDateAchat()).isEqualTo(UPDATED_DATE_ACHAT);
        assertThat(testEquipement.getDatedernieremaintenance()).isEqualTo(UPDATED_DATEDERNIEREMAINTENANCE);
        assertThat(testEquipement.getDateexperation()).isEqualTo(UPDATED_DATEEXPERATION);
        assertThat(testEquipement.getFabricant()).isEqualTo(UPDATED_FABRICANT);
        assertThat(testEquipement.getPrixAchat()).isEqualTo(UPDATED_PRIX_ACHAT);
        assertThat(testEquipement.getNumeroSerie()).isEqualTo(UPDATED_NUMERO_SERIE);
        assertThat(testEquipement.getNumeroCommande()).isEqualTo(UPDATED_NUMERO_COMMANDE);
        assertThat(testEquipement.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testEquipement.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingEquipement() throws Exception {
        int databaseSizeBeforeUpdate = equipementRepository.findAll().size();

        // Create the Equipement
        EquipementDTO equipementDTO = equipementMapper.toDto(equipement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEquipementMockMvc.perform(put("/api/equipements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Equipement in the database
        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEquipement() throws Exception {
        // Initialize the database
        equipementRepository.saveAndFlush(equipement);

        int databaseSizeBeforeDelete = equipementRepository.findAll().size();

        // Delete the equipement
        restEquipementMockMvc.perform(delete("/api/equipements/{id}", equipement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Equipement> equipementList = equipementRepository.findAll();
        assertThat(equipementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Equipement.class);
        Equipement equipement1 = new Equipement();
        equipement1.setId(1L);
        Equipement equipement2 = new Equipement();
        equipement2.setId(equipement1.getId());
        assertThat(equipement1).isEqualTo(equipement2);
        equipement2.setId(2L);
        assertThat(equipement1).isNotEqualTo(equipement2);
        equipement1.setId(null);
        assertThat(equipement1).isNotEqualTo(equipement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EquipementDTO.class);
        EquipementDTO equipementDTO1 = new EquipementDTO();
        equipementDTO1.setId(1L);
        EquipementDTO equipementDTO2 = new EquipementDTO();
        assertThat(equipementDTO1).isNotEqualTo(equipementDTO2);
        equipementDTO2.setId(equipementDTO1.getId());
        assertThat(equipementDTO1).isEqualTo(equipementDTO2);
        equipementDTO2.setId(2L);
        assertThat(equipementDTO1).isNotEqualTo(equipementDTO2);
        equipementDTO1.setId(null);
        assertThat(equipementDTO1).isNotEqualTo(equipementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(equipementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(equipementMapper.fromId(null)).isNull();
    }
}
