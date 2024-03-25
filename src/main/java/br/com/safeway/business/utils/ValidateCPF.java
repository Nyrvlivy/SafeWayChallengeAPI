package br.com.safeway.business.utils;

public class ValidateCPF {

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) return false;

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        int[] weights = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * weights[i + 1];
        }
        int checkDigit1 = (sum * 10) % 11 == 10 ? 0 : (sum * 10) % 11;
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * weights[i];
        }
        int checkDigit2 = (sum * 10) % 11 == 10 ? 0 : (sum * 10) % 11;

        return checkDigit1 == (cpf.charAt(9) - '0') && checkDigit2 == (cpf.charAt(10) - '0');
    }

}
