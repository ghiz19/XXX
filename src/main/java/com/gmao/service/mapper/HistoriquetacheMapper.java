package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.HistoriquetacheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Historiquetache and its DTO HistoriquetacheDTO.
 */
@Mapper(componentModel = "spring", uses = {InterevntionMapper.class, UtilisateurMapper.class})
public interface HistoriquetacheMapper extends EntityMapper<HistoriquetacheDTO, Historiquetache> {

    @Mapping(source = "interevntion", target = "interevntionDTO")
    @Mapping(source = "utilisateur", target = "utilisateurDTO")
    HistoriquetacheDTO toDto(Historiquetache historiquetache);

    @Mapping(source = "interevntionDTO", target = "interevntion")
    @Mapping(source = "utilisateurDTO", target = "utilisateur")
    Historiquetache toEntity(HistoriquetacheDTO historiquetacheDTO);

    default Historiquetache fromId(Long id) {
        if (id == null) {
            return null;
        }
        Historiquetache historiquetache = new Historiquetache();
        historiquetache.setId(id);
        return historiquetache;
    }
}
