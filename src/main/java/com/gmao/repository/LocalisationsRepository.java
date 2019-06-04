package com.gmao.repository;

import com.gmao.domain.Localisations;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Localisations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocalisationsRepository extends JpaRepository<Localisations, Long> {

}
