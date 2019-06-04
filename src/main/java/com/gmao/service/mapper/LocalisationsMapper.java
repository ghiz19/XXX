package com.gmao.service.mapper;

import com.gmao.domain.*;
import com.gmao.service.dto.LocalisationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Localisations and its DTO LocalisationsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LocalisationsMapper extends EntityMapper<LocalisationsDTO, Localisations> {


    @Mapping(target = "localisationeEquipements", ignore = true)
    Localisations toEntity(LocalisationsDTO localisationsDTO);

    default Localisations fromId(Long id) {
        if (id == null) {
            return null;
        }
        Localisations localisations = new Localisations();
        localisations.setId(id);
        return localisations;
    }
}
