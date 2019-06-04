package com.gmao.web.rest;
import com.gmao.service.ServiceeService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.ServiceeDTO;
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
 * REST controller for managing Servicee.
 */
@RestController
@RequestMapping("/api")
public class ServiceeResource {

    private final Logger log = LoggerFactory.getLogger(ServiceeResource.class);

    private static final String ENTITY_NAME = "servicee";

    private final ServiceeService serviceeService;

    public ServiceeResource(ServiceeService serviceeService) {
        this.serviceeService = serviceeService;
    }

    /**
     * POST  /servicees : Create a new servicee.
     *
     * @param serviceeDTO the serviceeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serviceeDTO, or with status 400 (Bad Request) if the servicee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/servicees")
    public ResponseEntity<ServiceeDTO> createServicee(@Valid @RequestBody ServiceeDTO serviceeDTO) throws URISyntaxException {
        log.debug("REST request to save Servicee : {}", serviceeDTO);
        if (serviceeDTO.getId() != null) {
            throw new BadRequestAlertException("A new servicee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceeDTO result = serviceeService.save(serviceeDTO);
        return ResponseEntity.created(new URI("/api/servicees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /servicees : Updates an existing servicee.
     *
     * @param serviceeDTO the serviceeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated serviceeDTO,
     * or with status 400 (Bad Request) if the serviceeDTO is not valid,
     * or with status 500 (Internal Server Error) if the serviceeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/servicees")
    public ResponseEntity<ServiceeDTO> updateServicee(@Valid @RequestBody ServiceeDTO serviceeDTO) throws URISyntaxException {
        log.debug("REST request to update Servicee : {}", serviceeDTO);
        if (serviceeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceeDTO result = serviceeService.save(serviceeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, serviceeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /servicees : get all the servicees.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of servicees in body
     */
    @GetMapping("/servicees")
    public ResponseEntity<List<ServiceeDTO>> getAllServicees(Pageable pageable) {
        log.debug("REST request to get a page of Servicees");
        Page<ServiceeDTO> page = serviceeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/servicees");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /servicees/:id : get the "id" servicee.
     *
     * @param id the id of the serviceeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the serviceeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/servicees/{id}")
    public ResponseEntity<ServiceeDTO> getServicee(@PathVariable Long id) {
        log.debug("REST request to get Servicee : {}", id);
        Optional<ServiceeDTO> serviceeDTO = serviceeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceeDTO);
    }

    /**
     * DELETE  /servicees/:id : delete the "id" servicee.
     *
     * @param id the id of the serviceeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/servicees/{id}")
    public ResponseEntity<Void> deleteServicee(@PathVariable Long id) {
        log.debug("REST request to delete Servicee : {}", id);
        serviceeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
