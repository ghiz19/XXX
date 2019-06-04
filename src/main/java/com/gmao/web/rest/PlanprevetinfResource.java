package com.gmao.web.rest;
import com.gmao.service.PlanprevetinfService;
import com.gmao.web.rest.errors.BadRequestAlertException;
import com.gmao.web.rest.util.HeaderUtil;
import com.gmao.web.rest.util.PaginationUtil;
import com.gmao.service.dto.PlanprevetinfDTO;
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
 * REST controller for managing Planprevetinf.
 */
@RestController
@RequestMapping("/api")
public class PlanprevetinfResource {

    private final Logger log = LoggerFactory.getLogger(PlanprevetinfResource.class);

    private static final String ENTITY_NAME = "planprevetinf";

    private final PlanprevetinfService planprevetinfService;

    public PlanprevetinfResource(PlanprevetinfService planprevetinfService) {
        this.planprevetinfService = planprevetinfService;
    }

    /**
     * POST  /planprevetinfs : Create a new planprevetinf.
     *
     * @param planprevetinfDTO the planprevetinfDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planprevetinfDTO, or with status 400 (Bad Request) if the planprevetinf has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/planprevetinfs")
    public ResponseEntity<PlanprevetinfDTO> createPlanprevetinf(@Valid @RequestBody PlanprevetinfDTO planprevetinfDTO) throws URISyntaxException {
        log.debug("REST request to save Planprevetinf : {}", planprevetinfDTO);
        if (planprevetinfDTO.getId() != null) {
            throw new BadRequestAlertException("A new planprevetinf cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanprevetinfDTO result = planprevetinfService.save(planprevetinfDTO);
        return ResponseEntity.created(new URI("/api/planprevetinfs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /planprevetinfs : Updates an existing planprevetinf.
     *
     * @param planprevetinfDTO the planprevetinfDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planprevetinfDTO,
     * or with status 400 (Bad Request) if the planprevetinfDTO is not valid,
     * or with status 500 (Internal Server Error) if the planprevetinfDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/planprevetinfs")
    public ResponseEntity<PlanprevetinfDTO> updatePlanprevetinf(@Valid @RequestBody PlanprevetinfDTO planprevetinfDTO) throws URISyntaxException {
        log.debug("REST request to update Planprevetinf : {}", planprevetinfDTO);
        if (planprevetinfDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanprevetinfDTO result = planprevetinfService.save(planprevetinfDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planprevetinfDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /planprevetinfs : get all the planprevetinfs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of planprevetinfs in body
     */
    @GetMapping("/planprevetinfs")
    public ResponseEntity<List<PlanprevetinfDTO>> getAllPlanprevetinfs(Pageable pageable) {
        log.debug("REST request to get a page of Planprevetinfs");
        Page<PlanprevetinfDTO> page = planprevetinfService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/planprevetinfs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /planprevetinfs/:id : get the "id" planprevetinf.
     *
     * @param id the id of the planprevetinfDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planprevetinfDTO, or with status 404 (Not Found)
     */
    @GetMapping("/planprevetinfs/{id}")
    public ResponseEntity<PlanprevetinfDTO> getPlanprevetinf(@PathVariable Long id) {
        log.debug("REST request to get Planprevetinf : {}", id);
        Optional<PlanprevetinfDTO> planprevetinfDTO = planprevetinfService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planprevetinfDTO);
    }

    /**
     * DELETE  /planprevetinfs/:id : delete the "id" planprevetinf.
     *
     * @param id the id of the planprevetinfDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/planprevetinfs/{id}")
    public ResponseEntity<Void> deletePlanprevetinf(@PathVariable Long id) {
        log.debug("REST request to delete Planprevetinf : {}", id);
        planprevetinfService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
