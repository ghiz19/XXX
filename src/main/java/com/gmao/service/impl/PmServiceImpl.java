package com.gmao.service.impl;

import com.gmao.service.PmService;
import com.gmao.domain.Pm;
import com.gmao.repository.PmRepository;
import com.gmao.service.dto.PmDTO;
import com.gmao.service.mapper.PmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Pm.
 */
@Service
@Transactional
public class PmServiceImpl implements PmService {

    private final Logger log = LoggerFactory.getLogger(PmServiceImpl.class);

    private final PmRepository pmRepository;

    private final PmMapper pmMapper;

    public PmServiceImpl(PmRepository pmRepository, PmMapper pmMapper) {
        this.pmRepository = pmRepository;
        this.pmMapper = pmMapper;
    }

    /**
     * Save a pm.
     *
     * @param pmDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PmDTO save(PmDTO pmDTO) {
        log.debug("Request to save Pm : {}", pmDTO);
        Pm pm = pmMapper.toEntity(pmDTO);
        pm = pmRepository.save(pm);
        return pmMapper.toDto(pm);
    }

    /**
     * Get all the pms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PmDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pms");
        return pmRepository.findAll(pageable)
            .map(pmMapper::toDto);
    }


    /**
     * Get one pm by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PmDTO> findOne(Long id) {
        log.debug("Request to get Pm : {}", id);
        return pmRepository.findById(id)
            .map(pmMapper::toDto);
    }

    /**
     * Delete the pm by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pm : {}", id);
        pmRepository.deleteById(id);
    }
}
