package ru.sbtqa.tag.goms.process;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ru.sbtqa.tag.goms.process.tokens.Token;

public class Processor {

    public static List<Token> process(List<Token> scenario) {
        List<Token> processedScenario = new ArrayList<>();
        
        States.setStatesToDefault();
        for (Token token : scenario) {
            processedScenario.addAll(token.atomize());
        }

        return processedScenario;
    }
    
    public static Map<String, List<Token>> process(Map<String, List<Token>> feature) {
        Map<String, List<Token>> processedFeature = new LinkedHashMap<>();
        
        for (Map.Entry<String, List<Token>> scenario : feature.entrySet()) {
            String scenarioName = scenario.getKey();
            List<Token> processedScenario = Processor.process(scenario.getValue());
            
            processedFeature.put(scenarioName, processedScenario);
        }
        
        return processedFeature;
    }
}
