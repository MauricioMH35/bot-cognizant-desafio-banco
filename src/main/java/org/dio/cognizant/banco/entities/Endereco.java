package org.dio.cognizant.banco.entities;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor
@EqualsAndHashCode
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;

    public Endereco(String logradouro, String numero, String complemento, String bairro,
                    String cidade, String estado, String pais, String cep) {
        this.setLogradouro(logradouro);
        this.setNumero(numero);
        this.setComplemento(complemento);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
        this.setPais(pais);
        this.setCep(cep);
    }

    @Override
    public String toString() {
        return "endereço: {" +
                " logradouro: " + logradouro +
                ", numero: " + numero +
                ", complemento: " + complemento +
                ", bairro: " + bairro +
                ", cidade: " + cidade +
                ", estado: " + estado +
                ", pais: " + pais +
                ", cep: " + cep +
                " }";
    }
}
