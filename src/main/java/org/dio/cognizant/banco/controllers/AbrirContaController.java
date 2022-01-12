package org.dio.cognizant.banco.controllers;

import org.dio.cognizant.banco.entities.*;
import org.dio.cognizant.banco.entities.tipos.TiposConta;

public class AbrirContaController {
    public void aplicar(Banco banco, Cliente cliente, Conta conta) {
        if (!cliente.equals(null) && !conta.equals(null)) {
            conta.inserirCliente(cliente);
            if (conta.getTipo().equals(TiposConta.CORRENTE))
                abrirContaCorrente((ContaCorrente) conta, banco);
            else if (conta.getTipo().equals(TiposConta.POUPANCA))
                abrirContaPoupanca((ContaPoupanca) conta, banco);
        } else {
            System.err.println("Não é possível prosseguir com a abertura de conta!");
        }
    }

    private void abrirContaCorrente(ContaCorrente conta, Banco banco) {
        int i = 0;
        for (Conta c : banco.getContas()) {
            if (c.getNumero().equals(conta.getNumero())) {
                i++;
                break;
            }
        }

        if (i == 0)
            banco.getContas().add(conta);
        else
            System.err.println("Verifique com um dos nossos atendentes a situação da conta!");
    }

    private void abrirContaPoupanca(ContaPoupanca conta, Banco banco) {
        int i = 0;
        for (Conta c : banco.getContas()) {
            if (c.getNumero().equals(conta.getNumero())) {
                i++;
                break;
            }
        }

        if (i == 0)
            banco.getContas().add(conta);
        else
            System.err.println("Verifique com um dos nossos atendentes a situação da conta!");
    }
}
