package com.belle.util;


import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static final String REGEX = "\\d";

    public static boolean notEmpty(String str) {
        return Objects.isNull(str) ? false : str.trim()=="";
    }

    public static int indexFirstNumber(String input) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            return matcher.start();
        }
        return input.length();
    }

    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
//      ch[0] = Character.toUpperCase(ch[0]);
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    public static boolean isMatch(String regex, String matchText) {
        return Pattern.matches(regex, matchText);
    }
}
