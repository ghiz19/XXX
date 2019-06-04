package com.gmao.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Planprevetinf.
 */
@Entity
@Table(name = "planprevetinf")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Planprevetinf implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "descriptionplan", nullable = false)
    private String descriptionplan;

    @ManyToOne
    @JsonIgnoreProperties("equipavoirplans")
    private Equipement equipement;

    @OneToMany(mappedBy = "planprevetinf")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Interevntion> planinterventions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionplan() {
        return descriptionplan;
    }

    public Planprevetinf descriptionplan(String descriptionplan) {
        this.descriptionplan = descriptionplan;
        return this;
    }

    public void setDescriptionplan(String descriptionplan) {
        this.descriptionplan = descriptionplan;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public Planprevetinf equipement(Equipement equipement) {
        this.equipement = equipement;
        return this;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public Set<Interevntion> getPlaninterventions() {
        return planinterventions;
    }

    public Planprevetinf planinterventions(Set<Interevntion> interevntions) {
        this.planinterventions = interevntions;
        return this;
    }

    public Planprevetinf addPlanintervention(Interevntion interevntion) {
        this.planinterventions.add(interevntion);
        interevntion.setPlanprevetinf(this);
        return this;
    }

    public Planprevetinf removePlanintervention(Interevntion interevntion) {
        this.planinterventions.remove(interevntion);
        interevntion.setPlanprevetinf(null);
        return this;
    }

    public void setPlaninterventions(Set<Interevntion> interevntions) {
        this.planinterventions = interevntions;
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
        Planprevetinf planprevetinf = (Planprevetinf) o;
        if (planprevetinf.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planprevetinf.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Planprevetinf{" +
            "id=" + getId() +
            ", descriptionplan='" + getDescriptionplan() + "'" +
            "}";
    }
}
