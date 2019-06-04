package com.gmao.service;

import com.gmao.service.dto.PmDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Pm.
 */
public interface PmService {

    /**
     * Save a pm.
     *
     * @param pmDTO the entity to save
     * @return the persisted entity
     */
    PmDTO save(PmDTO pmDTO);

    /**
     * Get all the pms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PmDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pm.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PmDTO> findOne(Long id);

    /**
     * Delete the "id" pm.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
