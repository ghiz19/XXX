package com.gmao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Etat entity.
 */
public class EtatDTO implements Serializable {

    private Long id;

    @NotNull
    private String nometat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNometat() {
        return nometat;
    }

    public void setNometat(String nometat) {
        this.nometat = nometat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtatDTO etatDTO = (EtatDTO) o;
        if (etatDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etatDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtatDTO{" +
            "id=" + getId() +
            ", nometat='" + getNometat() + "'" +
            "}";
    }
}
