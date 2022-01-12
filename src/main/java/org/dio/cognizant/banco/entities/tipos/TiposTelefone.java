package org.dio.cognizant.banco.entities.tipos;

public enum TiposTelefone {
    PESSOAL(1, "Pessoal"),
    TRABALHO(2, "Trabalho"),
    RECADO(3, "Recado");

    Integer codigo;
    String descricao;

    TiposTelefone(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
