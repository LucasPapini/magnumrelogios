package br.com.lucaspapini.dto;

public class VeiculoDTO {
    private String codigo;
    private String modelo;
    private String observacao;
    private String marca;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "VeiculoDTO{" +
                "codigo='" + codigo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
