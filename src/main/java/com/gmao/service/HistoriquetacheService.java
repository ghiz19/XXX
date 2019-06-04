package com.gmao.service;

import com.gmao.service.dto.HistoriquetacheDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Historiquetache.
 */
public interface HistoriquetacheService {

    /**
     * Save a historiquetache.
     *
     * @param historiquetacheDTO the entity to save
     * @return the persisted entity
     */
    HistoriquetacheDTO save(HistoriquetacheDTO historiquetacheDTO);

    /**
     * Get all the historiquetaches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HistoriquetacheDTO> findAll(Pageable pageable);


    /**
     * Get the "id" historiquetache.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HistoriquetacheDTO> findOne(Long id);

    /**
     * Delete the "id" historiquetache.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
