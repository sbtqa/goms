package ru.sbtqa.tag.goms.process;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.process.tokens.Token;

public class Processor {

    public static List<Token> process(List<Token> scenario) {
        List<Token> precessedSceanrio = new ArrayList<>();
        
        setStatesToDefault();
        for (Token token : scenario) {
            precessedSceanrio.addAll(token.atomize());
        }

        return precessedSceanrio;
    }
    
    public static Map<String, List<Token>> process(Map<String, List<Token>> feature) {
        Map<String, List<Token>> processedFeature = new LinkedHashMap<>();
        
        for (Map.Entry<String, List<Token>> scenario : feature.entrySet()) {
            String scenarioName = scenario.getKey();
            List<Token> precessedSceanrio = Processor.process(scenario.getValue());
            
            processedFeature.put(scenarioName, precessedSceanrio);
        }
        
        return processedFeature;
    }

    
    
    private static void setStatesToDefault() {
        States.handContext = HandContext.ON_MOUSE;
        States.focusedElement = "";
    }
}
