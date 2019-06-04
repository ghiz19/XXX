package com.gmao.web.rest;
import com.gmao.service.LocalisationsService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.LocalisationsDTO;
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
 * REST controller for managing Localisations.
 */
@RestController
@RequestMapping("/api")
public class LocalisationsResource {

    private final Logger log = LoggerFactory.getLogger(LocalisationsResource.class);

    private static final String ENTITY_NAME = "localisations";

    private final LocalisationsService localisationsService;

    public LocalisationsResource(LocalisationsService localisationsService) {
        this.localisationsService = localisationsService;
    }

    /**
     * POST  /localisations : Create a new localisations.
     *
     * @param localisationsDTO the localisationsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new localisationsDTO, or with status 400 (Bad Request) if the localisations has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/localisations")
    public ResponseEntity<LocalisationsDTO> createLocalisations(@Valid @RequestBody LocalisationsDTO localisationsDTO) throws URISyntaxException {
        log.debug("REST request to save Localisations : {}", localisationsDTO);
        if (localisationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new localisations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LocalisationsDTO result = localisationsService.save(localisationsDTO);
        return ResponseEntity.created(new URI("/api/localisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /localisations : Updates an existing localisations.
     *
     * @param localisationsDTO the localisationsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated localisationsDTO,
     * or with status 400 (Bad Request) if the localisationsDTO is not valid,
     * or with status 500 (Internal Server Error) if the localisationsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/localisations")
    public ResponseEntity<LocalisationsDTO> updateLocalisations(@Valid @RequestBody LocalisationsDTO localisationsDTO) throws URISyntaxException {
        log.debug("REST request to update Localisations : {}", localisationsDTO);
        if (localisationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LocalisationsDTO result = localisationsService.save(localisationsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, localisationsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /localisations : get all the localisations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of localisations in body
     */
    @GetMapping("/localisations")
    public ResponseEntity<List<LocalisationsDTO>> getAllLocalisations(Pageable pageable) {
        log.debug("REST request to get a page of Localisations");
        Page<LocalisationsDTO> page = localisationsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/localisations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /localisations/:id : get the "id" localisations.
     *
     * @param id the id of the localisationsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the localisationsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/localisations/{id}")
    public ResponseEntity<LocalisationsDTO> getLocalisations(@PathVariable Long id) {
        log.debug("REST request to get Localisations : {}", id);
        Optional<LocalisationsDTO> localisationsDTO = localisationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(localisationsDTO);
    }

    /**
     * DELETE  /localisations/:id : delete the "id" localisations.
     *
     * @param id the id of the localisationsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/localisations/{id}")
    public ResponseEntity<Void> deleteLocalisations(@PathVariable Long id) {
        log.debug("REST request to delete Localisations : {}", id);
        localisationsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
