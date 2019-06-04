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
 * A Etat.
 */
@Entity
@Table(name = "etat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Etat implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nometat", nullable = false)
    private String nometat;

    @OneToMany(mappedBy = "etat")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Interevntion> etatintervents = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNometat() {
        return nometat;
    }

    public Etat nometat(String nometat) {
        this.nometat = nometat;
        return this;
    }

    public void setNometat(String nometat) {
        this.nometat = nometat;
    }

    public Set<Interevntion> getEtatintervents() {
        return etatintervents;
    }

    public Etat etatintervents(Set<Interevntion> interevntions) {
        this.etatintervents = interevntions;
        return this;
    }

    public Etat addEtatintervent(Interevntion interevntion) {
        this.etatintervents.add(interevntion);
        interevntion.setEtat(this);
        return this;
    }

    public Etat removeEtatintervent(Interevntion interevntion) {
        this.etatintervents.remove(interevntion);
        interevntion.setEtat(null);
        return this;
    }

    public void setEtatintervents(Set<Interevntion> interevntions) {
        this.etatintervents = interevntions;
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
        Etat etat = (Etat) o;
        if (etat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Etat{" +
            "id=" + getId() +
            ", nometat='" + getNometat() + "'" +
            "}";
    }
}
