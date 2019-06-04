package com.gmao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Planprevetinf entity.
 */
public class PlanprevetinfDTO implements Serializable {

    private Long id;

    @NotNull
    private String descriptionplan;


   private EquipementDTO equipementPlan;

    public EquipementDTO getEquipementPlan() {
        return equipementPlan;
    }

    public void setEquipementPlan(EquipementDTO equipementPlan) {
        this.equipementPlan = equipementPlan;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionplan() {
        return descriptionplan;
    }

    public void setDescriptionplan(String descriptionplan) {
        this.descriptionplan = descriptionplan;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlanprevetinfDTO planprevetinfDTO = (PlanprevetinfDTO) o;
        if (planprevetinfDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planprevetinfDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanprevetinfDTO{" +
            "id=" + getId() +
            ", descriptionplan='" + getDescriptionplan() + "'" +
            ", equipement=" + getEquipementPlan() +
            "}";
    }
}
