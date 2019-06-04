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
 * A Localisations.
 */
@Entity
@Table(name = "localisations")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Localisations implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nomlocalisation", nullable = false)
    private String nomlocalisation;

    @OneToMany(mappedBy = "localisations")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Equipement> localisationeEquipements = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomlocalisation() {
        return nomlocalisation;
    }

    public Localisations nomlocalisation(String nomlocalisation) {
        this.nomlocalisation = nomlocalisation;
        return this;
    }

    public void setNomlocalisation(String nomlocalisation) {
        this.nomlocalisation = nomlocalisation;
    }

    public Set<Equipement> getLocalisationeEquipements() {
        return localisationeEquipements;
    }

    public Localisations localisationeEquipements(Set<Equipement> equipements) {
        this.localisationeEquipements = equipements;
        return this;
    }

    public Localisations addLocalisationeEquipements(Equipement equipement) {
        this.localisationeEquipements.add(equipement);
        equipement.setLocalisations(this);
        return this;
    }

    public Localisations removeLocalisationeEquipements(Equipement equipement) {
        this.localisationeEquipements.remove(equipement);
        equipement.setLocalisations(null);
        return this;
    }

    public void setLocalisationeEquipements(Set<Equipement> equipements) {
        this.localisationeEquipements = equipements;
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
        Localisations localisations = (Localisations) o;
        if (localisations.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), localisations.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Localisations{" +
            "id=" + getId() +
            ", nomlocalisation='" + getNomlocalisation() + "'" +
            "}";
    }
}
