package com.gmao.repository;

import com.gmao.domain.Etat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Etat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatRepository extends JpaRepository<Etat, Long> {

}
