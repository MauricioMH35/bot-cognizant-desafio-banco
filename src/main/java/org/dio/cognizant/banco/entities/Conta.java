package org.dio.cognizant.banco.entities;

import lombok.*;
import org.dio.cognizant.banco.entities.tipos.TiposConta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Conta {
    protected static Integer NUMERO_SEQUENCIA = 1;

    private TiposConta tipo;
    private Integer agencia;
    private Integer numero;
    private Double saldo;
    private Cliente cliente;
    private List<LancamentoLivro> lancamentos;

    public Conta(Cliente cliente) {
        this.tipo = TiposConta.CORRENTE;
        this.agencia = 1;
        this.numero = NUMERO_SEQUENCIA++;
        this.saldo = 0d;
        this.cliente = cliente;
        this.lancamentos = new ArrayList<>();
    }

    public void inserirCliente(Cliente cliente) {
        if (!this.cliente.equals(null))
            this.cliente = cliente;
        else
            System.err.println("A conta não é permitida ao cliente!");
    }

    public void setLancamento(LocalDateTime data, Double valor, Conta conta, String operacao) {
        lancamentos.add(new LancamentoLivro(data, valor, conta, operacao));
    }

    protected void debitar(Double valor) {
        this.saldo -= valor;
    }

    protected void creditar(Double valor) {
        this.saldo += valor;
    }
}
