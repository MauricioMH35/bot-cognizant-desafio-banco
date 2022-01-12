package org.dio.cognizant.banco.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Banco {
    private List<Conta> contas = new ArrayList<>();

    public Banco() {

    }

    public void printContas() {
        contas.stream().forEach(System.out::println);
    }
}
