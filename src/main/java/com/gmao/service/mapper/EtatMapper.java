package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.EtatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Etat and its DTO EtatDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatMapper extends EntityMapper<EtatDTO, Etat> {


    @Mapping(target = "etatintervents", ignore = true)
    Etat toEntity(EtatDTO etatDTO);

    default Etat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Etat etat = new Etat();
        etat.setId(id);
        return etat;
    }
}
