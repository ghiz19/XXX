package com.gmao.service;

import com.gmao.service.dto.ServiceeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Servicee.
 */
public interface ServiceeService {

    /**
     * Save a servicee.
     *
     * @param serviceeDTO the entity to save
     * @return the persisted entity
     */
    ServiceeDTO save(ServiceeDTO serviceeDTO);

    /**
     * Get all the servicees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ServiceeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" servicee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ServiceeDTO> findOne(Long id);

    /**
     * Delete the "id" servicee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
