package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.EquipeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Equipe and its DTO EquipeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EquipeMapper extends EntityMapper<EquipeDTO, Equipe> {


    @Mapping(target = "equipeinterventions", ignore = true)
    @Mapping(target = "equipeusers", ignore = true)
    Equipe toEntity(EquipeDTO equipeDTO);

    default Equipe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Equipe equipe = new Equipe();
        equipe.setId(id);
        return equipe;
    }
}
