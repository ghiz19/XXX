package com.gmao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Localisations entity.
 */
public class LocalisationsDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomlocalisation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomlocalisation() {
        return nomlocalisation;
    }

    public void setNomlocalisation(String nomlocalisation) {
        this.nomlocalisation = nomlocalisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LocalisationsDTO localisationsDTO = (LocalisationsDTO) o;
        if (localisationsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), localisationsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LocalisationsDTO{" +
            "id=" + getId() +
            ", nomlocalisation='" + getNomlocalisation() + "'" +
            "}";
    }
}
