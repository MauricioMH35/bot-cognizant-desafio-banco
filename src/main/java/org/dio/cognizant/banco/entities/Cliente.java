package org.dio.cognizant.banco.entities;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.dio.cognizant.banco.entities.tipos.TiposCliente;
import org.dio.cognizant.banco.utils.CnpjUtil;
import org.dio.cognizant.banco.utils.CpfUtil;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Cliente {
    private String nome;
    private TiposCliente tipo;
    private String documento;
    private String email;
    private Set<Telefone> telefone;
    private Endereco endereco;

    public Cliente(String nome, TiposCliente tipo, String documento,
                   String email, Set<Telefone> telefone, Endereco endereco) {
        this.setNome(nome);
        this.setTipo(tipo);
        if (tipo.equals(TiposCliente.PF)) {
            CpfUtil cpf = new CpfUtil();
            if (cpf.valid(documento))
                this.setDocumento(documento);
            else
                System.err.println("O documento informado não é válido!");

        } else if (tipo.equals(TiposCliente.PJ)) {
            CnpjUtil cnpj = new CnpjUtil();
            if (cnpj.valid(documento))
                this.setDocumento(documento);
            else
                System.err.println("O documento informado não é válido!");
        }
        this.setEmail(email);
        this.setTelefone(telefone);
        this.setEndereco(endereco);
    }

    @Override
    public String toString() {
        return "cliente: {" +
                " nome: " + nome +
                ", tipo: " + tipo +
                ", documento: " + documento +
                ", email: " + email +
                ", telefones: " + telefone + ", " +
                endereco +
                " }";
    }
}
