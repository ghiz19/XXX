package com.gmao.repository;

import com.gmao.domain.Planprevetinf;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Planprevetinf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanprevetinfRepository extends JpaRepository<Planprevetinf, Long> {

}
