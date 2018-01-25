package ru.sbtqa.tag.goms.processing;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.tokens.Token;

public class Processor {

    public static List<Token> process(List<Token> tokens) {
        List<Token> workflow = new ArrayList<>();
        
        setStatesToDefault();
        for (Token token : tokens) {
            workflow.addAll(token.atomize());
        }

//        return Normalizer.normalize(workflow);
        return workflow;
    }

    private static void setStatesToDefault() {
        States.handContext = HandContext.ON_MOUSE;
        States.focusedElement = "";
    }
}
