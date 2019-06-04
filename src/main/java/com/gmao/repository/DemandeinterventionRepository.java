package com.gmao.repository;

import com.gmao.domain.Demandeintervention;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Demandeintervention entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeinterventionRepository extends JpaRepository<Demandeintervention, Long> {

}
