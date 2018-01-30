package ru.sbtqa.tag.goms.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import ru.sbtqa.tag.goms.model.Operator;
import ru.sbtqa.tag.goms.model.Operators;
import ru.sbtqa.tag.goms.process.tokens.Token;
import ru.sbtqa.tag.goms.process.tokens.TokenFactory;
import ru.sbtqa.tag.goms.utils.Templates;

public class Parser {

    private static final String REGEX_CONTROL_CHARS = "[\n\t]";
    private static final String REGEX_KEYWORDS = "^(%s).*$";
    private static final String FEATURE_KEYWORDS = "Функция: |Функционал: |Свойство: ";
    private static final String SCENARIO_KEYWORDS = "Сценарий: ";

    private static String featureName = "";

    public static Map<String, List<Token>> parseFeature(String document) {
        List<String> featureRaw = Arrays.asList(document.split(Templates.NEW_LINE));
        featureRaw = featureRaw.stream().filter(line -> !"".equals(line.replaceAll(REGEX_CONTROL_CHARS, "").trim()))
                .map(line -> line.replaceAll(REGEX_CONTROL_CHARS, "").trim()).collect(Collectors.toList());

        Map<String, List<Token>> feature = new LinkedHashMap<>();
        List<Token> scenario = new ArrayList<>();
        String scenarioName = "";
        int counter = 0;
        for (String line : featureRaw) {
            Token token = getGomsToken(line);
            if (token != null) {
                scenario.add(token);
            }

            if (line.matches(String.format(REGEX_KEYWORDS, FEATURE_KEYWORDS))) {
                setFeatureName(line);
            }

            if (counter++ == featureRaw.size() - 1 || line.matches(String.format(REGEX_KEYWORDS, SCENARIO_KEYWORDS))) {
                if (!scenario.isEmpty()) {
                    feature.put(scenarioName, new ArrayList<>(scenario));
                }
                scenario.clear();
                scenarioName = line.replaceAll(SCENARIO_KEYWORDS, "").trim();
            }
        }

        return feature;
    }

    private static Token getGomsToken(String step) {
        for (Operator operator : Operators.INSTANCE.getOperators()) {
            if (null == operator.getTerm()) {
                continue;
            }
            
            Matcher matcher = Pattern.compile(operator.getTerm()).matcher(step);
            if ((step.contains(operator.getTerm()) || matcher.matches())) {
                return TokenFactory.createToken(step, operator.getSymbol());
            }
        }

        return null;
    }

    private static void setFeatureName(String line) {
        featureName = line.replaceAll(FEATURE_KEYWORDS, "").trim();
    }
    
    public static String getFeatureName() {
        return featureName;
    }
}
