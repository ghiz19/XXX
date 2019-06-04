package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.UtilisateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Utilisateur and its DTO UtilisateurDTO.
 */
@Mapper(componentModel = "spring", uses = {EquipeMapper.class})
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {

    @Mapping(source = "equipe", target = "equipeDTO")
    UtilisateurDTO toDto(Utilisateur utilisateur);

    @Mapping(source = "equipeDTO", target = "equipe")
    @Mapping(target = "usertaches", ignore = true)
    @Mapping(target = "userdemandes", ignore = true)
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);

    default Utilisateur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(id);
        return utilisateur;
    }
}
