package utils;

import java.util.regex.Pattern;

public class Validador {

    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

    private static final Pattern CONTA_PATTERN = Pattern.compile("^C-\\d{3}$");

     // Verifica se uma string de CPF corresponde ao formato "xxx.xxx.xxx-xx".

    public static boolean validarFormatoCPF(String cpf) {
        if (cpf == null) {
            return false;
        }
        return CPF_PATTERN.matcher(cpf).matches();
    }

     // Verifica se uma string de Conta corresponde ao formato "C-xxx".

    public static boolean validarFormatoConta(String numeroConta) {
        if (numeroConta == null) {
            return false;
        }
        return CONTA_PATTERN.matcher(numeroConta).matches();
    }
}