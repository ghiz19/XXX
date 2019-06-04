package com.gmao.service.impl;

import com.gmao.service.EtatService;
import com.gmao.domain.Etat;
import com.gmao.repository.EtatRepository;
import com.gmao.service.dto.EtatDTO;
import com.gmao.service.mapper.EtatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Etat.
 */
@Service
@Transactional
public class EtatServiceImpl implements EtatService {

    private final Logger log = LoggerFactory.getLogger(EtatServiceImpl.class);

    private final EtatRepository etatRepository;

    private final EtatMapper etatMapper;

    public EtatServiceImpl(EtatRepository etatRepository, EtatMapper etatMapper) {
        this.etatRepository = etatRepository;
        this.etatMapper = etatMapper;
    }

    /**
     * Save a etat.
     *
     * @param etatDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EtatDTO save(EtatDTO etatDTO) {
        log.debug("Request to save Etat : {}", etatDTO);
        Etat etat = etatMapper.toEntity(etatDTO);
        etat = etatRepository.save(etat);
        return etatMapper.toDto(etat);
    }

    /**
     * Get all the etats.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EtatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Etats");
        return etatRepository.findAll(pageable)
            .map(etatMapper::toDto);
    }


    /**
     * Get one etat by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtatDTO> findOne(Long id) {
        log.debug("Request to get Etat : {}", id);
        return etatRepository.findById(id)
            .map(etatMapper::toDto);
    }

    /**
     * Delete the etat by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Etat : {}", id);
        etatRepository.deleteById(id);
    }
}
