package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.PmDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pm and its DTO PmDTO.
 */
@Mapper(componentModel = "spring", uses = {EquipementMapper.class})
public interface PmMapper extends EntityMapper<PmDTO, Pm> {

    @Mapping(source = "equipement", target = "equipementDtoo")
    PmDTO toDto(Pm pm);

    @Mapping(source = "equipementDtoo", target = "equipement")
    Pm toEntity(PmDTO pmDTO);

    default Pm fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pm pm = new Pm();
        pm.setId(id);
        return pm;
    }
}
