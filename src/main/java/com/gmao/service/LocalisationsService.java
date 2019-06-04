package com.gmao.service;

import com.gmao.service.dto.LocalisationsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Localisations.
 */
public interface LocalisationsService {

    /**
     * Save a localisations.
     *
     * @param localisationsDTO the entity to save
     * @return the persisted entity
     */
    LocalisationsDTO save(LocalisationsDTO localisationsDTO);

    /**
     * Get all the localisations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LocalisationsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" localisations.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LocalisationsDTO> findOne(Long id);

    /**
     * Delete the "id" localisations.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
