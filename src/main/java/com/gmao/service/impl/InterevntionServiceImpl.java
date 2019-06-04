package com.gmao.service.impl;

import com.gmao.service.InterevntionService;
import com.gmao.domain.Interevntion;
import com.gmao.repository.InterevntionRepository;
import com.gmao.service.dto.InterevntionDTO;
import com.gmao.service.mapper.InterevntionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Interevntion.
 */
@Service
@Transactional
public class InterevntionServiceImpl implements InterevntionService {

    private final Logger log = LoggerFactory.getLogger(InterevntionServiceImpl.class);

    private final InterevntionRepository interevntionRepository;

    private final InterevntionMapper interevntionMapper;

    public InterevntionServiceImpl(InterevntionRepository interevntionRepository, InterevntionMapper interevntionMapper) {
        this.interevntionRepository = interevntionRepository;
        this.interevntionMapper = interevntionMapper;
    }

    /**
     * Save a interevntion.
     *
     * @param interevntionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InterevntionDTO save(InterevntionDTO interevntionDTO) {
        log.debug("Request to save Interevntion : {}", interevntionDTO);
        Interevntion interevntion = interevntionMapper.toEntity(interevntionDTO);
        interevntion = interevntionRepository.save(interevntion);
        return interevntionMapper.toDto(interevntion);
    }

    /**
     * Get all the interevntions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InterevntionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Interevntions");
        return interevntionRepository.findAll(pageable)
            .map(interevntionMapper::toDto);
    }


    /**
     * Get one interevntion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InterevntionDTO> findOne(Long id) {
        log.debug("Request to get Interevntion : {}", id);
        return interevntionRepository.findById(id)
            .map(interevntionMapper::toDto);
    }

    /**
     * Delete the interevntion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Interevntion : {}", id);
        interevntionRepository.deleteById(id);
    }
}
