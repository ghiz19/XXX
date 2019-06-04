package com.gmao.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Contrat entity.
 */
public class ContratDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomcontrat;

    @NotNull
    private Integer coutcontrat;

    private Integer numerocontrat;

    @NotNull
    private LocalDate datedebutcontrat;

    @NotNull
    private LocalDate datefincontrat;

    private LocalDate daterealisation;


    private EquipementDTO equipementDTO;

    public EquipementDTO getEquipementDTO() {
        return equipementDTO;
    }

    public void setEquipementDTO(EquipementDTO equipementDTO) {
        this.equipementDTO = equipementDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomcontrat() {
        return nomcontrat;
    }

    public void setNomcontrat(String nomcontrat) {
        this.nomcontrat = nomcontrat;
    }

    public Integer getCoutcontrat() {
        return coutcontrat;
    }

    public void setCoutcontrat(Integer coutcontrat) {
        this.coutcontrat = coutcontrat;
    }

    public Integer getNumerocontrat() {
        return numerocontrat;
    }

    public void setNumerocontrat(Integer numerocontrat) {
        this.numerocontrat = numerocontrat;
    }

    public LocalDate getDatedebutcontrat() {
        return datedebutcontrat;
    }

    public void setDatedebutcontrat(LocalDate datedebutcontrat) {
        this.datedebutcontrat = datedebutcontrat;
    }

    public LocalDate getDatefincontrat() {
        return datefincontrat;
    }

    public void setDatefincontrat(LocalDate datefincontrat) {
        this.datefincontrat = datefincontrat;
    }

    public LocalDate getDaterealisation() {
        return daterealisation;
    }

    public void setDaterealisation(LocalDate daterealisation) {
        this.daterealisation = daterealisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContratDTO contratDTO = (ContratDTO) o;
        if (contratDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contratDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContratDTO{" +
            "id=" + getId() +
            ", nomcontrat='" + getNomcontrat() + "'" +
            ", coutcontrat=" + getCoutcontrat() +
            ", numerocontrat=" + getNumerocontrat() +
            ", datedebutcontrat='" + getDatedebutcontrat() + "'" +
            ", datefincontrat='" + getDatefincontrat() + "'" +
            ", daterealisation='" + getDaterealisation() + "'" +
            ", equipement=" + getEquipementDTO() +
            "}";
    }
}
