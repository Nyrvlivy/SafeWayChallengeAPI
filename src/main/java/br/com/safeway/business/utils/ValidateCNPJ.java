package br.com.safeway.business.utils;

public class ValidateCNPJ {
    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || cnpj.matches(cnpj.charAt(0) + "{14}")) return false;

        try {
            Long.parseLong(cnpj);
        } catch (NumberFormatException e) {
            return false;
        }

        int[] weightsFirstDigit = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weightsSecondDigit = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weightsFirstDigit[i];
        }
        int checkDigit1 = (sum % 11 < 2) ? 0 : (11 - (sum % 11));
        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * weightsSecondDigit[i];
        }
        int checkDigit2 = (sum % 11 < 2) ? 0 : (11 - (sum % 11));

        return checkDigit1 == (cnpj.charAt(12) - '0') && checkDigit2 == (cnpj.charAt(13) - '0');
    }

}
