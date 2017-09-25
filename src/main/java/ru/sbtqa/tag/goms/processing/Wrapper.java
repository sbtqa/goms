package ru.sbtqa.tag.goms.processing;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;
import ru.sbtqa.tag.goms.objects.contexts.HandContext;

public class Wrapper {

    public static List<Token> wrap(Token token) {
        List<Token> wrappedToken = new ArrayList<>();

        wrappedToken.addAll(applyPrelims(token));
        wrappedToken.addAll(applySelfContext(token));
        wrappedToken.add(token);
        
        return wrappedToken;
    }

    private static List<Token> applyPrelims(Token token) {
        List<Token> workflowPortion = new ArrayList<>();

        for (Symbol symbol : token.getPrelims()) {
            workflowPortion.add(new Token(symbol));
        }

        return workflowPortion;
    }

    private static List<Token> applySelfContext(Token token) {
        List<Token> workflowPortion = new ArrayList<>();

        if (token.isSelfContext()) {
            workflowPortion.add(new Token(Symbol.H));
            workflowPortion.add(new Token(Symbol.M));
            Processor.handContext = HandContext.SELF;
        }
        
        return workflowPortion;
    }
}
