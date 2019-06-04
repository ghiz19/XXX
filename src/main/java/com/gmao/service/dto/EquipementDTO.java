package com.gmao.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Equipement entity.
 */
public class EquipementDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomEquipement;

    @NotNull
    private String marque;

    private String description;

    private LocalDate dateAchat;

    private LocalDate datedernieremaintenance;

    @NotNull
    private LocalDate dateexperation;

    @NotNull
    private String fabricant;

    @NotNull
    private Double prixAchat;

    private Integer numeroSerie;

    private Integer numeroCommande;

    @Lob
    private byte[] image;

    private String imageContentType;

    private EquipementDTO equipementparentDTO;

    private Set<EquipementDTO> equipementFils = new HashSet<>();

    private Set<DemandeinterventionDTO> equipsdemandes = new HashSet<>();

    private TypeequipementDTO typeequipementDTO;

    private LocalisationsDTO localisationDTO;

    private ServiceeDTO serviceDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public LocalDate getDatedernieremaintenance() {
        return datedernieremaintenance;
    }

    public void setDatedernieremaintenance(LocalDate datedernieremaintenance) {
        this.datedernieremaintenance = datedernieremaintenance;
    }

    public LocalDate getDateexperation() {
        return dateexperation;
    }

    public void setDateexperation(LocalDate dateexperation) {
        this.dateexperation = dateexperation;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public Double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Integer getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(Integer numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Integer getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(Integer numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

   

    public Set<EquipementDTO> getEquipementFils() {
        return equipementFils;
    }

    public void setEquipementFils(Set<EquipementDTO> equipements) {
        this.equipementFils = equipements;
    }

    public Set<DemandeinterventionDTO> getEquipsdemandes() {
        return equipsdemandes;
    }

    public void setEquipsdemandes(Set<DemandeinterventionDTO> demandeinterventions) {
        this.equipsdemandes = demandeinterventions;
    }

    public EquipementDTO getEquipementparentDTO() {
        return equipementparentDTO;
    }

    public void setEquipementparentDTO(EquipementDTO equipementparentDTO) {
        this.equipementparentDTO = equipementparentDTO;
    }

    public TypeequipementDTO getTypeequipementDTO() {
        return typeequipementDTO;
    }

    public void setTypeequipementDTO(TypeequipementDTO typeequipementDTO) {
        this.typeequipementDTO = typeequipementDTO;
    }

    public LocalisationsDTO getLocalisationDTO() {
        return localisationDTO;
    }

    public void setLocalisationDTO(LocalisationsDTO localisationDTO) {
        this.localisationDTO = localisationDTO;
    }

    public ServiceeDTO getServiceDTO() {
        return serviceDTO;
    }

    public void setServiceDTO(ServiceeDTO serviceDTO) {
        this.serviceDTO = serviceDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EquipementDTO equipementDTO = (EquipementDTO) o;
        if (equipementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EquipementDTO{" +
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
            ", equipementParent=" + getEquipementparentDTO() +
            ", typeequipement=" + getTypeequipementDTO() +
            ", localisations=" + getLocalisationDTO() +
            ", servicee=" + getServiceDTO() +
            "}";
    }
}
