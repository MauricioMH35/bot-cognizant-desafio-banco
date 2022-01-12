package org.dio.cognizant.banco.controllers;

import org.dio.cognizant.banco.entities.Banco;
import org.dio.cognizant.banco.entities.Conta;
import org.dio.cognizant.banco.entities.ContaCorrente;
import org.dio.cognizant.banco.entities.ContaPoupanca;
import org.dio.cognizant.banco.entities.tipos.TiposConta;

public class SacarController {
    public void aplicar(Double valor, Conta conta, Banco banco) {
        if (banco.getContas().contains(conta)) {
            if (conta.getTipo().equals(TiposConta.CORRENTE))
                sacarContaCorrente((ContaCorrente) conta, valor);
            else if (conta.getTipo().equals(TiposConta.POUPANCA))
                sacarContaPoupanca((ContaPoupanca) conta, valor);
        } else {
            System.out.println("A conta não existe!");
        }
    }

    private void sacarContaCorrente(ContaCorrente conta, Double valor) {
        conta.sacar(valor, conta);
    }

    private void sacarContaPoupanca(ContaPoupanca conta, Double valor) {
        conta.sacar(valor);
    }
}
