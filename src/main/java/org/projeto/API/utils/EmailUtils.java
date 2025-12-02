package org.projeto.API.utils;

import java.util.regex.Pattern;

public class EmailUtils {

    private EmailUtils() {}

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^(?![.])[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        return email != null && pattern.matcher(email).matches();
    }

}
