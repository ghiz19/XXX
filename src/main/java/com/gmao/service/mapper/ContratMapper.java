package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.ContratDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contrat and its DTO ContratDTO.
 */
@Mapper(componentModel = "spring", uses = {EquipementMapper.class})
public interface ContratMapper extends EntityMapper<ContratDTO, Contrat> {

    @Mapping(source = "equipement", target = "equipementDTO")
    ContratDTO toDto(Contrat contrat);

    @Mapping(source = "equipementDTO", target = "equipement")
    Contrat toEntity(ContratDTO contratDTO);

    default Contrat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contrat contrat = new Contrat();
        contrat.setId(id);
        return contrat;
    }
}
