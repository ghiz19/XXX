package com.gmao.service.impl;

import com.gmao.service.PlanprevetinfService;
import com.gmao.domain.Planprevetinf;
import com.gmao.repository.PlanprevetinfRepository;
import com.gmao.service.dto.PlanprevetinfDTO;
import com.gmao.service.mapper.PlanprevetinfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Planprevetinf.
 */
@Service
@Transactional
public class PlanprevetinfServiceImpl implements PlanprevetinfService {

    private final Logger log = LoggerFactory.getLogger(PlanprevetinfServiceImpl.class);

    private final PlanprevetinfRepository planprevetinfRepository;

    private final PlanprevetinfMapper planprevetinfMapper;

    public PlanprevetinfServiceImpl(PlanprevetinfRepository planprevetinfRepository, PlanprevetinfMapper planprevetinfMapper) {
        this.planprevetinfRepository = planprevetinfRepository;
        this.planprevetinfMapper = planprevetinfMapper;
    }

    /**
     * Save a planprevetinf.
     *
     * @param planprevetinfDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanprevetinfDTO save(PlanprevetinfDTO planprevetinfDTO) {
        log.debug("Request to save Planprevetinf : {}", planprevetinfDTO);
        Planprevetinf planprevetinf = planprevetinfMapper.toEntity(planprevetinfDTO);
        planprevetinf = planprevetinfRepository.save(planprevetinf);
        return planprevetinfMapper.toDto(planprevetinf);
    }

    /**
     * Get all the planprevetinfs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanprevetinfDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Planprevetinfs");
        return planprevetinfRepository.findAll(pageable)
            .map(planprevetinfMapper::toDto);
    }


    /**
     * Get one planprevetinf by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanprevetinfDTO> findOne(Long id) {
        log.debug("Request to get Planprevetinf : {}", id);
        return planprevetinfRepository.findById(id)
            .map(planprevetinfMapper::toDto);
    }

    /**
     * Delete the planprevetinf by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Planprevetinf : {}", id);
        planprevetinfRepository.deleteById(id);
    }
}
