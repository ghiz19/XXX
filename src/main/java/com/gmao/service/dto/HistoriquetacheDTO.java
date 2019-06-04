package com.gmao.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Historiquetache entity.
 */
public class HistoriquetacheDTO implements Serializable {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    private ZonedDateTime datetimedebut;

    @NotNull
    private ZonedDateTime detetimefin;


    private InterevntionDTO interevntionDTO;

    private UtilisateurDTO utilisateurDTO;

    public void setInterevntionDTO(InterevntionDTO interevntionDTO) {
        this.interevntionDTO = interevntionDTO;
    }
    public InterevntionDTO getInterevntionDTO() {
        return interevntionDTO;
    }
    public UtilisateurDTO  getUtilisateurDTO() {
        return utilisateurDTO;
    }

    public void setUtilisateurDTO(UtilisateurDTO utilisateurDTO) {
        this.utilisateurDTO = utilisateurDTO;
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

    public ZonedDateTime getDatetimedebut() {
        return datetimedebut;
    }

    public void setDatetimedebut(ZonedDateTime datetimedebut) {
        this.datetimedebut = datetimedebut;
    }

    public ZonedDateTime getDetetimefin() {
        return detetimefin;
    }

    public void setDetetimefin(ZonedDateTime detetimefin) {
        this.detetimefin = detetimefin;
    }

   
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HistoriquetacheDTO historiquetacheDTO = (HistoriquetacheDTO) o;
        if (historiquetacheDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), historiquetacheDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistoriquetacheDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", datetimedebut='" + getDatetimedebut() + "'" +
            ", detetimedebut='" + getDetetimefin() + "'" +
            ", interevntion=" + getInterevntionDTO() +
            ", utilisateur=" + getUtilisateurDTO() +
            "}";
    }
}
