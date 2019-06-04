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
 * A Equipe.
 */
@Entity
@Table(name = "equipe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nomequipe", nullable = false)
    private String nomequipe;

    @OneToMany(mappedBy = "equipe")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Interevntion> equipeinterventions = new HashSet<>();
    @OneToMany(mappedBy = "equipe")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Utilisateur> equipeusers = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomequipe() {
        return nomequipe;
    }

    public Equipe nomequipe(String nomequipe) {
        this.nomequipe = nomequipe;
        return this;
    }

    public void setNomequipe(String nomequipe) {
        this.nomequipe = nomequipe;
    }

    public Set<Interevntion> getEquipeinterventions() {
        return equipeinterventions;
    }

    public Equipe equipeinterventions(Set<Interevntion> interevntions) {
        this.equipeinterventions = interevntions;
        return this;
    }

    public Equipe addEquipeintervention(Interevntion interevntion) {
        this.equipeinterventions.add(interevntion);
        interevntion.setEquipe(this);
        return this;
    }

    public Equipe removeEquipeintervention(Interevntion interevntion) {
        this.equipeinterventions.remove(interevntion);
        interevntion.setEquipe(null);
        return this;
    }

    public void setEquipeinterventions(Set<Interevntion> interevntions) {
        this.equipeinterventions = interevntions;
    }

    public Set<Utilisateur> getEquipeusers() {
        return equipeusers;
    }

    public Equipe equipeusers(Set<Utilisateur> utilisateurs) {
        this.equipeusers = utilisateurs;
        return this;
    }

    public Equipe addEquipeuser(Utilisateur utilisateur) {
        this.equipeusers.add(utilisateur);
        utilisateur.setEquipe(this);
        return this;
    }

    public Equipe removeEquipeuser(Utilisateur utilisateur) {
        this.equipeusers.remove(utilisateur);
        utilisateur.setEquipe(null);
        return this;
    }

    public void setEquipeusers(Set<Utilisateur> utilisateurs) {
        this.equipeusers = utilisateurs;
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
        Equipe equipe = (Equipe) o;
        if (equipe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Equipe{" +
            "id=" + getId() +
            ", nomequipe='" + getNomequipe() + "'" +
            "}";
    }
}
