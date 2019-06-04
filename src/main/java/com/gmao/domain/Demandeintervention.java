package com.gmao.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Demandeintervention.
 */
@Entity
@Table(name = "demandeintervention")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Demandeintervention implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "degreeurgence")
    private String degreeurgence;

    @Column(name = "datedemande")
    private LocalDate datedemande;

    @Column(name = "priorite")
    private String priorite;

    @Column(name = "dateprevu")
    private String dateprevu;

    @Column(name = "datereel")
    private String datereel;

    @OneToMany(mappedBy = "demandeintervention")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Interevntion> demandeintervens = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("userdemandes")
    private Utilisateur utilisateur;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Demandeintervention description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDegreeurgence() {
        return degreeurgence;
    }

    public Demandeintervention degreeurgence(String degreeurgence) {
        this.degreeurgence = degreeurgence;
        return this;
    }

    public void setDegreeurgence(String degreeurgence) {
        this.degreeurgence = degreeurgence;
    }

    public LocalDate getDatedemande() {
        return datedemande;
    }

    public Demandeintervention datedemande(LocalDate datedemande) {
        this.datedemande = datedemande;
        return this;
    }

    public void setDatedemande(LocalDate datedemande) {
        this.datedemande = datedemande;
    }

    public String getPriorite() {
        return priorite;
    }

    public Demandeintervention priorite(String priorite) {
        this.priorite = priorite;
        return this;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getDateprevu() {
        return dateprevu;
    }

    public Demandeintervention dateprevu(String dateprevu) {
        this.dateprevu = dateprevu;
        return this;
    }

    public void setDateprevu(String dateprevu) {
        this.dateprevu = dateprevu;
    }

    public String getDatereel() {
        return datereel;
    }

    public Demandeintervention datereel(String datereel) {
        this.datereel = datereel;
        return this;
    }

    public void setDatereel(String datereel) {
        this.datereel = datereel;
    }

    public Set<Interevntion> getDemandeintervens() {
        return demandeintervens;
    }

    public Demandeintervention demandeintervens(Set<Interevntion> interevntions) {
        this.demandeintervens = interevntions;
        return this;
    }

    public Demandeintervention addDemandeinterven(Interevntion interevntion) {
        this.demandeintervens.add(interevntion);
        interevntion.setDemandeintervention(this);
        return this;
    }

    public Demandeintervention removeDemandeinterven(Interevntion interevntion) {
        this.demandeintervens.remove(interevntion);
        interevntion.setDemandeintervention(null);
        return this;
    }

    public void setDemandeintervens(Set<Interevntion> interevntions) {
        this.demandeintervens = interevntions;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Demandeintervention utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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
        Demandeintervention demandeintervention = (Demandeintervention) o;
        if (demandeintervention.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demandeintervention.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Demandeintervention{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", degreeurgence='" + getDegreeurgence() + "'" +
            ", datedemande='" + getDatedemande() + "'" +
            ", priorite='" + getPriorite() + "'" +
            ", dateprevu='" + getDateprevu() + "'" +
            ", datereel='" + getDatereel() + "'" +
            "}";
    }
}
