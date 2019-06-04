package com.gmao.service.impl;

import com.gmao.service.LocalisationsService;
import com.gmao.domain.Localisations;
import com.gmao.repository.LocalisationsRepository;
import com.gmao.service.dto.LocalisationsDTO;
import com.gmao.service.mapper.LocalisationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Localisations.
 */
@Service
@Transactional
public class LocalisationsServiceImpl implements LocalisationsService {

    private final Logger log = LoggerFactory.getLogger(LocalisationsServiceImpl.class);

    private final LocalisationsRepository localisationsRepository;

    private final LocalisationsMapper localisationsMapper;

    public LocalisationsServiceImpl(LocalisationsRepository localisationsRepository, LocalisationsMapper localisationsMapper) {
        this.localisationsRepository = localisationsRepository;
        this.localisationsMapper = localisationsMapper;
    }

    /**
     * Save a localisations.
     *
     * @param localisationsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LocalisationsDTO save(LocalisationsDTO localisationsDTO) {
        log.debug("Request to save Localisations : {}", localisationsDTO);
        Localisations localisations = localisationsMapper.toEntity(localisationsDTO);
        localisations = localisationsRepository.save(localisations);
        return localisationsMapper.toDto(localisations);
    }

    /**
     * Get all the localisations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LocalisationsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Localisations");
        return localisationsRepository.findAll(pageable)
            .map(localisationsMapper::toDto);
    }


    /**
     * Get one localisations by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LocalisationsDTO> findOne(Long id) {
        log.debug("Request to get Localisations : {}", id);
        return localisationsRepository.findById(id)
            .map(localisationsMapper::toDto);
    }

    /**
     * Delete the localisations by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Localisations : {}", id);
        localisationsRepository.deleteById(id);
    }
}
