package com.gmao.service;

import com.gmao.service.dto.TypeequipementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Typeequipement.
 */
public interface TypeequipementService {

    /**
     * Save a typeequipement.
     *
     * @param typeequipementDTO the entity to save
     * @return the persisted entity
     */
    TypeequipementDTO save(TypeequipementDTO typeequipementDTO);

    /**
     * Get all the typeequipements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TypeequipementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" typeequipement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeequipementDTO> findOne(Long id);

    /**
     * Delete the "id" typeequipement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
