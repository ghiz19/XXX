package com.gmao.repository;

import com.gmao.domain.Typeequipement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Typeequipement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeequipementRepository extends JpaRepository<Typeequipement, Long> {

}
