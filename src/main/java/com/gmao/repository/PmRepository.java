package com.gmao.repository;

import com.gmao.domain.Pm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Pm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PmRepository extends JpaRepository<Pm, Long> {

}
