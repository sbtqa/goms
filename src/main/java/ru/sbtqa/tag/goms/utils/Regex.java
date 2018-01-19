package ru.sbtqa.tag.goms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static String get(String input, String regex) {
        return get(input, regex, 0);
    }

    public static String get(String input, String regex, int matchNumber) {
        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        String text = "";
        int counter = 1;
        
        if (getMatchesCount(matcher) < matchNumber) {
            return "";
        }
        while (matcher.find()) {
            text = matcher.group();
            if (counter == matchNumber) {
                return text;
            }
            counter++;
        }

        return text;
    }

    private static int getMatchesCount(Matcher matcher) {
        int matches = 0;

        while (matcher.find()) {
            matches++;
        }
        matcher.reset();
        
        return matches;
    }
}
