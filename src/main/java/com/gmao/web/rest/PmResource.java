package com.gmao.web.rest;
import com.gmao.service.PmService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.PmDTO;
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
 * REST controller for managing Pm.
 */
@RestController
@RequestMapping("/api")
public class PmResource {

    private final Logger log = LoggerFactory.getLogger(PmResource.class);

    private static final String ENTITY_NAME = "pm";

    private final PmService pmService;

    public PmResource(PmService pmService) {
        this.pmService = pmService;
    }

    /**
     * POST  /pms : Create a new pm.
     *
     * @param pmDTO the pmDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pmDTO, or with status 400 (Bad Request) if the pm has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pms")
    public ResponseEntity<PmDTO> createPm(@Valid @RequestBody PmDTO pmDTO) throws URISyntaxException {
        log.debug("REST request to save Pm : {}", pmDTO);
        if (pmDTO.getId() != null) {
            throw new BadRequestAlertException("A new pm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PmDTO result = pmService.save(pmDTO);
        return ResponseEntity.created(new URI("/api/pms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pms : Updates an existing pm.
     *
     * @param pmDTO the pmDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pmDTO,
     * or with status 400 (Bad Request) if the pmDTO is not valid,
     * or with status 500 (Internal Server Error) if the pmDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pms")
    public ResponseEntity<PmDTO> updatePm(@Valid @RequestBody PmDTO pmDTO) throws URISyntaxException {
        log.debug("REST request to update Pm : {}", pmDTO);
        if (pmDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PmDTO result = pmService.save(pmDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pmDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pms : get all the pms.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pms in body
     */
    @GetMapping("/pms")
    public ResponseEntity<List<PmDTO>> getAllPms(Pageable pageable) {
        log.debug("REST request to get a page of Pms");
        Page<PmDTO> page = pmService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pms");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /pms/:id : get the "id" pm.
     *
     * @param id the id of the pmDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pmDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pms/{id}")
    public ResponseEntity<PmDTO> getPm(@PathVariable Long id) {
        log.debug("REST request to get Pm : {}", id);
        Optional<PmDTO> pmDTO = pmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pmDTO);
    }

    /**
     * DELETE  /pms/:id : delete the "id" pm.
     *
     * @param id the id of the pmDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pms/{id}")
    public ResponseEntity<Void> deletePm(@PathVariable Long id) {
        log.debug("REST request to delete Pm : {}", id);
        pmService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
