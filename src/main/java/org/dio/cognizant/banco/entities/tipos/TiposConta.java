package org.dio.cognizant.banco.entities.tipos;

public enum TiposConta {
    CORRENTE(1, "Conta Corrente"),
    POUPANCA(2, "Conta Poupança");

    Integer codigo;
    String descricao;

    TiposConta(Integer codigo, String descricao) {
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
