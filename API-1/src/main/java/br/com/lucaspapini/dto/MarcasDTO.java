package br.com.lucaspapini.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarcasDTO {
    @JsonProperty("nome")
    public String nome;
    @JsonProperty("codigo")
    public String codigo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "MarcasDTO{" +
                "nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
