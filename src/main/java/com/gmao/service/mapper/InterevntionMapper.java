package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.InterevntionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Interevntion and its DTO InterevntionDTO.
 */
@Mapper(componentModel = "spring", uses = {DemandeinterventionMapper.class, PlanprevetinfMapper.class, EtatMapper.class, EquipeMapper.class})
public interface InterevntionMapper extends EntityMapper<InterevntionDTO, Interevntion> {

    @Mapping(source = "demandeintervention", target = "demandeinterventionDTO")
    @Mapping(source = "planprevetinf", target = "planprevetinfDTO")
    @Mapping(source = "etat", target = "etatDTO")
    @Mapping(source = "equipe", target = "equipeDTO")
    InterevntionDTO toDto(Interevntion interevntion);

    @Mapping(source = "demandeinterventionDTO", target = "demandeintervention")
    @Mapping(source = "planprevetinfDTO", target = "planprevetinf")
    @Mapping(target = "intervenhistoriques", ignore = true)
    @Mapping(source = "etatDTO", target = "etat")
    @Mapping(source = "equipeDTO", target = "equipe")
    Interevntion toEntity(InterevntionDTO interevntionDTO);

    default Interevntion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Interevntion interevntion = new Interevntion();
        interevntion.setId(id);
        return interevntion;
    }
}
