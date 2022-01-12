package org.dio.cognizant.banco.entities;

import lombok.*;
import org.dio.cognizant.banco.entities.tipos.TiposCliente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
@EqualsAndHashCode
public class LancamentoLivro {
    private LocalDateTime data;
    private Conta conta;
    private Double valor;
    private String opercao;

    protected LancamentoLivro(LocalDateTime data, Double valor, Conta conta, String opercao) {
        this.data = data;
        this.valor = valor;
        this.conta = conta;
        this.opercao = opercao;
    }

    @Override
    public String toString() {
        String resp = "";

        String dataOperacao = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String valorOperacao = "R$ " + String.format("%.2f", valor);
        String nomeCliente = conta.getCliente().getNome();
        String tipoCliente = conta.getCliente().getTipo().toString();
        String docTipo = (conta.getCliente().getTipo().equals(TiposCliente.PF)) ? "CPF: " :
                (conta.getCliente().getTipo().equals(TiposCliente.PJ)) ? "CNPJ: " : "documento: ";
        String docCliente = conta.getCliente().getDocumento();
        String contaTipoDescr = conta.getTipo().getDescricao();

        resp += ' ' + dataOperacao + '\t' + opercao.toUpperCase() + '\t' + valorOperacao + '\n';
        resp += '\t' + nomeCliente + '\t' + tipoCliente + "\t" + docTipo + docCliente + '\n';
        resp += '\t' + contaTipoDescr + "\tAgência: " + conta.getAgencia() + "\t Conta: " + conta.getNumero();

        return resp;
    }
}
