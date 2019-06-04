package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.PlanprevetinfDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Planprevetinf and its DTO PlanprevetinfDTO.
 */
@Mapper(componentModel = "spring", uses = {EquipementMapper.class})
public interface PlanprevetinfMapper extends EntityMapper<PlanprevetinfDTO, Planprevetinf> {

    @Mapping(source = "equipement", target = "equipementPlan")
    PlanprevetinfDTO toDto(Planprevetinf planprevetinf);

    @Mapping(source = "equipementPlan", target = "equipement")
    @Mapping(target = "planinterventions", ignore = true)
    Planprevetinf toEntity(PlanprevetinfDTO planprevetinfDTO);

    default Planprevetinf fromId(Long id) {
        if (id == null) {
            return null;
        }
        Planprevetinf planprevetinf = new Planprevetinf();
        planprevetinf.setId(id);
        return planprevetinf;
    }
}
