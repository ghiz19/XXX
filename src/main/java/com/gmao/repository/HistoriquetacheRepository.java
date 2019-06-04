package com.gmao.repository;

import com.gmao.domain.Historiquetache;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Historiquetache entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoriquetacheRepository extends JpaRepository<Historiquetache, Long> {

}
