package ru.sbtqa.tag.goms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static String get(String input, String regex) {
        Pattern p = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m = p.matcher(input);
        String text = "";
        while (m.find()) {
            text = m.group();
        }

        return text;
    }
}
