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
 * A Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nomresponsable", nullable = false)
    private String nomresponsable;

    @NotNull
    @Column(name = "prenomresponsable", nullable = false)
    private String prenomresponsable;

    @NotNull
    @Column(name = "jhi_role", nullable = false)
    private String role;

    @ManyToOne
    @JsonIgnoreProperties("equipeusers")
    private Equipe equipe;

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Historiquetache> usertaches = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Demandeintervention> userdemandes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomresponsable() {
        return nomresponsable;
    }

    public Utilisateur nomresponsable(String nomresponsable) {
        this.nomresponsable = nomresponsable;
        return this;
    }

    public void setNomresponsable(String nomresponsable) {
        this.nomresponsable = nomresponsable;
    }

    public String getPrenomresponsable() {
        return prenomresponsable;
    }

    public Utilisateur prenomresponsable(String prenomresponsable) {
        this.prenomresponsable = prenomresponsable;
        return this;
    }

    public void setPrenomresponsable(String prenomresponsable) {
        this.prenomresponsable = prenomresponsable;
    }

    public String getRole() {
        return role;
    }

    public Utilisateur role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Utilisateur equipe(Equipe equipe) {
        this.equipe = equipe;
        return this;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Set<Historiquetache> getUsertaches() {
        return usertaches;
    }

    public Utilisateur usertaches(Set<Historiquetache> historiquetaches) {
        this.usertaches = historiquetaches;
        return this;
    }

    public Utilisateur addUsertache(Historiquetache historiquetache) {
        this.usertaches.add(historiquetache);
        historiquetache.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeUsertache(Historiquetache historiquetache) {
        this.usertaches.remove(historiquetache);
        historiquetache.setUtilisateur(null);
        return this;
    }

    public void setUsertaches(Set<Historiquetache> historiquetaches) {
        this.usertaches = historiquetaches;
    }

    public Set<Demandeintervention> getUserdemandes() {
        return userdemandes;
    }

    public Utilisateur userdemandes(Set<Demandeintervention> demandeinterventions) {
        this.userdemandes = demandeinterventions;
        return this;
    }

    public Utilisateur addUserdemande(Demandeintervention demandeintervention) {
        this.userdemandes.add(demandeintervention);
        demandeintervention.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeUserdemande(Demandeintervention demandeintervention) {
        this.userdemandes.remove(demandeintervention);
        demandeintervention.setUtilisateur(null);
        return this;
    }

    public void setUserdemandes(Set<Demandeintervention> demandeinterventions) {
        this.userdemandes = demandeinterventions;
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
        Utilisateur utilisateur = (Utilisateur) o;
        if (utilisateur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisateur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
            "id=" + getId() +
            ", nomresponsable='" + getNomresponsable() + "'" +
            ", prenomresponsable='" + getPrenomresponsable() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
