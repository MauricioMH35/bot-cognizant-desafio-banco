package org.dio.cognizant.banco.entities;

import lombok.*;
import org.dio.cognizant.banco.entities.tipos.TiposConta;

import java.awt.desktop.AboutEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor
@ToString
public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente) {
        this.setAgencia(1);
        this.setNumero(NUMERO_SEQUENCIA++);
        this.setSaldo(0d);
        this.setTipo(TiposConta.POUPANCA);
        this.setCliente(cliente);
        this.setLancamentos(new ArrayList<>());
    }

    public void sacar(Double valor) {
        if (valor <= getSaldo()) {
            this.debitar(valor); // Debitando desta conta
            this.setLancamento(LocalDateTime.now(), (valor * -1), this, "saque"); // Lançando debido desta conta
        } else {
            System.err.println("Não é possível sacar o valor indicado, saldo em conta é insuficiente!");
            return;
        }
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            this.creditar(valor); // Creditando para esta conta
            this.setLancamento(LocalDateTime.now(), valor, this, "depósito"); // Lançamento credito desta conta
        } else {
            System.err.println("Não é possível depositar valores negativos!");
            return;
        }
    }

    public void transferir(Double valor, ContaCorrente destinatario) {
        boolean isValid = !destinatario.equals(null) &&
                !destinatario.getNumero().equals(this.getNumero()) &&
                destinatario.getCliente().getDocumento().equals(this.getCliente().getDocumento());
        if (isValid) {
            this.sacar(valor); // Debitando desta conta
            destinatario.creditar(valor); // Creditando para a conta indicada

            destinatario.setLancamento(LocalDateTime.now(), valor, this, "depósito transferido"); // Lançando credito para a conta indicada
        } else {
            System.err.println("Não é possível transferir para uma conta não existente!");
            return;
        }
    }

    public void transferir(Double valor, ContaPoupanca destinatario) {
        boolean isValid = !destinatario.equals(null) &&
                !destinatario.getNumero().equals(this.getNumero()) &&
                !destinatario.getCliente().getDocumento().equals(this.getCliente().getDocumento());
        if (isValid) {
            this.sacar(valor); // Debitando desta conta
            destinatario.creditar(valor); // Creditando para a conta indicada

            destinatario.setLancamento(LocalDateTime.now(), valor, this, "depósito transferido"); // Lançando credito para a conta indicada
        } else {
            System.err.println("Não é possível transferir para uma conta não existente!");
            return;
        }
    }
}
