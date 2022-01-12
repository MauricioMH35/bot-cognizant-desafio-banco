package org.dio.cognizant.banco.controllers;

import org.dio.cognizant.banco.entities.Conta;
import org.dio.cognizant.banco.entities.LancamentoLivro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class ExtratoController {
    public String aplicar(int mes, Conta conta) {
        String resposta = "----------------------- Banco Cognizant -----------------------\n";
        resposta += conta.getCliente().getNome() + " (" + conta.getCliente().getTipo().getDescricao() + ")\t";
        resposta += conta.getCliente().getDocumento() + '\n';
        resposta += conta.getTipo().getDescricao() + '\t';
        resposta += "Agência: " + conta.getAgencia() + "\t Conta: " + conta.getNumero() + '\n';
        resposta += "---------------------------------------------------------------\n";
        resposta += "Extrato Bancário" + "\n\n";
        resposta += conta.getLancamentos().stream()
                .filter(l -> l.getData().getMonthValue() == mes)
                .map(e -> e.toString())
                .collect(Collectors.joining("\n"));

        double saldo = 0d;
        for (LancamentoLivro c : conta.getLancamentos()) {
            saldo += c.getValor();
        }
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        resposta += "\n---------------------------------------------------------------";
        resposta += "\n " + data + "\tSaldo: R$ " + String.format("%.2f", saldo);
        resposta += "\n---------------------------------------------------------------";

        return resposta;
    }
}
