package com.gmao.repository;

import com.gmao.domain.Contrat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Contrat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

}
