package br.com.lucaspapini.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ModelosDTO {

    @JsonProperty("modelos")
    public List<MarcasDTO> modelos;
    @JsonProperty("anos")
    public List<Object> anos;

    public List<MarcasDTO> getModelos() {
        return modelos;
    }

    public void setModelos(List<MarcasDTO> modelos) {
        this.modelos = modelos;
    }

    public List<Object> getAnos() {
        return anos;
    }

    public void setAnos(List<Object> anos) {
        this.anos = anos;
    }

    @Override
    public String toString() {
        return "ModelosDTO{" +
                "modelos=" + modelos +
                ", anos=" + anos +
                '}';
    }
}

