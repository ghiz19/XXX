package com.gmao.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Pm.
 */
@Entity
@Table(name = "pm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pm implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "unite")
    private String unite;

    @NotNull
    @Column(name = "avoirplanprevetinf", nullable = false)
    private Boolean avoirplanprevetinf;

    @ManyToOne
    @JsonIgnoreProperties("pms")
    private Equipement equipement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Pm libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getUnite() {
        return unite;
    }

    public Pm unite(String unite) {
        this.unite = unite;
        return this;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Boolean isAvoirplanprevetinf() {
        return avoirplanprevetinf;
    }

    public Pm avoirplanprevetinf(Boolean avoirplanprevetinf) {
        this.avoirplanprevetinf = avoirplanprevetinf;
        return this;
    }

    public void setAvoirplanprevetinf(Boolean avoirplanprevetinf) {
        this.avoirplanprevetinf = avoirplanprevetinf;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public Pm equipement(Equipement equipement) {
        this.equipement = equipement;
        return this;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
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
        Pm pm = (Pm) o;
        if (pm.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pm.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pm{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", unite='" + getUnite() + "'" +
            ", avoirplanprevetinf='" + isAvoirplanprevetinf() + "'" +
            "}";
    }
}
