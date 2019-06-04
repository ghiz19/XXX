package com.gmao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Utilisateur entity.
 */
public class UtilisateurDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomresponsable;

    @NotNull
    private String prenomresponsable;

    @NotNull
    private String role;


    private EquipeDTO equipeDTO;

    public EquipeDTO getEquipeDTO() {
        return equipeDTO;
    }

    public void setEquipeDTO(EquipeDTO equipeDTO) {
        this.equipeDTO = equipeDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomresponsable() {
        return nomresponsable;
    }

    public void setNomresponsable(String nomresponsable) {
        this.nomresponsable = nomresponsable;
    }

    public String getPrenomresponsable() {
        return prenomresponsable;
    }

    public void setPrenomresponsable(String prenomresponsable) {
        this.prenomresponsable = prenomresponsable;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UtilisateurDTO utilisateurDTO = (UtilisateurDTO) o;
        if (utilisateurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisateurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UtilisateurDTO{" +
            "id=" + getId() +
            ", nomresponsable='" + getNomresponsable() + "'" +
            ", prenomresponsable='" + getPrenomresponsable() + "'" +
            ", role='" + getRole() + "'" +
            ", equipe=" + getEquipeDTO() +
            "}";
    }
}
