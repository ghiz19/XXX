package com.gmao.service;

import com.gmao.service.dto.ContratDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Contrat.
 */
public interface ContratService {

    /**
     * Save a contrat.
     *
     * @param contratDTO the entity to save
     * @return the persisted entity
     */
    ContratDTO save(ContratDTO contratDTO);

    /**
     * Get all the contrats.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ContratDTO> findAll(Pageable pageable);


    /**
     * Get the "id" contrat.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ContratDTO> findOne(Long id);

    /**
     * Delete the "id" contrat.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
