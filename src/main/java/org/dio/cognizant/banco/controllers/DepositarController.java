package org.dio.cognizant.banco.controllers;

import org.dio.cognizant.banco.entities.Banco;
import org.dio.cognizant.banco.entities.Conta;
import org.dio.cognizant.banco.entities.ContaCorrente;
import org.dio.cognizant.banco.entities.ContaPoupanca;
import org.dio.cognizant.banco.entities.tipos.TiposConta;

public class DepositarController {
    public void aplicar(Double valor, Conta conta, Banco banco) {
        if (banco.getContas().contains(conta)) {
            if (conta.getTipo().equals(TiposConta.CORRENTE))
                depositoContaCorrente((ContaCorrente) conta, valor);
            else if (conta.getTipo().equals(TiposConta.POUPANCA))
                depositoContaPoupanca((ContaPoupanca) conta, valor);

        } else {
            System.out.println("A conta não existe!");
        }
    }

    private void depositoContaCorrente(ContaCorrente conta, Double valor) {
        conta.depositar(valor);
    }

    private void depositoContaPoupanca(ContaPoupanca conta, Double valor) {
        conta.depositar(valor);
    }
}
