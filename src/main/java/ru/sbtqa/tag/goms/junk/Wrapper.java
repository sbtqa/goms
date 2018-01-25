package ru.sbtqa.tag.goms.junk;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;

public class Wrapper {

    /**
     * Apply prelims and self-context to token
     * 
     * @param token token
     * @return wrapped token
     */
    public static List<Token> wrap(Token token) {
        List<Token> wrappedToken = new ArrayList<>();

        wrappedToken.addAll(applyPrelims(token));
        wrappedToken.addAll(applySelfContext(token));
        wrappedToken.add(token);
        
        return wrappedToken;
    }

    /**
     * Insert prelims tokens before
     * 
     * @param token token
     * @return workflow portion with prelims tokens
     */
    private static List<Token> applyPrelims(Token token) {
        List<Token> workflowPortion = new ArrayList<>();

        for (Symbol symbol : token.getPrelims()) {
            workflowPortion.add(new Token(symbol));
        }

        return workflowPortion;
    }

    /**
     * Insert H->M before token if it is self-contexted
     * 
     * @param token token
     * @return workflow portion with H and M sequence
     */
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
