package org.dio.cognizant.banco.entities;

import lombok.*;
import org.dio.cognizant.banco.entities.tipos.TiposTelefone;

@Getter
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor
@EqualsAndHashCode
public class Telefone {
    private TiposTelefone tipo;
    private String prefixo;
    private String numero;

    public Telefone(TiposTelefone tipo, String prefixo, String numero) {
        this.tipo = tipo;
        this.prefixo = prefixo;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "telefone: {" +
                " tipo: " + tipo +
                ", prefixo: " + prefixo +
                ", numero: " + numero +
                " }";
    }
}
