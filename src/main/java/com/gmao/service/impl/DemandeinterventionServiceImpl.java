package com.gmao.service.impl;

import com.gmao.service.DemandeinterventionService;
import com.gmao.domain.Demandeintervention;
import com.gmao.repository.DemandeinterventionRepository;
import com.gmao.service.dto.DemandeinterventionDTO;
import com.gmao.service.mapper.DemandeinterventionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Demandeintervention.
 */
@Service
@Transactional
public class DemandeinterventionServiceImpl implements DemandeinterventionService {

    private final Logger log = LoggerFactory.getLogger(DemandeinterventionServiceImpl.class);

    private final DemandeinterventionRepository demandeinterventionRepository;

    private final DemandeinterventionMapper demandeinterventionMapper;

    public DemandeinterventionServiceImpl(DemandeinterventionRepository demandeinterventionRepository, DemandeinterventionMapper demandeinterventionMapper) {
        this.demandeinterventionRepository = demandeinterventionRepository;
        this.demandeinterventionMapper = demandeinterventionMapper;
    }

    /**
     * Save a demandeintervention.
     *
     * @param demandeinterventionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DemandeinterventionDTO save(DemandeinterventionDTO demandeinterventionDTO) {
        log.debug("Request to save Demandeintervention : {}", demandeinterventionDTO);
        Demandeintervention demandeintervention = demandeinterventionMapper.toEntity(demandeinterventionDTO);
        demandeintervention = demandeinterventionRepository.save(demandeintervention);
        return demandeinterventionMapper.toDto(demandeintervention);
    }

    /**
     * Get all the demandeinterventions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DemandeinterventionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Demandeinterventions");
        return demandeinterventionRepository.findAll(pageable)
            .map(demandeinterventionMapper::toDto);
    }


    /**
     * Get one demandeintervention by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DemandeinterventionDTO> findOne(Long id) {
        log.debug("Request to get Demandeintervention : {}", id);
        return demandeinterventionRepository.findById(id)
            .map(demandeinterventionMapper::toDto);
    }

    /**
     * Delete the demandeintervention by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Demandeintervention : {}", id);
        demandeinterventionRepository.deleteById(id);
    }
}
