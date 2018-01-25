package ru.sbtqa.tag.goms.processing;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.tokens.Token;

public class Processor {

    public static List<Token> process(List<Token> tokens) {
        List<Token> workflow = new ArrayList<>();

        for (Token token : tokens) {
            workflow.addAll(token.rule());
        }

        return workflow;
    }
}
