package com.gmao.web.rest;
import com.gmao.service.InterevntionService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.InterevntionDTO;
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
 * REST controller for managing Interevntion.
 */
@RestController
@RequestMapping("/api")
public class InterevntionResource {

    private final Logger log = LoggerFactory.getLogger(InterevntionResource.class);

    private static final String ENTITY_NAME = "interevntion";

    private final InterevntionService interevntionService;

    public InterevntionResource(InterevntionService interevntionService) {
        this.interevntionService = interevntionService;
    }

    /**
     * POST  /interevntions : Create a new interevntion.
     *
     * @param interevntionDTO the interevntionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new interevntionDTO, or with status 400 (Bad Request) if the interevntion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/interevntions")
    public ResponseEntity<InterevntionDTO> createInterevntion(@Valid @RequestBody InterevntionDTO interevntionDTO) throws URISyntaxException {
        log.debug("REST request to save Interevntion : {}", interevntionDTO);
        if (interevntionDTO.getId() != null) {
            throw new BadRequestAlertException("A new interevntion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InterevntionDTO result = interevntionService.save(interevntionDTO);
        return ResponseEntity.created(new URI("/api/interevntions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /interevntions : Updates an existing interevntion.
     *
     * @param interevntionDTO the interevntionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated interevntionDTO,
     * or with status 400 (Bad Request) if the interevntionDTO is not valid,
     * or with status 500 (Internal Server Error) if the interevntionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/interevntions")
    public ResponseEntity<InterevntionDTO> updateInterevntion(@Valid @RequestBody InterevntionDTO interevntionDTO) throws URISyntaxException {
        log.debug("REST request to update Interevntion : {}", interevntionDTO);
        if (interevntionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InterevntionDTO result = interevntionService.save(interevntionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, interevntionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /interevntions : get all the interevntions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of interevntions in body
     */
    @GetMapping("/interevntions")
    public ResponseEntity<List<InterevntionDTO>> getAllInterevntions(Pageable pageable) {
        log.debug("REST request to get a page of Interevntions");
        Page<InterevntionDTO> page = interevntionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/interevntions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /interevntions/:id : get the "id" interevntion.
     *
     * @param id the id of the interevntionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the interevntionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/interevntions/{id}")
    public ResponseEntity<InterevntionDTO> getInterevntion(@PathVariable Long id) {
        log.debug("REST request to get Interevntion : {}", id);
        Optional<InterevntionDTO> interevntionDTO = interevntionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(interevntionDTO);
    }

    /**
     * DELETE  /interevntions/:id : delete the "id" interevntion.
     *
     * @param id the id of the interevntionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/interevntions/{id}")
    public ResponseEntity<Void> deleteInterevntion(@PathVariable Long id) {
        log.debug("REST request to delete Interevntion : {}", id);
        interevntionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
