package com.gmao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Typeequipement entity.
 */
public class TypeequipementDTO implements Serializable {

    private Long id;

    @NotNull
    private String typeequipem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeequipem() {
        return typeequipem;
    }

    public void setTypeequipem(String typeequipem) {
        this.typeequipem = typeequipem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeequipementDTO typeequipementDTO = (TypeequipementDTO) o;
        if (typeequipementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeequipementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeequipementDTO{" +
            "id=" + getId() +
            ", typeequipem='" + getTypeequipem() + "'" +
            "}";
    }
}
