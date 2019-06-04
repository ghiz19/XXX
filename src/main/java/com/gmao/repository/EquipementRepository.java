package com.gmao.repository;

import com.gmao.domain.Equipement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Equipement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {

    @Query(value = "select distinct equipement from Equipement equipement left join fetch equipement.equipementFils left join fetch equipement.equipsdemandes",
        countQuery = "select count(distinct equipement) from Equipement equipement")
    Page<Equipement> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct equipement from Equipement equipement left join fetch equipement.equipementFils left join fetch equipement.equipsdemandes")
    List<Equipement> findAllWithEagerRelationships();

    @Query("select equipement from Equipement equipement left join fetch equipement.equipementFils left join fetch equipement.equipsdemandes where equipement.id =:id")
    Optional<Equipement> findOneWithEagerRelationships(@Param("id") Long id);

}
