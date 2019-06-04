package com.gmao.web.rest;
import com.gmao.service.DemandeinterventionService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.DemandeinterventionDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Demandeintervention.
 */
@RestController
@RequestMapping("/api")
public class DemandeinterventionResource {

    private final Logger log = LoggerFactory.getLogger(DemandeinterventionResource.class);

    private static final String ENTITY_NAME = "demandeintervention";

    private final DemandeinterventionService demandeinterventionService;

    public DemandeinterventionResource(DemandeinterventionService demandeinterventionService) {
        this.demandeinterventionService = demandeinterventionService;
    }

    /**
     * POST  /demandeinterventions : Create a new demandeintervention.
     *
     * @param demandeinterventionDTO the demandeinterventionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new demandeinterventionDTO, or with status 400 (Bad Request) if the demandeintervention has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/demandeinterventions")
    public ResponseEntity<DemandeinterventionDTO> createDemandeintervention(@Valid @RequestBody DemandeinterventionDTO demandeinterventionDTO) throws URISyntaxException {
        log.debug("REST request to save Demandeintervention : {}", demandeinterventionDTO);
        if (demandeinterventionDTO.getId() != null) {
            throw new BadRequestAlertException("A new demandeintervention cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DemandeinterventionDTO result = demandeinterventionService.save(demandeinterventionDTO);
        return ResponseEntity.created(new URI("/api/demandeinterventions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /demandeinterventions : Updates an existing demandeintervention.
     *
     * @param demandeinterventionDTO the demandeinterventionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated demandeinterventionDTO,
     * or with status 400 (Bad Request) if the demandeinterventionDTO is not valid,
     * or with status 500 (Internal Server Error) if the demandeinterventionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/demandeinterventions")
    public ResponseEntity<DemandeinterventionDTO> updateDemandeintervention(@Valid @RequestBody DemandeinterventionDTO demandeinterventionDTO) throws URISyntaxException {
        log.debug("REST request to update Demandeintervention : {}", demandeinterventionDTO);
        if (demandeinterventionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DemandeinterventionDTO result = demandeinterventionService.save(demandeinterventionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, demandeinterventionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /demandeinterventions : get all the demandeinterventions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of demandeinterventions in body
     */
    @GetMapping("/demandeinterventions")
    public ResponseEntity<List<DemandeinterventionDTO>> getAllDemandeinterventions(Pageable pageable) {
        log.debug("REST request to get a page of Demandeinterventions");
        Page<DemandeinterventionDTO> page = demandeinterventionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/demandeinterventions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /demandeinterventions/:id : get the "id" demandeintervention.
     *
     * @param id the id of the demandeinterventionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the demandeinterventionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/demandeinterventions/{id}")
    public ResponseEntity<DemandeinterventionDTO> getDemandeintervention(@PathVariable Long id) {
        log.debug("REST request to get Demandeintervention : {}", id);
        Optional<DemandeinterventionDTO> demandeinterventionDTO = demandeinterventionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demandeinterventionDTO);
    }

    /**
     * DELETE  /demandeinterventions/:id : delete the "id" demandeintervention.
     *
     * @param id the id of the demandeinterventionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/demandeinterventions/{id}")
    public ResponseEntity<Void> deleteDemandeintervention(@PathVariable Long id) {
        log.debug("REST request to delete Demandeintervention : {}", id);
        demandeinterventionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
