package org.dio.cognizant.banco;

import org.dio.cognizant.banco.configs.RepositoryFactory;
import org.dio.cognizant.banco.controllers.*;
import org.dio.cognizant.banco.entities.*;
import org.dio.cognizant.banco.entities.tipos.TiposCliente;
import org.dio.cognizant.banco.entities.tipos.TiposTelefone;
import org.dio.cognizant.banco.utils.EnderecoUtil;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Variaveis básicas
        Banco banco = new Banco();
        AbrirContaController abrirCtrl = RepositoryFactory.getAbrirContaCtrl();
        SacarController sacarCtrl = RepositoryFactory.getSacarCtrl();
        DepositarController depositarCtrl = RepositoryFactory.getDepositarCtrl();
        TransferirController transferirCtrl = RepositoryFactory.getTransferirCtrl();
        ExtratoController extratoCtrl = RepositoryFactory.getExtratoCtrl();

        // Utilitários
        EnderecoUtil enderecoUtil = new EnderecoUtil();

        // Variaveis default
        TiposCliente tipoPJ = TiposCliente.PJ;
        String docPJ = "00.000.000/0000-00"; // Documento não é válido (Add um válido)
        TiposCliente tipoPF = TiposCliente.PF;
        String docPF = "000.000.000-00";  // Documento não é válido (Add um válido)
        Set<Telefone> fones = new HashSet<>() {{
            add(new Telefone(TiposTelefone.PESSOAL, "11", "96421-5612"));
            add(new Telefone(TiposTelefone.TRABALHO, "11", "2011-1379"));
        }};
        Endereco endereco = enderecoUtil.run(
                "R Qualquer, 11, Casa A, Vila Qualquer, São Qualquer, SP, BR, 0810-000");

        // Declaração de clientes e contas
        String nomeA = "Cognizant";
        String emailA = "cognizant@dev.com";
        Cliente clienteA = new Cliente(nomeA, tipoPJ, docPJ, emailA, fones, endereco);
        Conta contaA = new ContaCorrente(clienteA);

        String nomeB = "José Sampaio de Almeida";
        String emailB = "josesamp@gmail.com";
        Cliente clienteB = new Cliente(nomeB, tipoPF, docPF, emailB, fones, endereco);
        Conta contaB = new ContaPoupanca(clienteB);

        String nomeC = "Bianva Alvares de Sousa";
        String emailC = "bialva@outlook.com";
        Cliente clienteC = new Cliente(nomeC, tipoPF, docPF, emailC, fones, endereco);
        Conta contaC = new ContaCorrente(clienteC);

        String nomeD = "Leonardo Alguma Coisa";
        String emailD = "leonidas@hotmail.com";
        Cliente clienteD = new Cliente(nomeD, tipoPF, docPF, emailD, fones, endereco);
        Conta contaD = new ContaCorrente(clienteD);

        // Abrindo contas
        abrirCtrl.aplicar(banco, clienteA, contaA);
        abrirCtrl.aplicar(banco, clienteB, contaB);
        abrirCtrl.aplicar(banco, clienteC, contaC);
        abrirCtrl.aplicar(banco, clienteD, contaD);

        // Operações bancárias
        depositarCtrl.aplicar(10000d, contaA, banco);
        depositarCtrl.aplicar(250d, contaB, banco);
        depositarCtrl.aplicar(1245.12d, contaC, banco);
        depositarCtrl.aplicar(130d, contaD, banco);

        sacarCtrl.aplicar(125.50d, contaA, banco);
        sacarCtrl.aplicar(250d, contaB, banco);
        sacarCtrl.aplicar(245d, contaC, banco);
        sacarCtrl.aplicar(130d, contaD, banco);

        transferirCtrl.aplicar(230d, contaA, contaB, banco);
        transferirCtrl.aplicar(150d, contaB, contaC, banco);
        transferirCtrl.aplicar(50d, contaC, contaD, banco);
        transferirCtrl.aplicar(300d, contaD, contaB, banco);

        // Extratos das contas
        System.out.println("Mostrar todos os extratos\n");
        System.out.println(extratoCtrl.aplicar(1, contaA) + '\n');
        System.out.println(extratoCtrl.aplicar(1, contaB) + '\n');
        System.out.println(extratoCtrl.aplicar(1, contaC) + '\n');
        System.out.println(extratoCtrl.aplicar(1, contaD) + '\n');
    }
}
