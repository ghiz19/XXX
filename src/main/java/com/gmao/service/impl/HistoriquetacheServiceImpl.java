package com.gmao.service.impl;

import com.gmao.service.HistoriquetacheService;
import com.gmao.domain.Historiquetache;
import com.gmao.repository.HistoriquetacheRepository;
import com.gmao.service.dto.HistoriquetacheDTO;
import com.gmao.service.mapper.HistoriquetacheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Historiquetache.
 */
@Service
@Transactional
public class HistoriquetacheServiceImpl implements HistoriquetacheService {

    private final Logger log = LoggerFactory.getLogger(HistoriquetacheServiceImpl.class);

    private final HistoriquetacheRepository historiquetacheRepository;

    private final HistoriquetacheMapper historiquetacheMapper;

    public HistoriquetacheServiceImpl(HistoriquetacheRepository historiquetacheRepository, HistoriquetacheMapper historiquetacheMapper) {
        this.historiquetacheRepository = historiquetacheRepository;
        this.historiquetacheMapper = historiquetacheMapper;
    }

    /**
     * Save a historiquetache.
     *
     * @param historiquetacheDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HistoriquetacheDTO save(HistoriquetacheDTO historiquetacheDTO) {
        log.debug("Request to save Historiquetache : {}", historiquetacheDTO);
        Historiquetache historiquetache = historiquetacheMapper.toEntity(historiquetacheDTO);
        historiquetache = historiquetacheRepository.save(historiquetache);
        return historiquetacheMapper.toDto(historiquetache);
    }

    /**
     * Get all the historiquetaches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HistoriquetacheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Historiquetaches");
        return historiquetacheRepository.findAll(pageable)
            .map(historiquetacheMapper::toDto);
    }


    /**
     * Get one historiquetache by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HistoriquetacheDTO> findOne(Long id) {
        log.debug("Request to get Historiquetache : {}", id);
        return historiquetacheRepository.findById(id)
            .map(historiquetacheMapper::toDto);
    }

    /**
     * Delete the historiquetache by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Historiquetache : {}", id);
        historiquetacheRepository.deleteById(id);
    }
}
