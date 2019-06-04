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
 * A Equipement.
 */
@Entity
@Table(name = "equipement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Equipement implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_equipement", nullable = false)
    private String nomEquipement;

    @NotNull
    @Column(name = "marque", nullable = false)
    private String marque;

    @Column(name = "description")
    private String description;

    @Column(name = "date_achat")
    private LocalDate dateAchat;

    @Column(name = "datedernieremaintenance")
    private LocalDate datedernieremaintenance;

    @NotNull
    @Column(name = "dateexperation", nullable = false)
    private LocalDate dateexperation;

    @NotNull
    @Column(name = "fabricant", nullable = false)
    private String fabricant;

    @NotNull
    @Column(name = "prix_achat", nullable = false)
    private Double prixAchat;

    @Column(name = "numero_serie")
    private Integer numeroSerie;

    @Column(name = "numero_commande")
    private Integer numeroCommande;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @OneToMany(mappedBy = "equipement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pm> pms = new HashSet<>();
    @OneToMany(mappedBy = "equipement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contrat> contrats = new HashSet<>();
    @OneToMany(mappedBy = "equipement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Planprevetinf> equipavoirplans = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("equipements")
    private Equipement equipementParent;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "equipement_equipement_fils",
               joinColumns = @JoinColumn(name = "equipement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "equipement_fils_id", referencedColumnName = "id"))
    private Set<Equipement> equipementFils = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "equipement_equipsdemandes",
               joinColumns = @JoinColumn(name = "equipement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "equipsdemandes_id", referencedColumnName = "id"))
    private Set<Demandeintervention> equipsdemandes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("equipments")
    private Typeequipement typeequipement;

    @ManyToOne
    @JsonIgnoreProperties("localisationeEquipements")
    private Localisations localisations;

    @ManyToOne
    @JsonIgnoreProperties("equipems")
    private Servicee servicee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public Equipement nomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
        return this;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }

    public String getMarque() {
        return marque;
    }

    public Equipement marque(String marque) {
        this.marque = marque;
        return this;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public Equipement description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public Equipement dateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
        return this;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public LocalDate getDatedernieremaintenance() {
        return datedernieremaintenance;
    }

    public Equipement datedernieremaintenance(LocalDate datedernieremaintenance) {
        this.datedernieremaintenance = datedernieremaintenance;
        return this;
    }

    public void setDatedernieremaintenance(LocalDate datedernieremaintenance) {
        this.datedernieremaintenance = datedernieremaintenance;
    }

    public LocalDate getDateexperation() {
        return dateexperation;
    }

    public Equipement dateexperation(LocalDate dateexperation) {
        this.dateexperation = dateexperation;
        return this;
    }

    public void setDateexperation(LocalDate dateexperation) {
        this.dateexperation = dateexperation;
    }

    public String getFabricant() {
        return fabricant;
    }

    public Equipement fabricant(String fabricant) {
        this.fabricant = fabricant;
        return this;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public Double getPrixAchat() {
        return prixAchat;
    }

    public Equipement prixAchat(Double prixAchat) {
        this.prixAchat = prixAchat;
        return this;
    }

    public void setPrixAchat(Double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Integer getNumeroSerie() {
        return numeroSerie;
    }

    public Equipement numeroSerie(Integer numeroSerie) {
        this.numeroSerie = numeroSerie;
        return this;
    }

    public void setNumeroSerie(Integer numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Integer getNumeroCommande() {
        return numeroCommande;
    }

    public Equipement numeroCommande(Integer numeroCommande) {
        this.numeroCommande = numeroCommande;
        return this;
    }

    public void setNumeroCommande(Integer numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public byte[] getImage() {
        return image;
    }

    public Equipement image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Equipement imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Set<Pm> getPms() {
        return pms;
    }

    public Equipement pms(Set<Pm> pms) {
        this.pms = pms;
        return this;
    }

    public Equipement addPms(Pm pm) {
        this.pms.add(pm);
        pm.setEquipement(this);
        return this;
    }

    public Equipement removePms(Pm pm) {
        this.pms.remove(pm);
        pm.setEquipement(null);
        return this;
    }

    public void setPms(Set<Pm> pms) {
        this.pms = pms;
    }

    public Set<Contrat> getContrats() {
        return contrats;
    }

    public Equipement contrats(Set<Contrat> contrats) {
        this.contrats = contrats;
        return this;
    }

    public Equipement addContrats(Contrat contrat) {
        this.contrats.add(contrat);
        contrat.setEquipement(this);
        return this;
    }

    public Equipement removeContrats(Contrat contrat) {
        this.contrats.remove(contrat);
        contrat.setEquipement(null);
        return this;
    }

    public void setContrats(Set<Contrat> contrats) {
        this.contrats = contrats;
    }

    public Set<Planprevetinf> getEquipavoirplans() {
        return equipavoirplans;
    }

    public Equipement equipavoirplans(Set<Planprevetinf> planprevetinfs) {
        this.equipavoirplans = planprevetinfs;
        return this;
    }

    public Equipement addEquipavoirplan(Planprevetinf planprevetinf) {
        this.equipavoirplans.add(planprevetinf);
        planprevetinf.setEquipement(this);
        return this;
    }

    public Equipement removeEquipavoirplan(Planprevetinf planprevetinf) {
        this.equipavoirplans.remove(planprevetinf);
        planprevetinf.setEquipement(null);
        return this;
    }

    public void setEquipavoirplans(Set<Planprevetinf> planprevetinfs) {
        this.equipavoirplans = planprevetinfs;
    }

    public Equipement getEquipementParent() {
        return equipementParent;
    }

    public Equipement equipementParent(Equipement equipement) {
        this.equipementParent = equipement;
        return this;
    }

    public void setEquipementParent(Equipement equipement) {
        this.equipementParent = equipement;
    }

    public Set<Equipement> getEquipementFils() {
        return equipementFils;
    }

    public Equipement equipementFils(Set<Equipement> equipements) {
        this.equipementFils = equipements;
        return this;
    }

    public Equipement addEquipementFils(Equipement equipement) {
        this.equipementFils.add(equipement);
        equipement.getEquipementFils().add(this);
        return this;
    }

    public Equipement removeEquipementFils(Equipement equipement) {
        this.equipementFils.remove(equipement);
        equipement.getEquipementFils().remove(this);
        return this;
    }

    public void setEquipementFils(Set<Equipement> equipements) {
        this.equipementFils = equipements;
    }

    public Set<Demandeintervention> getEquipsdemandes() {
        return equipsdemandes;
    }

    public Equipement equipsdemandes(Set<Demandeintervention> demandeinterventions) {
        this.equipsdemandes = demandeinterventions;
        return this;
    }

    public Equipement addEquipsdemandes(Demandeintervention demandeintervention) {
        this.equipsdemandes.add(demandeintervention);
     //  demandeintervention.getEquipements().add(this);
        return this;
    }

    public Equipement removeEquipsdemandes(Demandeintervention demandeintervention) {
        this.equipsdemandes.remove(demandeintervention);
      //  demandeintervention.getEquipements().remove(this);
        return this;
    }

    public void setEquipsdemandes(Set<Demandeintervention> demandeinterventions) {
        this.equipsdemandes = demandeinterventions;
    }

    public Typeequipement getTypeequipement() {
        return typeequipement;
    }

    public Equipement typeequipement(Typeequipement typeequipement) {
        this.typeequipement = typeequipement;
        return this;
    }

    public void setTypeequipement(Typeequipement typeequipement) {
        this.typeequipement = typeequipement;
    }

    public Localisations getLocalisations() {
        return localisations;
    }

    public Equipement localisations(Localisations localisations) {
        this.localisations = localisations;
        return this;
    }

    public void setLocalisations(Localisations localisations) {
        this.localisations = localisations;
    }

    public Servicee getServicee() {
        return servicee;
    }

    public Equipement servicee(Servicee servicee) {
        this.servicee = servicee;
        return this;
    }

    public void setServicee(Servicee servicee) {
        this.servicee = servicee;
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
        Equipement equipement = (Equipement) o;
        if (equipement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Equipement{" +
            "id=" + getId() +
            ", nomEquipement='" + getNomEquipement() + "'" +
            ", marque='" + getMarque() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateAchat='" + getDateAchat() + "'" +
            ", datedernieremaintenance='" + getDatedernieremaintenance() + "'" +
            ", dateexperation='" + getDateexperation() + "'" +
            ", fabricant='" + getFabricant() + "'" +
            ", prixAchat=" + getPrixAchat() +
            ", numeroSerie=" + getNumeroSerie() +
            ", numeroCommande=" + getNumeroCommande() +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
