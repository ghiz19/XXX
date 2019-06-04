package com.gmao.service.impl;

import com.gmao.service.ServiceeService;
import com.gmao.domain.Servicee;
import com.gmao.repository.ServiceeRepository;
import com.gmao.service.dto.ServiceeDTO;
import com.gmao.service.mapper.ServiceeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Servicee.
 */
@Service
@Transactional
public class ServiceeServiceImpl implements ServiceeService {

    private final Logger log = LoggerFactory.getLogger(ServiceeServiceImpl.class);

    private final ServiceeRepository serviceeRepository;

    private final ServiceeMapper serviceeMapper;

    public ServiceeServiceImpl(ServiceeRepository serviceeRepository, ServiceeMapper serviceeMapper) {
        this.serviceeRepository = serviceeRepository;
        this.serviceeMapper = serviceeMapper;
    }

    /**
     * Save a servicee.
     *
     * @param serviceeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ServiceeDTO save(ServiceeDTO serviceeDTO) {
        log.debug("Request to save Servicee : {}", serviceeDTO);
        Servicee servicee = serviceeMapper.toEntity(serviceeDTO);
        servicee = serviceeRepository.save(servicee);
        return serviceeMapper.toDto(servicee);
    }

    /**
     * Get all the servicees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ServiceeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Servicees");
        return serviceeRepository.findAll(pageable)
            .map(serviceeMapper::toDto);
    }


    /**
     * Get one servicee by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceeDTO> findOne(Long id) {
        log.debug("Request to get Servicee : {}", id);
        return serviceeRepository.findById(id)
            .map(serviceeMapper::toDto);
    }

    /**
     * Delete the servicee by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Servicee : {}", id);
        serviceeRepository.deleteById(id);
    }
}
