package com.gmao.web.rest;
import com.gmao.service.TypeequipementService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.TypeequipementDTO;
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
 * REST controller for managing Typeequipement.
 */
@RestController
@RequestMapping("/api")
public class TypeequipementResource {

    private final Logger log = LoggerFactory.getLogger(TypeequipementResource.class);

    private static final String ENTITY_NAME = "typeequipement";

    private final TypeequipementService typeequipementService;

    public TypeequipementResource(TypeequipementService typeequipementService) {
        this.typeequipementService = typeequipementService;
    }

    /**
     * POST  /typeequipements : Create a new typeequipement.
     *
     * @param typeequipementDTO the typeequipementDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeequipementDTO, or with status 400 (Bad Request) if the typeequipement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/typeequipements")
    public ResponseEntity<TypeequipementDTO> createTypeequipement(@Valid @RequestBody TypeequipementDTO typeequipementDTO) throws URISyntaxException {
        log.debug("REST request to save Typeequipement : {}", typeequipementDTO);
        if (typeequipementDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeequipement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeequipementDTO result = typeequipementService.save(typeequipementDTO);
        return ResponseEntity.created(new URI("/api/typeequipements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /typeequipements : Updates an existing typeequipement.
     *
     * @param typeequipementDTO the typeequipementDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeequipementDTO,
     * or with status 400 (Bad Request) if the typeequipementDTO is not valid,
     * or with status 500 (Internal Server Error) if the typeequipementDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/typeequipements")
    public ResponseEntity<TypeequipementDTO> updateTypeequipement(@Valid @RequestBody TypeequipementDTO typeequipementDTO) throws URISyntaxException {
        log.debug("REST request to update Typeequipement : {}", typeequipementDTO);
        if (typeequipementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeequipementDTO result = typeequipementService.save(typeequipementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeequipementDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /typeequipements : get all the typeequipements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of typeequipements in body
     */
    @GetMapping("/typeequipements")
    public ResponseEntity<List<TypeequipementDTO>> getAllTypeequipements(Pageable pageable) {
        log.debug("REST request to get a page of Typeequipements");
        Page<TypeequipementDTO> page = typeequipementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/typeequipements");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /typeequipements/:id : get the "id" typeequipement.
     *
     * @param id the id of the typeequipementDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeequipementDTO, or with status 404 (Not Found)
     */
    @GetMapping("/typeequipements/{id}")
    public ResponseEntity<TypeequipementDTO> getTypeequipement(@PathVariable Long id) {
        log.debug("REST request to get Typeequipement : {}", id);
        Optional<TypeequipementDTO> typeequipementDTO = typeequipementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeequipementDTO);
    }

    /**
     * DELETE  /typeequipements/:id : delete the "id" typeequipement.
     *
     * @param id the id of the typeequipementDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/typeequipements/{id}")
    public ResponseEntity<Void> deleteTypeequipement(@PathVariable Long id) {
        log.debug("REST request to delete Typeequipement : {}", id);
        typeequipementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
