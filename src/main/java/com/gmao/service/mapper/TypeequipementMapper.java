package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.TypeequipementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Typeequipement and its DTO TypeequipementDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeequipementMapper extends EntityMapper<TypeequipementDTO, Typeequipement> {


    @Mapping(target = "equipments", ignore = true)
    Typeequipement toEntity(TypeequipementDTO typeequipementDTO);

    default Typeequipement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Typeequipement typeequipement = new Typeequipement();
        typeequipement.setId(id);
        return typeequipement;
    }
}
