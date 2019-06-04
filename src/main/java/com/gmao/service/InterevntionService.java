package com.gmao.service;

import com.gmao.service.dto.InterevntionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Interevntion.
 */
public interface InterevntionService {

    /**
     * Save a interevntion.
     *
     * @param interevntionDTO the entity to save
     * @return the persisted entity
     */
    InterevntionDTO save(InterevntionDTO interevntionDTO);

    /**
     * Get all the interevntions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<InterevntionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" interevntion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InterevntionDTO> findOne(Long id);

    /**
     * Delete the "id" interevntion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
