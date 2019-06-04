package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.DemandeinterventionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Demandeintervention and its DTO DemandeinterventionDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface DemandeinterventionMapper extends EntityMapper<DemandeinterventionDTO, Demandeintervention> {

    @Mapping(source = "utilisateur", target = "utilisateurDTO")
    DemandeinterventionDTO toDto(Demandeintervention demandeintervention);

    @Mapping(target = "demandeintervens", ignore = true)
    @Mapping(source = "utilisateurDTO", target = "utilisateur")
    Demandeintervention toEntity(DemandeinterventionDTO demandeinterventionDTO);

    default Demandeintervention fromId(Long id) {
        if (id == null) {
            return null;
        }
        Demandeintervention demandeintervention = new Demandeintervention();
        demandeintervention.setId(id);
        return demandeintervention;
    }
}
