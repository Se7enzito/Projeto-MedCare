package org.projeto.API.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelefoneUtils {

    private TelefoneUtils() {}

    private static final Random random = new Random();

    public static String formatPhone(String phone) {
        if (!isValid(phone, null)) {
            return null;
        }

        String ddd = phone.substring(0, 2);
        String phoneNumber = phone.substring(2);

        String part1 = phoneNumber.substring(0, phoneNumber.length() - 4);
        String part2 = phoneNumber.substring(phoneNumber.length() - 4);

        return "(" + ddd + ")" + part1 + "-" + part2;
    }

    public static boolean isValid(String phoneNumber, String type) {
        if ("landline".equals(type)) {
            return isValidLandline(phoneNumber);
        }
        if ("mobile".equals(type)) {
            return isValidMobile(phoneNumber);
        }
        return isValidLandline(phoneNumber) || isValidMobile(phoneNumber);
    }

    public static String removeSymbolsPhone(String phoneNumber) {
        return phoneNumber
                .replace("(", "")
                .replace(")", "")
                .replace("-", "")
                .replace("+", "")
                .replace(" ", "");
    }

    public static String generate(String type) {
        if ("mobile".equals(type)) {
            return generateMobilePhone();
        }
        if ("landline".equals(type)) {
            return generateLandlinePhone();
        }

        return random.nextBoolean() ? generateLandlinePhone() : generateMobilePhone();
    }

    public static String removeInternationalDialingCode(String phoneNumber) {

        Pattern pattern = Pattern.compile("\\+?55");
        Matcher matcher = pattern.matcher(phoneNumber);

        String cleaned = phoneNumber.replace(" ", "");

        if (matcher.find() && cleaned.length() > 11) {
            return phoneNumber.replaceFirst("55", "");
        }

        return phoneNumber;
    }

    private static boolean isValidMobile(String phoneNumber) {
        Pattern pattern = Pattern.compile("^[1-9][1-9]9\\d{8}$");
        return phoneNumber != null && pattern.matcher(phoneNumber).matches();
    }

    private static boolean isValidLandline(String phoneNumber) {
        Pattern pattern = Pattern.compile("^[1-9][1-9][2-5]\\d{7}$");
        return phoneNumber != null && pattern.matcher(phoneNumber).matches();
    }

    private static String generateDDD() {
        int d1 = random.nextInt(9) + 1;
        int d2 = random.nextInt(9) + 1;
        return "" + d1 + d2;
    }

    private static String generateMobilePhone() {
        String ddd = generateDDD();
        StringBuilder client = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            client.append(random.nextInt(10));
        }

        return ddd + "9" + client;
    }

    private static String generateLandlinePhone() {
        String ddd = generateDDD();

        int prefix = random.nextInt(4) + 2; // entre 2 e 5
        String suffix = String.format("%07d", random.nextInt(10_000_000));

        return ddd + prefix + suffix;
    }

}
