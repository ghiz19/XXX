package com.gmao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Pm entity.
 */
public class PmDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    private String unite;

    @NotNull
    private Boolean avoirplanprevetinf;


    private EquipementDTO equipementDtoo;

    public EquipementDTO getEquipementDtoo() {
        return equipementDtoo;
    }

    public void setEquipementDtoo(EquipementDTO equipementDtoo) {
        this.equipementDtoo = equipementDtoo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Boolean isAvoirplanprevetinf() {
        return avoirplanprevetinf;
    }

    public void setAvoirplanprevetinf(Boolean avoirplanprevetinf) {
        this.avoirplanprevetinf = avoirplanprevetinf;
    }

   
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PmDTO pmDTO = (PmDTO) o;
        if (pmDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pmDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PmDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", unite='" + getUnite() + "'" +
            ", avoirplanprevetinf='" + isAvoirplanprevetinf() + "'" +
            ", equipement=" + getEquipementDtoo() +
            "}";
    }
}
