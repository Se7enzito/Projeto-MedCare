package org.projeto.API.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class CpfUtils {

    private CpfUtils() {}

    private static String sieve(String dirty) {
        if (dirty == null) return null;
        return dirty.replace(".", "").replace("-", "");
    }

    private static String display(String cpf) {
        if (cpf == null) return null;
        if (!cpf.matches("\\d{11}")) return null;
        if (allCharsEqual(cpf)) return null;

        return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9, 11));
    }

    private static boolean validate(String cpf) {
        if (cpf == null) return false;
        if (!cpf.matches("\\d{11}")) return false;
        if (allCharsEqual(cpf)) return false;

        for (int i = 0; i < 2; i++) {
            int expected = hashDigit(cpf, 10 + i);
            int actual = Character.getNumericValue(cpf.charAt(9 + i));
            if (expected != actual) return false;
        }
        return true;
    }

    private static int hashDigit(String cpf, int position) {
        int sum = 0;
        int weight = position;

        for (int i = 0; i < cpf.length(); i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));

            sum += digit * weight;
            weight--;

            if (weight < 2) break;
        }

        int val = sum % 11;

        return (val < 2) ? 0 : 11 - val;
    }

    private static String checksum(String baseNum) {
        int first = hashDigit(baseNum, 10);
        String secondInput = baseNum + Integer.toString(first);
        int second = hashDigit(secondInput, 11);

        return Integer.toString(first) + Integer.toString(second);
    }

    private static boolean allCharsEqual(String s) {
        if (s == null || s.isEmpty()) return true;

        char first = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != first) return false;
        }

        return true;
    }

    public static String removeSymbols(String dirty) {
        return sieve(dirty);
    }

    public static String formatCpf(String cpf) {
        if (!isValid(cpf)) return null;
        return display(cpf);
    }

    public static boolean isValid(String cpf) {
        return cpf instanceof String && validate(cpf);
    }

    public static String generate() {
        int rand = ThreadLocalRandom.current().nextInt(1, 999_999_999);
        String base = String.format("%09d", rand);

        return base + checksum(base);
    }

}