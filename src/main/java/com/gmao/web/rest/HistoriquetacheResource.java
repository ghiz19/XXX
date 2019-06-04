package com.gmao.web.rest;
import com.gmao.service.HistoriquetacheService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.HistoriquetacheDTO;
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
 * REST controller for managing Historiquetache.
 */
@RestController
@RequestMapping("/api")
public class HistoriquetacheResource {

    private final Logger log = LoggerFactory.getLogger(HistoriquetacheResource.class);

    private static final String ENTITY_NAME = "historiquetache";

    private final HistoriquetacheService historiquetacheService;

    public HistoriquetacheResource(HistoriquetacheService historiquetacheService) {
        this.historiquetacheService = historiquetacheService;
    }

    /**
     * POST  /historiquetaches : Create a new historiquetache.
     *
     * @param historiquetacheDTO the historiquetacheDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new historiquetacheDTO, or with status 400 (Bad Request) if the historiquetache has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/historiquetaches")
    public ResponseEntity<HistoriquetacheDTO> createHistoriquetache(@Valid @RequestBody HistoriquetacheDTO historiquetacheDTO) throws URISyntaxException {
        log.debug("REST request to save Historiquetache : {}", historiquetacheDTO);
        if (historiquetacheDTO.getId() != null) {
            throw new BadRequestAlertException("A new historiquetache cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoriquetacheDTO result = historiquetacheService.save(historiquetacheDTO);
        return ResponseEntity.created(new URI("/api/historiquetaches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /historiquetaches : Updates an existing historiquetache.
     *
     * @param historiquetacheDTO the historiquetacheDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated historiquetacheDTO,
     * or with status 400 (Bad Request) if the historiquetacheDTO is not valid,
     * or with status 500 (Internal Server Error) if the historiquetacheDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/historiquetaches")
    public ResponseEntity<HistoriquetacheDTO> updateHistoriquetache(@Valid @RequestBody HistoriquetacheDTO historiquetacheDTO) throws URISyntaxException {
        log.debug("REST request to update Historiquetache : {}", historiquetacheDTO);
        if (historiquetacheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistoriquetacheDTO result = historiquetacheService.save(historiquetacheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, historiquetacheDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /historiquetaches : get all the historiquetaches.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of historiquetaches in body
     */
    @GetMapping("/historiquetaches")
    public ResponseEntity<List<HistoriquetacheDTO>> getAllHistoriquetaches(Pageable pageable) {
        log.debug("REST request to get a page of Historiquetaches");
        Page<HistoriquetacheDTO> page = historiquetacheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/historiquetaches");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /historiquetaches/:id : get the "id" historiquetache.
     *
     * @param id the id of the historiquetacheDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiquetacheDTO, or with status 404 (Not Found)
     */
    @GetMapping("/historiquetaches/{id}")
    public ResponseEntity<HistoriquetacheDTO> getHistoriquetache(@PathVariable Long id) {
        log.debug("REST request to get Historiquetache : {}", id);
        Optional<HistoriquetacheDTO> historiquetacheDTO = historiquetacheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historiquetacheDTO);
    }

    /**
     * DELETE  /historiquetaches/:id : delete the "id" historiquetache.
     *
     * @param id the id of the historiquetacheDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/historiquetaches/{id}")
    public ResponseEntity<Void> deleteHistoriquetache(@PathVariable Long id) {
        log.debug("REST request to delete Historiquetache : {}", id);
        historiquetacheService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
