package com.gmao.service;

import com.gmao.service.dto.PlanprevetinfDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Planprevetinf.
 */
public interface PlanprevetinfService {

    /**
     * Save a planprevetinf.
     *
     * @param planprevetinfDTO the entity to save
     * @return the persisted entity
     */
    PlanprevetinfDTO save(PlanprevetinfDTO planprevetinfDTO);

    /**
     * Get all the planprevetinfs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanprevetinfDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planprevetinf.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanprevetinfDTO> findOne(Long id);

    /**
     * Delete the "id" planprevetinf.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
