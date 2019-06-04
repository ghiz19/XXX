package com.gmao.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Demandeintervention entity.
 */
public class DemandeinterventionDTO implements Serializable {

    private Long id;

    @NotNull
    private String description;

    private String degreeurgence;

    private LocalDate datedemande;

    private String priorite;

    private String dateprevu;

    private String datereel;


    private UtilisateurDTO utilisateurDTO;

    public void setUtilisateurDTO(UtilisateurDTO utilisateurDTO) {
        this.utilisateurDTO = utilisateurDTO;
    }
    public UtilisateurDTO getUtilisateurDTO() {
        return utilisateurDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDegreeurgence() {
        return degreeurgence;
    }

    public void setDegreeurgence(String degreeurgence) {
        this.degreeurgence = degreeurgence;
    }

    public LocalDate getDatedemande() {
        return datedemande;
    }

    public void setDatedemande(LocalDate datedemande) {
        this.datedemande = datedemande;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getDateprevu() {
        return dateprevu;
    }

    public void setDateprevu(String dateprevu) {
        this.dateprevu = dateprevu;
    }

    public String getDatereel() {
        return datereel;
    }

    public void setDatereel(String datereel) {
        this.datereel = datereel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DemandeinterventionDTO demandeinterventionDTO = (DemandeinterventionDTO) o;
        if (demandeinterventionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demandeinterventionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DemandeinterventionDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", degreeurgence='" + getDegreeurgence() + "'" +
            ", datedemande='" + getDatedemande() + "'" +
            ", priorite='" + getPriorite() + "'" +
            ", dateprevu='" + getDateprevu() + "'" +
            ", datereel='" + getDatereel() + "'" +
            ", utilisateur=" + getUtilisateurDTO() +
            "}";
    }
}
