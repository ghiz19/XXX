package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.EquipementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Equipement and its DTO EquipementDTO.
 */
@Mapper(componentModel = "spring", uses = {DemandeinterventionMapper.class, TypeequipementMapper.class, LocalisationsMapper.class, ServiceeMapper.class})
public interface EquipementMapper extends EntityMapper<EquipementDTO, Equipement> {

    @Mapping(source = "equipementParent", target = "equipementparentDTO")
    @Mapping(source = "typeequipement", target = "typeequipementDTO")
    @Mapping(source = "localisations", target = "localisationDTO")
    @Mapping(source = "servicee", target = "serviceDTO")
    EquipementDTO toDto(Equipement equipement);

    @Mapping(target = "pms", ignore = true)
    @Mapping(target = "contrats", ignore = true)
    @Mapping(target = "equipavoirplans", ignore = true)
    @Mapping(source = "equipementparentDTO", target = "equipementParent")
    @Mapping(source = "typeequipementDTO", target = "typeequipement")
    @Mapping(source = "localisationDTO", target = "localisations")
    @Mapping(source = "serviceDTO", target = "servicee")
    Equipement toEntity(EquipementDTO equipementDTO);

    default Equipement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Equipement equipement = new Equipement();
        equipement.setId(id);
        return equipement;
    }
}
