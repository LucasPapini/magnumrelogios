package br.com.lucaspapini.dto;

public class MarcaNomeDTO {
    private String marca;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "{" +
                "marca='" + marca + '\'' +
                '}';
    }
}
