package org.dio.cognizant.banco.utils;

import org.dio.cognizant.banco.entities.Endereco;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnderecoUtil implements Validation<String>, Separator<Endereco> {
    private final String simpleText = "([\\wáàãâéèêíìîóòõôúùû\\s]+)";
    private final String commaAndSpace = ",\\s";
    private final String textTwo = "([\\wáàãâéèêíìîóòõôúùû\\s]{2})";
    private final String cep = "([\\d\\-\\d]+)";
    private final String regex = "^"
            + simpleText + commaAndSpace // Logradouro
            + simpleText + commaAndSpace // Número
            + simpleText + commaAndSpace // Complemento
            + simpleText + commaAndSpace // Bairro
            + simpleText + commaAndSpace // Cidade
            + textTwo + commaAndSpace // Estado
            + textTwo + commaAndSpace // Pais
            + cep
            + "$";

    @Override
    public boolean valid(String target) {
        boolean isValid = Pattern.compile(regex).matcher(target).find();
        return isValid;
    }

    @Override
    public Endereco run(String target) {
        Endereco resposta = new Endereco();

        if (valid(target)) {
            final Matcher matcher = Pattern.compile(regex).matcher(target);
            while (matcher.find()) {
                resposta = new Endereco(
                        matcher.group(1),
                        matcher.group(2),
                        matcher.group(3),
                        matcher.group(4),
                        matcher.group(5),
                        matcher.group(6),
                        matcher.group(7),
                        matcher.group(8)
                );
            }
        } else {
            System.err.println("O endereço não possuí a representação necessária para conseguir capturar os campos!");
            System.out.println("Observação: Os campos estão separados por (,).");
            System.out.println("Exemplo: LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, PAIS, CEP");
        }

        return resposta;
    }
}
