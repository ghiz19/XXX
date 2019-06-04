package com.gmao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Documentations entity.
 */
public class DocumentationsDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomdocumentation;


    private Set<EquipementDTO> equipements = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomdocumentation() {
        return nomdocumentation;
    }

    public void setNomdocumentation(String nomdocumentation) {
        this.nomdocumentation = nomdocumentation;
    }

    public Set<EquipementDTO> getEquipements() {
        return equipements;
    }

    public void setEquipements(Set<EquipementDTO> equipements) {
        this.equipements = equipements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentationsDTO documentationsDTO = (DocumentationsDTO) o;
        if (documentationsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentationsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentationsDTO{" +
            "id=" + getId() +
            ", nomdocumentation='" + getNomdocumentation() + "'" +
            "}";
    }
}
