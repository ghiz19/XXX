package com.gmao.repository;

import com.gmao.domain.Servicee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Servicee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceeRepository extends JpaRepository<Servicee, Long> {

}
