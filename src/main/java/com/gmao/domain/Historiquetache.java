package com.gmao.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Historiquetache.
 */
@Entity
@Table(name = "historiquetache")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Historiquetache implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "datetimedebut", nullable = false)
    private ZonedDateTime datetimedebut;

    @NotNull
    @Column(name = "detetimedebut", nullable = false)
    private ZonedDateTime detetimefin;

    @ManyToOne
    @JsonIgnoreProperties("intervenhistoriques")
    private Interevntion interevntion;

    @ManyToOne
    @JsonIgnoreProperties("usertaches")
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

    public Historiquetache description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getDatetimedebut() {
        return datetimedebut;
    }

    public Historiquetache datetimedebut(ZonedDateTime datetimedebut) {
        this.datetimedebut = datetimedebut;
        return this;
    }

    public void setDatetimedebut(ZonedDateTime datetimedebut) {
        this.datetimedebut = datetimedebut;
    }

    public ZonedDateTime getDetetimefin() {
        return detetimefin;
    }

    public Historiquetache detetimefin(ZonedDateTime detetimefin) {
        this.detetimefin = detetimefin;
        return this;
    }

    public void setDetetimefin(ZonedDateTime detetimefin) {
        this.detetimefin = detetimefin;
    }

    public Interevntion getInterevntion() {
        return interevntion;
    }

    public Historiquetache interevntion(Interevntion interevntion) {
        this.interevntion = interevntion;
        return this;
    }

    public void setInterevntion(Interevntion interevntion) {
        this.interevntion = interevntion;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Historiquetache utilisateur(Utilisateur utilisateur) {
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
        Historiquetache historiquetache = (Historiquetache) o;
        if (historiquetache.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), historiquetache.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Historiquetache{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", datetimedebut='" + getDatetimedebut() + "'" +
            ", detetimedebut='" + getDetetimefin() + "'" +
            "}";
    }
}
