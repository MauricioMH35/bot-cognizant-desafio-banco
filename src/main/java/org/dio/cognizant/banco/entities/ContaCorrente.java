package org.dio.cognizant.banco.entities;

import lombok.*;
import org.dio.cognizant.banco.entities.tipos.TiposConta;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        this.setAgencia(1);
        this.setNumero(NUMERO_SEQUENCIA++);
        this.setSaldo(0d);
        this.setCliente(cliente);
        this.setTipo(TiposConta.CORRENTE);
        this.setLancamentos(new ArrayList<>());
    }

    private double calcMontante(Double valor) {
        int meses = 6;
        int fator;
        double porcentagem = (valor / getSaldo());
        double montante = valor;

        if (porcentagem <= 3) {
            fator = 60;
            porcentagem /= fator;
            montante = valor * Math.pow((1 + porcentagem), meses);
        } else if (porcentagem <= 6) {
            fator = 40;
            porcentagem /= fator;
            montante = valor * Math.pow((1 + porcentagem), meses);
        } else if (porcentagem > 6) {
            fator = 30;
            porcentagem /= fator;
            montante = valor * Math.pow((1 + porcentagem), meses);
        }

        return montante;
    }

    public void sacar(Double valor, Conta destinatario) {
        if (valor <= getSaldo()) {
            this.debitar(valor); // Debitando desta conta

            if (destinatario.getNumero().equals(this.getNumero()))
                setLancamento(LocalDateTime.now(), (valor * -1), destinatario, "saque"); // Lançando debito desta conta
            else
                setLancamento(LocalDateTime.now(), (valor * -1), destinatario, "transferência"); // Lançando debito desta conta

        } else {
            double calcMontanteFinal = calcMontante(valor);
            double calcSaldoFinal = calcMontanteFinal * -1;
            this.debitar(calcMontanteFinal); // Debitando valor acima do saldo desta conta

            if (destinatario.getNumero().equals(this.getNumero()))
                setLancamento(LocalDateTime.now(), calcSaldoFinal, destinatario, "saque com taxa"); // Lançando debido desta conta
            else
                setLancamento(LocalDateTime.now(), calcSaldoFinal, destinatario, "transferência com taxa"); // Lançando debido desta conta
        }
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            this.creditar(valor); // Creditando para esta conta
            setLancamento(LocalDateTime.now(), valor, this, "depósito"); // Lançando credito desta conta
        } else {
            System.err.println("Não é possível depositar valores negativos!");
        }
    }

    public void transferir(Double valor, ContaCorrente destinatario) {
        if (!destinatario.equals(null)) {
            this.sacar(valor, destinatario); // Debitando desta conta
            destinatario.creditar(valor); // Creditando para esta conta

            destinatario.setLancamento(LocalDateTime.now(), valor, this, "depósito transferido"); // Lançando credito para esta conta
        } else {
            System.err.println("Não é possível transferir para uma conta não existente!");
        }
    }

    public void transferir(Double valor, ContaPoupanca destinatario) {
        if (!destinatario.equals(null)) {
            this.sacar(valor, destinatario); // Debitando desta conta
            destinatario.creditar(valor); // Creditando para esta conta

            destinatario.setLancamento(LocalDateTime.now(), valor, this, "depósito transferido"); // Lançando credito para esta conta
        } else {
            System.err.println("Não é possível transferir para uma conta não existente!");
        }
    }
}
