package org.dio.cognizant.banco.controllers;

import org.dio.cognizant.banco.entities.Banco;
import org.dio.cognizant.banco.entities.Conta;
import org.dio.cognizant.banco.entities.ContaCorrente;
import org.dio.cognizant.banco.entities.ContaPoupanca;
import org.dio.cognizant.banco.entities.tipos.TiposConta;

public class TransferirController {
    public void aplicar(Double valor, Conta conta, Conta destinatario, Banco banco) {
        if (banco.getContas().contains(conta)) {
            if (conta.getTipo().equals(TiposConta.CORRENTE))
                transferirContaCorrente(destinatario, (ContaCorrente) conta, valor);
            else if (conta.getTipo().equals(TiposConta.POUPANCA))
                transferirContaPoupanca(destinatario, (ContaPoupanca) conta, valor);
        } else {
            System.out.println("A conta não existe!");
        }
    }

    public void transferirContaCorrente(Conta destinatario, ContaCorrente conta, Double valor) {
        if (destinatario.getTipo().equals(TiposConta.CORRENTE))
            conta.transferir(valor, (ContaCorrente) destinatario);
        else if (destinatario.getTipo().equals(TiposConta.POUPANCA))
            conta.transferir(valor, (ContaPoupanca) destinatario);
    }

    private void transferirContaPoupanca(Conta destinatario, ContaPoupanca conta, Double valor) {
        if (destinatario.getTipo().equals(TiposConta.CORRENTE))
            conta.transferir(valor, (ContaCorrente) destinatario);
        else if (destinatario.getTipo().equals(TiposConta.POUPANCA))
            conta.transferir(valor, (ContaPoupanca) destinatario);
    }
}
