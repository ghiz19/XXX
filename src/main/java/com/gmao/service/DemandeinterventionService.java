package com.gmao.service;

import com.gmao.service.dto.DemandeinterventionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Demandeintervention.
 */
public interface DemandeinterventionService {

    /**
     * Save a demandeintervention.
     *
     * @param demandeinterventionDTO the entity to save
     * @return the persisted entity
     */
    DemandeinterventionDTO save(DemandeinterventionDTO demandeinterventionDTO);

    /**
     * Get all the demandeinterventions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DemandeinterventionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" demandeintervention.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DemandeinterventionDTO> findOne(Long id);

    /**
     * Delete the "id" demandeintervention.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
