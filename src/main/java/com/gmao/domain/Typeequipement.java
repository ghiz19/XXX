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
 * A Typeequipement.
 */
@Entity
@Table(name = "typeequipement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Typeequipement implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "typeequipem", nullable = false)
    private String typeequipem;

    @OneToMany(mappedBy = "typeequipement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Equipement> equipments = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeequipem() {
        return typeequipem;
    }

    public Typeequipement typeequipem(String typeequipem) {
        this.typeequipem = typeequipem;
        return this;
    }

    public void setTypeequipem(String typeequipem) {
        this.typeequipem = typeequipem;
    }

    public Set<Equipement> getEquipments() {
        return equipments;
    }

    public Typeequipement equipments(Set<Equipement> equipements) {
        this.equipments = equipements;
        return this;
    }

    public Typeequipement addEquipments(Equipement equipement) {
        this.equipments.add(equipement);
        equipement.setTypeequipement(this);
        return this;
    }

    public Typeequipement removeEquipments(Equipement equipement) {
        this.equipments.remove(equipement);
        equipement.setTypeequipement(null);
        return this;
    }

    public void setEquipments(Set<Equipement> equipements) {
        this.equipments = equipements;
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
        Typeequipement typeequipement = (Typeequipement) o;
        if (typeequipement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeequipement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Typeequipement{" +
            "id=" + getId() +
            ", typeequipem='" + getTypeequipem() + "'" +
            "}";
    }
}
