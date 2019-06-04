package com.gmao.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Contrat.
 */
@Entity
@Table(name = "contrat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nomcontrat", nullable = false)
    private String nomcontrat;

    @NotNull
    @Column(name = "coutcontrat", nullable = false)
    private Integer coutcontrat;

    @Column(name = "numerocontrat")
    private Integer numerocontrat;

    @NotNull
    @Column(name = "datedebutcontrat", nullable = false)
    private LocalDate datedebutcontrat;

    @NotNull
    @Column(name = "datefincontrat", nullable = false)
    private LocalDate datefincontrat;

    @Column(name = "daterealisation")
    private LocalDate daterealisation;

    @ManyToOne
    @JsonIgnoreProperties("contrats")
    private Equipement equipement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomcontrat() {
        return nomcontrat;
    }

    public Contrat nomcontrat(String nomcontrat) {
        this.nomcontrat = nomcontrat;
        return this;
    }

    public void setNomcontrat(String nomcontrat) {
        this.nomcontrat = nomcontrat;
    }

    public Integer getCoutcontrat() {
        return coutcontrat;
    }

    public Contrat coutcontrat(Integer coutcontrat) {
        this.coutcontrat = coutcontrat;
        return this;
    }

    public void setCoutcontrat(Integer coutcontrat) {
        this.coutcontrat = coutcontrat;
    }

    public Integer getNumerocontrat() {
        return numerocontrat;
    }

    public Contrat numerocontrat(Integer numerocontrat) {
        this.numerocontrat = numerocontrat;
        return this;
    }

    public void setNumerocontrat(Integer numerocontrat) {
        this.numerocontrat = numerocontrat;
    }

    public LocalDate getDatedebutcontrat() {
        return datedebutcontrat;
    }

    public Contrat datedebutcontrat(LocalDate datedebutcontrat) {
        this.datedebutcontrat = datedebutcontrat;
        return this;
    }

    public void setDatedebutcontrat(LocalDate datedebutcontrat) {
        this.datedebutcontrat = datedebutcontrat;
    }

    public LocalDate getDatefincontrat() {
        return datefincontrat;
    }

    public Contrat datefincontrat(LocalDate datefincontrat) {
        this.datefincontrat = datefincontrat;
        return this;
    }

    public void setDatefincontrat(LocalDate datefincontrat) {
        this.datefincontrat = datefincontrat;
    }

    public LocalDate getDaterealisation() {
        return daterealisation;
    }

    public Contrat daterealisation(LocalDate daterealisation) {
        this.daterealisation = daterealisation;
        return this;
    }

    public void setDaterealisation(LocalDate daterealisation) {
        this.daterealisation = daterealisation;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public Contrat equipement(Equipement equipement) {
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
        Contrat contrat = (Contrat) o;
        if (contrat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contrat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contrat{" +
            "id=" + getId() +
            ", nomcontrat='" + getNomcontrat() + "'" +
            ", coutcontrat=" + getCoutcontrat() +
            ", numerocontrat=" + getNumerocontrat() +
            ", datedebutcontrat='" + getDatedebutcontrat() + "'" +
            ", datefincontrat='" + getDatefincontrat() + "'" +
            ", daterealisation='" + getDaterealisation() + "'" +
            "}";
    }
}
