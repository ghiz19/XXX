package com.gmao.repository;

import com.gmao.domain.Interevntion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Interevntion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InterevntionRepository extends JpaRepository<Interevntion, Long> {

}
