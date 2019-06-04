package com.gmao.service;

import com.gmao.service.dto.EtatDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Etat.
 */
public interface EtatService {

    /**
     * Save a etat.
     *
     * @param etatDTO the entity to save
     * @return the persisted entity
     */
    EtatDTO save(EtatDTO etatDTO);

    /**
     * Get all the etats.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EtatDTO> findAll(Pageable pageable);


    /**
     * Get the "id" etat.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EtatDTO> findOne(Long id);

    /**
     * Delete the "id" etat.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
