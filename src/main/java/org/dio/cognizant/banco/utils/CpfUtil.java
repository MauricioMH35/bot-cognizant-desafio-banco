package org.dio.cognizant.banco.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CpfUtil implements Validation<String>, Separator<List<Integer>> {
    private final String regex = "^(\\d{3})\\.(\\d{3})\\.(\\d{3})\\-(\\d{2})$";
    private final String regexSeparator = "(\\d{1})";

    @Override
    public boolean valid(String target) {
        boolean isConteudo = target.matches(regex);
        boolean isPrimeiroDigito = false;
        boolean isSegundoDigito = false;

        if (isConteudo)
            isPrimeiroDigito = validDigito(target, 2);

        if (isPrimeiroDigito)
            isSegundoDigito = validDigito(target, 1);

        return (isConteudo && isPrimeiroDigito && isSegundoDigito);
    }

    private boolean validDigito(String target, int digitoCasaVerificada) {
        List<Integer> digitos = running(target);
        int soma = 0;
        int resto = 0;
        int digito = 0;

        for (int i = 0; i < (digitos.size() - digitoCasaVerificada); i++) {
            int fator = (digitos.size() - 1) - i;
            soma += digitos.get(i) * fator;
        }

        resto = soma % 11;

        if (resto < 2)
            digito = 0;
        else if (resto >= 2)
            digito = 11 - resto;

        if (digitos.get((digitos.size() - digitoCasaVerificada)).equals(digito))
            return true;
        else
            return false;
    }

    private List<Integer> running(String target) {
        List<Integer> matchers = new ArrayList<>();
        final Matcher matcher = Pattern.compile(regexSeparator).matcher((target));

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                matchers.add(Integer.parseInt(matcher.group(i)));
            }
        }

        return matchers;
    }

    @Override
    public List<Integer> run(String target) {
        List<Integer> matchers = new ArrayList<>();
        final Matcher matcher = Pattern.compile(regex).matcher((target));

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                matchers.add(Integer.parseInt(matcher.group(i)));
            }
        }

        return matchers;
    }
}
