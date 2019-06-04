package com.gmao.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Servicee.
 */
@Entity
@Table(name = "servicee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Servicee implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_service", nullable = false)
    private String nomService;

    @OneToMany(mappedBy = "servicee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Equipement> equipems = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomService() {
        return nomService;
    }

    public Servicee nomService(String nomService) {
        this.nomService = nomService;
        return this;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public Set<Equipement> getEquipems() {
        return equipems;
    }

    public Servicee equipems(Set<Equipement> equipements) {
        this.equipems = equipements;
        return this;
    }

    public Servicee addEquipems(Equipement equipement) {
        this.equipems.add(equipement);
        equipement.setServicee(this);
        return this;
    }

    public Servicee removeEquipems(Equipement equipement) {
        this.equipems.remove(equipement);
        equipement.setServicee(null);
        return this;
    }

    public void setEquipems(Set<Equipement> equipements) {
        this.equipems = equipements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Servicee servicee = (Servicee) o;
        if (servicee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), servicee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Servicee{" +
            "id=" + getId() +
            ", nomService='" + getNomService() + "'" +
            "}";
    }
}
