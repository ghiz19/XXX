package com.gmao.web.rest;
import com.gmao.service.EquipeService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.EquipeDTO;
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
 * REST controller for managing Equipe.
 */
@RestController
@RequestMapping("/api")
public class EquipeResource {

    private final Logger log = LoggerFactory.getLogger(EquipeResource.class);

    private static final String ENTITY_NAME = "equipe";

    private final EquipeService equipeService;

    public EquipeResource(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    /**
     * POST  /equipes : Create a new equipe.
     *
     * @param equipeDTO the equipeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new equipeDTO, or with status 400 (Bad Request) if the equipe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/equipes")
    public ResponseEntity<EquipeDTO> createEquipe(@Valid @RequestBody EquipeDTO equipeDTO) throws URISyntaxException {
        log.debug("REST request to save Equipe : {}", equipeDTO);
        if (equipeDTO.getId() != null) {
            throw new BadRequestAlertException("A new equipe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EquipeDTO result = equipeService.save(equipeDTO);
        return ResponseEntity.created(new URI("/api/equipes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /equipes : Updates an existing equipe.
     *
     * @param equipeDTO the equipeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated equipeDTO,
     * or with status 400 (Bad Request) if the equipeDTO is not valid,
     * or with status 500 (Internal Server Error) if the equipeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/equipes")
    public ResponseEntity<EquipeDTO> updateEquipe(@Valid @RequestBody EquipeDTO equipeDTO) throws URISyntaxException {
        log.debug("REST request to update Equipe : {}", equipeDTO);
        if (equipeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EquipeDTO result = equipeService.save(equipeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, equipeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /equipes : get all the equipes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of equipes in body
     */
    @GetMapping("/equipes")
    public ResponseEntity<List<EquipeDTO>> getAllEquipes(Pageable pageable) {
        log.debug("REST request to get a page of Equipes");
        Page<EquipeDTO> page = equipeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/equipes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /equipes/:id : get the "id" equipe.
     *
     * @param id the id of the equipeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the equipeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/equipes/{id}")
    public ResponseEntity<EquipeDTO> getEquipe(@PathVariable Long id) {
        log.debug("REST request to get Equipe : {}", id);
        Optional<EquipeDTO> equipeDTO = equipeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(equipeDTO);
    }

    /**
     * DELETE  /equipes/:id : delete the "id" equipe.
     *
     * @param id the id of the equipeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/equipes/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Long id) {
        log.debug("REST request to delete Equipe : {}", id);
        equipeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
