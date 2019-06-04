package com.gmao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Servicee entity.
 */
public class ServiceeDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomService;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceeDTO serviceeDTO = (ServiceeDTO) o;
        if (serviceeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), serviceeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ServiceeDTO{" +
            "id=" + getId() +
            ", nomService='" + getNomService() + "'" +
            "}";
    }
}
