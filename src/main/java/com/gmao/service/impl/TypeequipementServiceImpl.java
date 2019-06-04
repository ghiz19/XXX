package com.gmao.service.impl;

import com.gmao.service.TypeequipementService;
import com.gmao.domain.Typeequipement;
import com.gmao.repository.TypeequipementRepository;
import com.gmao.service.dto.TypeequipementDTO;
import com.gmao.service.mapper.TypeequipementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Typeequipement.
 */
@Service
@Transactional
public class TypeequipementServiceImpl implements TypeequipementService {

    private final Logger log = LoggerFactory.getLogger(TypeequipementServiceImpl.class);

    private final TypeequipementRepository typeequipementRepository;

    private final TypeequipementMapper typeequipementMapper;

    public TypeequipementServiceImpl(TypeequipementRepository typeequipementRepository, TypeequipementMapper typeequipementMapper) {
        this.typeequipementRepository = typeequipementRepository;
        this.typeequipementMapper = typeequipementMapper;
    }

    /**
     * Save a typeequipement.
     *
     * @param typeequipementDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeequipementDTO save(TypeequipementDTO typeequipementDTO) {
        log.debug("Request to save Typeequipement : {}", typeequipementDTO);
        Typeequipement typeequipement = typeequipementMapper.toEntity(typeequipementDTO);
        typeequipement = typeequipementRepository.save(typeequipement);
        return typeequipementMapper.toDto(typeequipement);
    }

    /**
     * Get all the typeequipements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeequipementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Typeequipements");
        return typeequipementRepository.findAll(pageable)
            .map(typeequipementMapper::toDto);
    }


    /**
     * Get one typeequipement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeequipementDTO> findOne(Long id) {
        log.debug("Request to get Typeequipement : {}", id);
        return typeequipementRepository.findById(id)
            .map(typeequipementMapper::toDto);
    }

    /**
     * Delete the typeequipement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Typeequipement : {}", id);
        typeequipementRepository.deleteById(id);
    }
}
