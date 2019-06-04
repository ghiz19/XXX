package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.ServiceeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Servicee and its DTO ServiceeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServiceeMapper extends EntityMapper<ServiceeDTO, Servicee> {


    @Mapping(target = "equipems", ignore = true)
    Servicee toEntity(ServiceeDTO serviceeDTO);

    default Servicee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Servicee servicee = new Servicee();
        servicee.setId(id);
        return servicee;
    }
}
