package com.gmao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Equipe entity.
 */
public class EquipeDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomequipe;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomequipe() {
        return nomequipe;
    }

    public void setNomequipe(String nomequipe) {
        this.nomequipe = nomequipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EquipeDTO equipeDTO = (EquipeDTO) o;
        if (equipeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EquipeDTO{" +
            "id=" + getId() +
            ", nomequipe='" + getNomequipe() + "'" +
            "}";
    }
}
