package com.gmao.web.rest;
import com.gmao.service.EtatService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.EtatDTO;
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
 * REST controller for managing Etat.
 */
@RestController
@RequestMapping("/api")
public class EtatResource {

    private final Logger log = LoggerFactory.getLogger(EtatResource.class);

    private static final String ENTITY_NAME = "etat";

    private final EtatService etatService;

    public EtatResource(EtatService etatService) {
        this.etatService = etatService;
    }

    /**
     * POST  /etats : Create a new etat.
     *
     * @param etatDTO the etatDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new etatDTO, or with status 400 (Bad Request) if the etat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/etats")
    public ResponseEntity<EtatDTO> createEtat(@Valid @RequestBody EtatDTO etatDTO) throws URISyntaxException {
        log.debug("REST request to save Etat : {}", etatDTO);
        if (etatDTO.getId() != null) {
            throw new BadRequestAlertException("A new etat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatDTO result = etatService.save(etatDTO);
        return ResponseEntity.created(new URI("/api/etats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /etats : Updates an existing etat.
     *
     * @param etatDTO the etatDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated etatDTO,
     * or with status 400 (Bad Request) if the etatDTO is not valid,
     * or with status 500 (Internal Server Error) if the etatDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/etats")
    public ResponseEntity<EtatDTO> updateEtat(@Valid @RequestBody EtatDTO etatDTO) throws URISyntaxException {
        log.debug("REST request to update Etat : {}", etatDTO);
        if (etatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatDTO result = etatService.save(etatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, etatDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /etats : get all the etats.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of etats in body
     */
    @GetMapping("/etats")
    public ResponseEntity<List<EtatDTO>> getAllEtats(Pageable pageable) {
        log.debug("REST request to get a page of Etats");
        Page<EtatDTO> page = etatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/etats");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /etats/:id : get the "id" etat.
     *
     * @param id the id of the etatDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the etatDTO, or with status 404 (Not Found)
     */
    @GetMapping("/etats/{id}")
    public ResponseEntity<EtatDTO> getEtat(@PathVariable Long id) {
        log.debug("REST request to get Etat : {}", id);
        Optional<EtatDTO> etatDTO = etatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatDTO);
    }

    /**
     * DELETE  /etats/:id : delete the "id" etat.
     *
     * @param id the id of the etatDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/etats/{id}")
    public ResponseEntity<Void> deleteEtat(@PathVariable Long id) {
        log.debug("REST request to delete Etat : {}", id);
        etatService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
