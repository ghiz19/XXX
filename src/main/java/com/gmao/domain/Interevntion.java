package com.gmao.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Interevntion.
 */
@Entity
@Table(name = "interevntion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Interevntion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "datedebutintervention", nullable = false)
    private ZonedDateTime datedebutintervention;

    @NotNull
    @Column(name = "datefinintervention", nullable = false)
    private ZonedDateTime datefinintervention;

    @Column(name = "duree")
    private String duree;

    @NotNull
    @Column(name = "coutmaindeuvre", nullable = false)
    private String coutmaindeuvre;

    @NotNull
    @Column(name = "coutinterevntion", nullable = false)
    private String coutinterevntion;

    @NotNull
    @Column(name = "solutions", nullable = false)
    private String solutions;

    @ManyToOne
    @JsonIgnoreProperties("demandeintervens")
    private Demandeintervention demandeintervention;

    @ManyToOne
    @JsonIgnoreProperties("planinterventions")
    private Planprevetinf planprevetinf;

    @OneToMany(mappedBy = "interevntion")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Historiquetache> intervenhistoriques = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("etatintervents")
    private Etat etat;

    @ManyToOne
    @JsonIgnoreProperties("equipeinterventions")
    private Equipe equipe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatedebutintervention() {
        return datedebutintervention;
    }

    public Interevntion datedebutintervention(ZonedDateTime datedebutintervention) {
        this.datedebutintervention = datedebutintervention;
        return this;
    }

    public void setDatedebutintervention(ZonedDateTime datedebutintervention) {
        this.datedebutintervention = datedebutintervention;
    }

    public ZonedDateTime getDatefinintervention() {
        return datefinintervention;
    }

    public Interevntion datefinintervention(ZonedDateTime datefinintervention) {
        this.datefinintervention = datefinintervention;
        return this;
    }

    public void setDatefinintervention(ZonedDateTime datefinintervention) {
        this.datefinintervention = datefinintervention;
    }

    public String getDuree() {
        return duree;
    }

    public Interevntion duree(String duree) {
        this.duree = duree;
        return this;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getCoutmaindeuvre() {
        return coutmaindeuvre;
    }

    public Interevntion coutmaindeuvre(String coutmaindeuvre) {
        this.coutmaindeuvre = coutmaindeuvre;
        return this;
    }

    public void setCoutmaindeuvre(String coutmaindeuvre) {
        this.coutmaindeuvre = coutmaindeuvre;
    }

    public String getCoutinterevntion() {
        return coutinterevntion;
    }

    public Interevntion coutinterevntion(String coutinterevntion) {
        this.coutinterevntion = coutinterevntion;
        return this;
    }

    public void setCoutinterevntion(String coutinterevntion) {
        this.coutinterevntion = coutinterevntion;
    }

    public String getSolutions() {
        return solutions;
    }

    public Interevntion solutions(String solutions) {
        this.solutions = solutions;
        return this;
    }

    public void setSolutions(String solutions) {
        this.solutions = solutions;
    }

    public Demandeintervention getDemandeintervention() {
        return demandeintervention;
    }

    public Interevntion demandeintervention(Demandeintervention demandeintervention) {
        this.demandeintervention = demandeintervention;
        return this;
    }

    public void setDemandeintervention(Demandeintervention demandeintervention) {
        this.demandeintervention = demandeintervention;
    }

    public Planprevetinf getPlanprevetinf() {
        return planprevetinf;
    }

    public Interevntion planprevetinf(Planprevetinf planprevetinf) {
        this.planprevetinf = planprevetinf;
        return this;
    }

    public void setPlanprevetinf(Planprevetinf planprevetinf) {
        this.planprevetinf = planprevetinf;
    }

    public Set<Historiquetache> getIntervenhistoriques() {
        return intervenhistoriques;
    }

    public Interevntion intervenhistoriques(Set<Historiquetache> historiquetaches) {
        this.intervenhistoriques = historiquetaches;
        return this;
    }

    public Interevntion addIntervenhistorique(Historiquetache historiquetache) {
        this.intervenhistoriques.add(historiquetache);
        historiquetache.setInterevntion(this);
        return this;
    }

    public Interevntion removeIntervenhistorique(Historiquetache historiquetache) {
        this.intervenhistoriques.remove(historiquetache);
        historiquetache.setInterevntion(null);
        return this;
    }

    public void setIntervenhistoriques(Set<Historiquetache> historiquetaches) {
        this.intervenhistoriques = historiquetaches;
    }

    public Etat getEtat() {
        return etat;
    }

    public Interevntion etat(Etat etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Interevntion equipe(Equipe equipe) {
        this.equipe = equipe;
        return this;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
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
        Interevntion interevntion = (Interevntion) o;
        if (interevntion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), interevntion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Interevntion{" +
            "id=" + getId() +
            ", datedebutintervention='" + getDatedebutintervention() + "'" +
            ", datefinintervention='" + getDatefinintervention() + "'" +
            ", duree='" + getDuree() + "'" +
            ", coutmaindeuvre='" + getCoutmaindeuvre() + "'" +
            ", coutinterevntion='" + getCoutinterevntion() + "'" +
            ", solutions='" + getSolutions() + "'" +
            "}";
    }
}
