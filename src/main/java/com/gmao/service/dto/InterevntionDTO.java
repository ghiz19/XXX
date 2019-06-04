package com.gmao.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Interevntion entity.
 */
public class InterevntionDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime datedebutintervention;

    @NotNull
    private ZonedDateTime datefinintervention;

    private String duree;

    @NotNull
    private String coutmaindeuvre;

    @NotNull
    private String coutinterevntion;

    @NotNull
    private String solutions;


    private DemandeinterventionDTO demandeinterventionDTO;

    private PlanprevetinfDTO planprevetinfDTO;

    private EtatDTO etatDTO;

    private EquipeDTO equipeDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatedebutintervention() {
        return datedebutintervention;
    }

    public void setDatedebutintervention(ZonedDateTime datedebutintervention) {
        this.datedebutintervention = datedebutintervention;
    }

    public ZonedDateTime getDatefinintervention() {
        return datefinintervention;
    }

    public void setDatefinintervention(ZonedDateTime datefinintervention) {
        this.datefinintervention = datefinintervention;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getCoutmaindeuvre() {
        return coutmaindeuvre;
    }

    public void setCoutmaindeuvre(String coutmaindeuvre) {
        this.coutmaindeuvre = coutmaindeuvre;
    }

    public String getCoutinterevntion() {
        return coutinterevntion;
    }

    public void setCoutinterevntion(String coutinterevntion) {
        this.coutinterevntion = coutinterevntion;
    }

    public String getSolutions() {
        return solutions;
    }

    public void setSolutions(String solutions) {
        this.solutions = solutions;
    }

    public DemandeinterventionDTO getDemandeinterventionDTO() {
        return demandeinterventionDTO;
    }

    public void setDemandeinterventionDTO(DemandeinterventionDTO demandeinterventionDTO) {
        this.demandeinterventionDTO = demandeinterventionDTO;
    }
    public PlanprevetinfDTO getPlanprevetinfDTO() {
        return planprevetinfDTO;
    }

    public void setPlanprevetinfDTO(PlanprevetinfDTO planprevetinfDTO) {
        this.planprevetinfDTO = planprevetinfDTO;
    }

    public EtatDTO getEtatDTO() {
        return etatDTO;
    }

    public void setEtatDTO(EtatDTO etatDTO) {
        this.etatDTO = etatDTO;
    }


    public EquipeDTO getEquipeDTO() {
        return equipeDTO;
    }

    public void setEquipeDTO(EquipeDTO equipeDTO) {
        this.equipeDTO = equipeDTO;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InterevntionDTO interevntionDTO = (InterevntionDTO) o;
        if (interevntionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), interevntionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InterevntionDTO{" +
            "id=" + getId() +
            ", datedebutintervention='" + getDatedebutintervention() + "'" +
            ", datefinintervention='" + getDatefinintervention() + "'" +
            ", duree='" + getDuree() + "'" +
            ", coutmaindeuvre='" + getCoutmaindeuvre() + "'" +
            ", coutinterevntion='" + getCoutinterevntion() + "'" +
            ", solutions='" + getSolutions() + "'" +
            ", demandeintervention=" + getDemandeinterventionDTO() +
            ", planprevetinf=" + getPlanprevetinfDTO() +
            ", etat=" + getEtatDTO() +
            ", equipe=" + getEquipeDTO() +
            "}";
    }
}
