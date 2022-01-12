package org.dio.cognizant.banco.entities.tipos;

public enum TiposCliente {
    PF(1, "Pessoa Física"),
    PJ(2, "Pessoa Jurídica");

    Integer codigo;
    String descricao;

    TiposCliente(Integer codigo, String descricao) {
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
