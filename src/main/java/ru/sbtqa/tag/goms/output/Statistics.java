package ru.sbtqa.tag.goms.output;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ru.sbtqa.tag.goms.process.tokens.Token;

public class Statistics {

    private static final String TEXT_UNDEFINED_SCREEN = "undefined_screen";

    public static Map<String, Map<String, Float>> getInfoByScreens(Map<String, List<Token>> feature) {
        /*
         * statistics is a map with structure
         * 
         * scenario-name:
         * 	screen-name: time 
         * 	screen-name: time
         */
        Map<String, Map<String, Float>> statistics = new LinkedHashMap<>();

        for (Map.Entry<String, List<Token>> scenario : feature.entrySet()) {
            Float sum = new Float(0);
            Map<String, Float> screens = new LinkedHashMap<>();
            String currentScreen = TEXT_UNDEFINED_SCREEN;
            int counter = 0;
            for (Token token : scenario.getValue()) {
                sum += (float) token.getOperator().getTime() / 1000 * token.getMultiplier();
                if (counter++ == scenario.getValue().size() - 1 || "O".equals(token.getOperator().getSymbol())) {
                    if (sum > 0) {
                        screens.put(currentScreen, sum);
                    }
                    sum = (float) 0;
                    currentScreen = token.getStep();
                }

            }

            statistics.put(scenario.getKey(), screens);
        }

        return statistics;
    }
}
